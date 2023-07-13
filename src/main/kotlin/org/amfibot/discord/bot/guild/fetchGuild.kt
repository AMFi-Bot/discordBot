package org.amfibot.discord.bot.guild

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.amfibot.discord.bot.exceptions.StopEventProcessing
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers


/**
 * Retrieves the bot guild
 */
fun fetchBotGuild(guildId: String): Guild {
    // TODO: Implement caching
    return fetchBotGuildFromServer(guildId)
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

        404 -> throw StopEventProcessing(
            "The guild is not found. May be it is not registered in the bot scope. Ignoring"
        )

        else -> {
            throw Exception(
                "API server returned an unhandled response with status " +
                        "${response.statusCode()} and message ${response.body() ?: "null"}"
            )
        }
    }

}

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