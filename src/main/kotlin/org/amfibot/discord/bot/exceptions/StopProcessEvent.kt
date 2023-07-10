package org.amfibot.discord.bot.exceptions

/**
 * Stops the event processing through the listeners but does not throw an exception
 */
class StopEventProcessing(message: String? = null, cause: Throwable? = null) : Exception(message, cause)