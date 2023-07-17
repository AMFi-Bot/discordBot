package org.amfibot.discord.bot.listeners.logging

import net.dv8tion.jda.api.events.GenericEvent
import org.amfibot.discord.bot.ListenerChain
import org.amfibot.discord.bot.guild.Guild

/**
 * Listens to all events and logs it depends on the configuration
 *
 * @property stopChain Stops the chain iteration when the logger method is reached
 */
class LogsListener(
    val stopChain: Boolean,
    private val listenerChain: Collection<LogsEventListener>
) : ListenerChain() {
    override fun processEvent(event: GenericEvent, botGuild: Guild?): Boolean {
        if (botGuild == null) return false

        if (!botGuild.general.log.enabled) return false

        for (listener in listenerChain) {
            if (
                listener.eventClass == event.javaClass
            ) {

                listener.onEvent(event, botGuild)
            }
        }

        return stopChain
    }

}