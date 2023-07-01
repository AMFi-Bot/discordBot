package org.amfibot.discord.bot

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import org.amfibot.discord.bot.command.slash.clearSlashCommands
import org.amfibot.discord.bot.command.slash.registerAllSlashCommands
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val botToken =
        System.getenv("BOT_TOKEN")
            ?: throw Exception("BOT_TOKEN is undefined")

    val jda = JDABuilder.createDefault(botToken)
        .enableIntents(GatewayIntent.MESSAGE_CONTENT) // enables explicit access to message.getContentDisplay()
        .build()

    parseArgs(args, jda)

    jda.addEventListener(toJDAEventListener(mainListenerChain.getEventListener()))
}

fun parseArgs(args: Array<String>, jda: JDA) {
    if (args.contains("clear_slash_commands")) {
        clearSlashCommands(jda)
        exitProcess(0)
    } else if (args.contains("register_slash_commands")) {
        registerAllSlashCommands(jda)
        exitProcess(0)
    }
}


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