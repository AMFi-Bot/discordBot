package org.amfibot.discord.bot


/**
 * Registers all top-level event listeners
 */
val mainListenerChainMembers: Collection<EventListener> = listOf()



val mainListenerChain = BaseListenerChain(mainListenerChainMembers)