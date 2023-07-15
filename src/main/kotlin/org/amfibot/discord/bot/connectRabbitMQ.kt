package org.amfibot.discord.bot

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory

fun initRabbitConnection(): Connection {
    val factory = ConnectionFactory()
    val host = System.getenv("RABBITMQ_HOST")
        ?: throw Exception("RABBITMQ_HOST is undefined")

    val username = System.getenv("RABBITMQ_DEFAULT_USER")
        ?: throw Exception("RABBITMQ_DEFAULT_USER is undefined")

    val password = System.getenv("RABBITMQ_DEFAULT_PASS")
        ?: throw Exception("RABBITMQ_DEFAULT_PASS is undefined")

    factory.host = host
    factory.username = username
    factory.password = password

    return factory.newConnection()
}

fun initRabbitChannel(connection: Connection): Channel = connection.createChannel()

private var connection: Connection? = null

fun getRabbitConnection(): Connection {
    return if (connection != null) {
        connection as Connection
    } else {
        connection = initRabbitConnection()
        connection as Connection
    }
}