package org.amfibot.discord.bot.listeners.rabbitmq

import com.rabbitmq.client.Connection
import org.amfibot.discord.bot.initRabbitChannel

/**
 * Registers all rabbitmq listeners for bot
 */
fun listenRabbitMQ(connection: Connection) {
    val botGuildEventsChannel = initRabbitChannel(connection)

    listenToBotGuildEvents(botGuildEventsChannel)
}