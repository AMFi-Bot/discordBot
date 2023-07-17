package org.amfibot.discord.bot.listeners.rabbitmq

import com.rabbitmq.client.Channel
import org.amfibot.discord.bot.config.RabbitQueues
import org.amfibot.discord.bot.guild.Guild
import org.amfibot.discord.bot.guild.cacheGuild
import org.amfibot.discord.bot.guild.guildMapper
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

    val consumerTag = channel.basicConsume(
        queueName,
        true,
        { _, message ->
            val content = String(message.body, Charsets.UTF_8)

            // update cache
            val guild = guildMapper.readValue(content, Guild::class.java)

            cacheGuild(guild)
        },
        { _ ->

        }
    )

    logger.info("RabbitMQ Bot guild update event listener registered with the consumer tag $consumerTag")

    return consumerTag
}

/**
 * Listens to bot guild register event
 *
 * @return rabbitMQ consumerTag
 */
fun listenToGuildRegister(channel: Channel): String {
    val queueName = RabbitQueues.DISCORD_GUILD_REGISTERED.queueName

    channel.queueDeclare(queueName, false, false, false, null)

    val consumerTag = channel.basicConsume(
        queueName,
        true,
        { _, message ->
            val content = String(message.body, Charsets.UTF_8)

            // update cache
            val guild = guildMapper.readValue(content, Guild::class.java)

            cacheGuild(guild)
        },
        { _ ->

        }
    )

    logger.info("RabbitMQ Bot guild update event listener registered with the consumer tag $consumerTag")

    return consumerTag
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