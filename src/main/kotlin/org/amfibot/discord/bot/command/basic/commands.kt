package org.amfibot.discord.bot.command.basic

import org.amfibot.discord.bot.command.Command
import org.amfibot.discord.bot.command.basic.help.HelpCommand

/**
 * Map of the basic commands of the bot
 */
val basicCommands: HashMap<String, Command> = hashMapOf(
    HelpCommand.name to HelpCommand
)