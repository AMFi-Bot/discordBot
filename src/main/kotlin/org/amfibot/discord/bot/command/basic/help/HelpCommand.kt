package org.amfibot.discord.bot.command.basic.help

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import org.amfibot.discord.bot.command.Command
import org.amfibot.discord.bot.command.CommandStatus
import org.amfibot.discord.bot.command.slash.SlashCommandTypes
import org.amfibot.discord.bot.guild.Guild
import org.slf4j.LoggerFactory

object HelpCommand : Command {
    override val slashCommandType: SlashCommandTypes = SlashCommandTypes.GLOBAL

    override fun getName(): String = "help"

    override fun getDescription(): String = "Lists the help of the bot usage"
    override fun register(jda: JDA, guild: Guild?): net.dv8tion.jda.api.interactions.commands.Command? {
        return jda
            .upsertCommand(
                getAsSlashCommand()
            )
            .complete()
    }

    private fun getAsSlashCommand(): SlashCommandData = Commands.slash(getName(), getDescription())

    override fun invoke(event: SlashCommandInteractionEvent): CommandStatus {

        LoggerFactory.getLogger(this::class.java).info("Help command executed as a slash command")
        event.reply("Welcome to the amfi bot!").queue()

        return CommandStatus.OK
    }
}