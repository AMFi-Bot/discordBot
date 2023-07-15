package org.amfibot.discord.bot.guild

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.amfibot.discord.bot.exceptions.StopEventProcessing
import org.amfibot.discord.bot.getJedis
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.time.LocalDateTime

val jedis = getJedis()
val logger: Logger = LoggerFactory.getLogger("FetchBotGuild")

/**
 * Retrieves the bot guild
 */
fun fetchBotGuild(guildId: String): Guild {
    return getGuildFromCache(guildId)
        ?: try {
            fetchAndCache(guildId)
        } catch (ex: GuildNotRegisteredException) {
            cacheGuild(guildId, null)

            throw ex
        }
}

/**
 * Caches the guild to redis
 */
fun cacheGuild(guildId: String, guild: Guild?) {
    val cachedGuild = CachedGuild(guild)

    // DSBGuild = discord bot guild
    jedis.set("DSBGuild.$guildId", jacksonObjectMapper().writeValueAsString(cachedGuild))
}

fun cacheGuild(guild: Guild) = cacheGuild(guild.id, guild)

/**
 * Retrieves the guild from the redis database.
 *
 * @return bot guild if the guild is present in the cache and the cached guild is not expired, else null
 */
fun getGuildFromCache(guildId: String): Guild? {
    val gs = jedis.get("DSBGuild.$guildId") ?: return null


    val cachedGuild = try {
        jacksonObjectMapper().readValue(gs, CachedGuild::class.java)
    } catch (e: Exception) {
        logger.error(
            "An exception occurred when parsing the cached guild object:" +
                    "\n ${e.message}"
        )

        null
    } ?: return null

    // If the cache is expired return null and enforce cache update
    if (cachedGuild.expiresAt < LocalDateTime.now())
        return null


    return cachedGuild.guild ?: throw GuildNotRegisteredException()
}

/**
 * Fetches the bot guild from an api server and caches it
 */
private fun fetchAndCache(guildId: String): Guild {
    val guild = fetchBotGuildFromServer(guildId)

    cacheGuild(guild)

    return guild
}

/**
 * Fetches the bot guild from API server
 */
fun fetchBotGuildFromServer(guildId: String): Guild {
    val request = HttpRequest
        .newBuilder()
        .uri(URI("${getAPI_URL()}/api/discord/guilds/$guildId"))
        .header("Authorization", "Bearer ${getAPIToken()}")
        .GET()
        .build()

    val response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString())

    when (response.statusCode()) {
        200 -> return jacksonObjectMapper().readValue(response.body(), Guild::class.java)

        404 -> throw GuildNotRegisteredException()

        else -> {
            throw Exception(
                "API server returned an unhandled response with status " +
                        "${response.statusCode()} and message ${response.body() ?: "null"}"
            )
        }
    }

}

class GuildNotRegisteredException(message: String? = null) : StopEventProcessing(
    message ?: "The guild is not registered in the bot scope. Ignoring"
)

/**
 * Returns the base URL for API
 */
fun getAPI_URL() =
    System.getenv("BOT_API_URL") ?: throw Exception("BOT_API_URL is undefined")

/**
 * Returns the token for API
 */
fun getAPIToken() =
    System.getenv("BOT_API_TOKEN") ?: throw Exception("BOT_API_TOKEN is undefined")