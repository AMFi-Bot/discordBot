package org.amfibot.discord.bot

import org.amfibot.discord.bot.command.listener.SlashCommandListener
import org.amfibot.discord.bot.listeners.basic.basicChain
import org.amfibot.discord.bot.listeners.logging.LogsListener
import org.amfibot.discord.bot.listeners.logging.logsChain


/**
 * Registers all top-level event listeners
 */
val mainListenerChainMembers: Collection<EventListener> = listOf(

    LogsListener(stopChain = false, logsChain).getEventListener(),
    basicChain.getEventListener(),
    SlashCommandListener,
)


val mainListenerChain = BaseListenerChain(mainListenerChainMembers)