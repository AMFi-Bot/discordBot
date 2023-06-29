package org.amfibot.discord.bot.command

/**
 * Defines the statuses that a command may return
 */
enum class CommandStatus(val statusCode: Int) {
    OK(0),
    FAIL(-1)
}