package org.amfibot.discord.bot

import net.dv8tion.jda.api.events.GenericEvent
import org.amfibot.discord.bot.exceptions.NoBotGuildException
import org.amfibot.discord.bot.exceptions.StopEventProcessing
import org.amfibot.discord.bot.guild.Guild
import org.amfibot.discord.bot.guild.fetchBotGuild
import net.dv8tion.jda.api.hooks.EventListener as JDAEventListener

/**
 * Defines the event listener for the ListenersChain
 * This listener may be used as replacement of JDA Event listener
 */
fun interface EventListener {

    /**
     * @param event An event object
     * @param botGuild Bot guild object that collects all the guild configuration
     *
     *
     * @return If the listener handled the event return true else false
     */
    @Throws(NoBotGuildException::class)
    fun onEvent(event: GenericEvent, botGuild: Guild?): Boolean
}

/**
 * Converts an amfi bot event listener instance to a JDA event listener
 */
fun toJDAEventListener(eventListener: EventListener): JDAEventListener {
    return JDAEventListener { event ->
        val method = event.javaClass.methods.find { m -> m.name == "getGuild" }


        val guild = method?.invoke(event) as net.dv8tion.jda.api.entities.Guild?

        if (guild != null) {
            try {
                val botGuild = fetchBotGuild(guild.id)
                eventListener.onEvent(event, botGuild)
                return@JDAEventListener
            } catch (_: StopEventProcessing) {
                // If the exception has occurred do not process an event through the listener
                return@JDAEventListener
            }
        }


        eventListener.onEvent(event, null)
        return@JDAEventListener
    }
}