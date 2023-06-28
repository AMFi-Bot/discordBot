package org.amfibot.discord.bot

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent

fun main() {
    val botToken = System.getenv("BOT_TOKEN")

    if (botToken == "" || botToken == null) throw Exception("The bot token is undefined")

    val jda = JDABuilder.createDefault(botToken)
        .enableIntents(GatewayIntent.MESSAGE_CONTENT) // enables explicit access to message.getContentDisplay()
        .build()


    jda.addEventListener(toJDAEventListener(mainListenerChain.getEventListener()))
}