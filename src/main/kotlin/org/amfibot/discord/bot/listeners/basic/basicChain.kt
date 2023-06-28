package org.amfibot.discord.bot.listeners.basic

import org.amfibot.discord.bot.BaseListenerChain
import org.amfibot.discord.bot.EventListener

private val listenerChain: Collection<EventListener> = listOf(
    ReadyListener()
)

val basicChain = BaseListenerChain(listenerChain)