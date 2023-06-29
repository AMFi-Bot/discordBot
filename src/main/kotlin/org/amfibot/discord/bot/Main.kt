package org.amfibot.discord.bot

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import org.amfibot.discord.bot.command.slash.clearSlashCommands
import org.amfibot.discord.bot.command.slash.registerAllSlashCommands

fun main(args: Array<String>) {
    val botToken = System.getenv("BOT_TOKEN")

    if (botToken == "" || botToken == null) throw Exception("The bot token is undefined")

    val jda = JDABuilder.createDefault(botToken)
        .enableIntents(GatewayIntent.MESSAGE_CONTENT) // enables explicit access to message.getContentDisplay()
        .build()

    return parseArgs(args, jda)
}

fun parseArgs(args: Array<String>, jda: JDA){
    if (args.contains("clear_slash_commands")){
        return clearSlashCommands(jda)
    } else if (args.contains("register_slash_commands")) {
        return registerAllSlashCommands(jda)
    } else{
        jda.addEventListener(toJDAEventListener(mainListenerChain.getEventListener()))
    }
}