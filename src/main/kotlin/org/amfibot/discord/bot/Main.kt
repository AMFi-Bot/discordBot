package org.amfibot.discord.bot

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import org.amfibot.discord.bot.command.slash.clearSlashCommands
import org.amfibot.discord.bot.command.slash.registerAllSlashCommands
import org.amfibot.discord.bot.guild.getAPIToken
import org.amfibot.discord.bot.guild.getAPI_URL
import org.amfibot.discord.bot.listeners.rabbitmq.listenRabbitMQ
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val botToken = getBotToken()

    // Check the env variables to contain an api Token and url
    getAPIToken()
    getAPI_URL()
    getRedisCredentials()


    val jda = JDABuilder.createDefault(botToken)
        .enableIntents(GatewayIntent.MESSAGE_CONTENT) // enables explicit access to message.getContentDisplay()
        .build()

    parseArgs(args, jda)

    jda.addEventListener(toJDAEventListener(mainListenerChain.getEventListener()))

    // listen to rabbitMQ
    listenRabbitMQ(getRabbitConnection())
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


fun getBotToken() = System.getenv("BOT_TOKEN") ?: throw Exception("BOT_TOKEN is undefined")