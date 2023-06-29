package org.amfibot.discord.bot.command.basic.help

import org.amfibot.discord.bot.command.Command
import org.amfibot.discord.bot.command.CommandContext
import org.amfibot.discord.bot.command.CommandStatus
import org.slf4j.LoggerFactory

object HelpCommand: Command {
    override fun getName(): String = "help"

    override fun getDescription(): String = "Lists the help of the bot usage"

    override fun invoke(context: CommandContext): CommandStatus {
        LoggerFactory.getLogger(this::class.java).info("Help command executed")

        return CommandStatus.OK
    }
}