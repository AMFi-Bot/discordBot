package org.amfibot.discord.bot.listeners.rabbitmq

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.rabbitmq.client.Channel
import org.amfibot.discord.bot.config.RabbitQueues
import org.amfibot.discord.bot.guild.Guild
import org.amfibot.discord.bot.guild.cacheGuild
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val logger: Logger = LoggerFactory.getLogger("BotGuildRabbit")

/**
 * Listens to bot guild update event
 *
 * @return rabbitMQ consumerTag
 */
fun listenToGuildUpdate(channel: Channel): String {
    val queueName = RabbitQueues.DISCORD_GUILD_UPDATED.queueName

    channel.queueDeclare(queueName, false, false, false, null)

    val consumeTag = channel.basicConsume(
        queueName,
        true,
        { consumerTag, message ->
            val content = String(message.body, Charsets.UTF_8)

            // update cache
            val guild = jacksonObjectMapper().readValue(content, Guild::class.java)

            cacheGuild(guild)
        },
        { consumerTag ->

        }
    )

    logger.info("RabbitMQ Bot guild update event listener registered with consume tag $consumeTag")

    return consumeTag
}

/**
 * Listens to bot guild register event
 *
 * @return rabbitMQ consumerTag
 */
fun listenToGuildRegister(channel: Channel): String {
    val queueName = RabbitQueues.DISCORD_GUILD_REGISTERED.queueName

    channel.queueDeclare(queueName, false, false, false, null)

    val consumeTag = channel.basicConsume(
        queueName,
        true,
        { consumerTag, message ->
            val content = String(message.body, Charsets.UTF_8)

            // update cache
            val guild = jacksonObjectMapper().readValue(content, Guild::class.java)

            cacheGuild(guild)
        },
        { consumerTag ->

        }
    )

    logger.info("RabbitMQ Bot guild update event listener registered with consume tag $consumeTag")

    return consumeTag
}

data class BotGuildEvents(
    val guildUpdateTag: String,
    val guildRegisterTag: String,
)

/**
 * Listens to the events associated with the bot guild
 */
fun listenToBotGuildEvents(channel: Channel): BotGuildEvents {
    return BotGuildEvents(
        guildUpdateTag = listenToGuildUpdate(channel),
        guildRegisterTag = listenToGuildRegister(channel)
    )
}