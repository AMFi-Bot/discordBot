package org.amfibot.discord.bot.command

import net.dv8tion.jda.api.events.GenericEvent


/**
 * Defines the context for a command
 */
data class CommandContext(val event: GenericEvent)