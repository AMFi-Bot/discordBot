package org.amfibot.discord.bot.command

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import org.amfibot.discord.bot.command.slash.SlashCommandTypes

/**
 * Defines the bot command class skeleton
 */
interface Command {

    val slashCommandType: SlashCommandTypes

    /**
     * @return The name of the command
     */
    fun getName(): String

    /**
     * @return The description of the command
     */
    fun getDescription(): String

    /**
     * @return A command as a slash one
     */
    fun getAsSlashCommand(): SlashCommandData


    /**
     * Invokes the command
     * @return Command execution status
     */
    fun invoke(event: SlashCommandInteractionEvent): CommandStatus


}