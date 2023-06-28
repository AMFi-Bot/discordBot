package org.amfibot.discord.bot.listeners.basic

import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.session.ReadyEvent
import org.amfibot.discord.bot.EventListener
import org.slf4j.LoggerFactory

class ReadyListener: EventListener {
    override fun onEvent(event: GenericEvent): Boolean {
        if (event is ReadyEvent) {
            LoggerFactory.getLogger(this::class.qualifiedName).info("The bot is ready!")
            return true
        }
        return false
    }
}