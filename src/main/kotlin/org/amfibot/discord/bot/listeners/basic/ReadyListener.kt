package org.amfibot.discord.bot.listeners.basic

import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.session.ReadyEvent
import org.amfibot.discord.bot.EventListener
import org.amfibot.discord.bot.guild.Guild
import org.slf4j.LoggerFactory


class ReadyListener : EventListener {
    override fun onEvent(event: GenericEvent, botGuild: Guild?): Boolean {
        if (event !is ReadyEvent) return false


        LoggerFactory.getLogger(this::class.qualifiedName).info("The bot is ready!")
        return true
    }
}