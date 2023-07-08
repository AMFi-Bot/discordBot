package org.amfibot.discord.bot

import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.guild.GenericGuildEvent
import org.amfibot.discord.bot.exceptions.NoBotGuildException
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
        if (event is GenericGuildEvent) {
            val guildId = event.guild.id

            val guild = fetchBotGuild(guildId)

            eventListener.onEvent(event, guild)
        } else
            eventListener.onEvent(event, null)
    }
}