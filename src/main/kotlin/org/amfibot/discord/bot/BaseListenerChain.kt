package org.amfibot.discord.bot

import net.dv8tion.jda.api.events.GenericEvent
import org.amfibot.discord.bot.guild.Guild

open class BaseListenerChain(private val listenerChain: Collection<EventListener>) : ListenerChain() {
    override fun processEvent(event: GenericEvent, botGuild: Guild?): Boolean {
        for (il in listenerChain) {
            if (il.onEvent(event, botGuild)) return true
        }

        return false
    }
}