package org.amfibot.discord.bot

import net.dv8tion.jda.api.events.GenericEvent

/**
 * Handles every event from the bot.
 * Distributes the events to the listeners.
 * Builds the listener chain that allows to distribute the events to only one listener
 * If the EventListener returns the true value, the listener chain stops to
 * process the event to next listeners.
 */
abstract class ListenerChain(private val listenerChain: Collection<EventListener>) {

    /**
     * Processes the event through the chain
     */
    abstract fun processEvent(event: GenericEvent): Boolean


    /**
     * @return Event listener instance of the ListenerChain that allows to implement
     * the Listener chain to the another listener chain or the JDA event listener chain
     */
    fun getEventListener(): EventListener {
        return EventListener {event ->
            processEvent(event)
        }
    }
}