package org.amfibot.discord.bot.command.basic.help

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import org.amfibot.discord.bot.command.Command
import org.amfibot.discord.bot.command.CommandContext
import org.amfibot.discord.bot.command.CommandStatus
import org.slf4j.LoggerFactory

object HelpCommand: Command {
    override fun getName(): String = "help"

    override fun getDescription(): String = "Lists the help of the bot usage"
    override fun getAsSlashCommand(): SlashCommandData = Commands.slash(getName(), getDescription())

    override fun invoke(context: CommandContext): CommandStatus {
        val event = context.event

        when (event) {
            is MessageReceivedEvent -> LoggerFactory.getLogger(this::class.java).info("Help command executed as a message command")

            is SlashCommandInteractionEvent -> {
                LoggerFactory.getLogger(this::class.java).info("Help command executed as a slash command")
                event.reply("Welcome to the amfi bot!").queue()
            }

            else -> {
                throw Exception("Command listened incorrectly")
            }
        }

        return CommandStatus.OK
    }
}