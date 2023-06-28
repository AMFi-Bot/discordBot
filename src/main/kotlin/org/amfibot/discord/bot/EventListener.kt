package org.amfibot.discord.bot

import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.hooks.EventListener as JDAEventListener

/**
 * Defines the event listener for the ListenersChain
 * This listener may be used as replacement of JDA Event listener
 */
fun interface EventListener {
    /**
     * @return If the listener handled the event return true else false
     */
    fun onEvent(event: GenericEvent): Boolean
}

/**
 * Converts an amfi bot event listener instance to a JDA event listener
 */
fun toJDAEventListener(eventListener: EventListener): JDAEventListener {
    return JDAEventListener {
        event -> eventListener.onEvent(event)
    }
}