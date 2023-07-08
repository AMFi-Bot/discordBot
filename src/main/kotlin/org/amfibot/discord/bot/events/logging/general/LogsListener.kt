package org.amfibot.discord.bot.events.logging.general

import net.dv8tion.jda.api.events.GenericEvent
import org.amfibot.discord.bot.EventListener
import org.amfibot.discord.bot.guild.Guild

/**
 * Listens to all events and logs it depends on the configuration
 *
 * @property stopChain Stops the chain iteration when the logger method is reached
 */
class LogsListener(val stopChain: Boolean) : EventListener {


    override fun onEvent(event: GenericEvent, botGuild: Guild?): Boolean {
        return stopChain
    }
}