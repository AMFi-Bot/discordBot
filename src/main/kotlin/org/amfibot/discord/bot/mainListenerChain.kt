package org.amfibot.discord.bot

import org.amfibot.discord.bot.listeners.basic.basicChain


/**
 * Registers all top-level event listeners
 */
val mainListenerChainMembers: Collection<EventListener> = listOf(
    basicChain.getEventListener()
)



val mainListenerChain = BaseListenerChain(mainListenerChainMembers)