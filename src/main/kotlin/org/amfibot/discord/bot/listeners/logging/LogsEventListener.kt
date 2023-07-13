package org.amfibot.discord.bot.listeners.logging

import net.dv8tion.jda.api.events.GenericEvent
import org.amfibot.discord.bot.guild.Guild
import org.amfibot.discord.bot.guild.modules.general.logging.LogType

/**
 * This interface represents the logging event listener.
 * The logging event listener object MUST listen to only one event
 */
interface LogsEventListener {

    val logType: LogType

    val eventClass: Class<out GenericEvent>
    fun onEvent(event: GenericEvent, botGuild: Guild)
}