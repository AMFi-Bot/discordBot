package org.amfibot.discord.bot

import net.dv8tion.jda.api.events.GenericEvent

class BaseListenerChain(private val listenerChain: Collection<EventListener>) : ListenerChain(listenerChain) {
    override fun processEvent(event: GenericEvent): Boolean {
        for (il in listenerChain){
            if (il.onEvent(event)) return true
        }

        return false
    }
}