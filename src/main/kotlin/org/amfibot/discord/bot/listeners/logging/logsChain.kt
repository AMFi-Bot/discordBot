package org.amfibot.discord.bot.listeners.logging

import org.amfibot.discord.bot.listeners.logging.events.MessageCreate

val logsChain: Collection<LogsEventListener> = listOf(
    MessageCreate
)