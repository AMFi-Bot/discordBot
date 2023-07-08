package org.amfibot.discord.bot.exceptions

/**
 * Throws when the bot guild is forced by the method but for some reasons is not provided
 */
class NoBotGuildException(message: String? = null, cause: Throwable? = null) : Exception(message, cause)