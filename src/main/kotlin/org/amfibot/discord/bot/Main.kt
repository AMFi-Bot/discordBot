package org.amfibot.discord.bot

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import org.amfibot.discord.bot.command.slash.clearSlashCommands
import org.amfibot.discord.bot.command.slash.registerAllSlashCommands
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val botToken = System.getenv("BOT_TOKEN")

    if (botToken == "" || botToken == null) throw Exception("The bot token is undefined")

    val jda = JDABuilder.createDefault(botToken)
        .enableIntents(GatewayIntent.MESSAGE_CONTENT) // enables explicit access to message.getContentDisplay()
        .build()

    parseArgs(args, jda)

    jda.addEventListener(toJDAEventListener(mainListenerChain.getEventListener()))
}

fun parseArgs(args: Array<String>, jda: JDA){
    if (args.contains("clear_slash_commands")){
        clearSlashCommands(jda)
        exitProcess(0)
    } else if (args.contains("register_slash_commands")) {
        registerAllSlashCommands(jda)
        exitProcess(0)
    }
}