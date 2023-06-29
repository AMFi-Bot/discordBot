package org.amfibot.discord.bot.command

import org.amfibot.discord.bot.command.basic.basicCommands

/**
 * Collects all the command of the bot
 */
val commands: HashMap<String, Command> = hashMapOf(
    *basicCommands.toList().toTypedArray()
)