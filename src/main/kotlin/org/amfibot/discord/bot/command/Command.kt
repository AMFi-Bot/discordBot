package org.amfibot.discord.bot.command

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.Command
import org.amfibot.discord.bot.command.slash.SlashCommandTypes
import org.amfibot.discord.bot.guild.Guild

/**
 * Defines the bot command class skeleton
 */
interface Command {

    /**
     * The name of the command
     */
    val name: String

    /**
     * The type of the command
     */
    val slashCommandType: SlashCommandTypes

    /**
     * Registers the command
     *
     * @param guild Bot guild, provided if the slash command registers locally
     *
     * @return registered command object
     */
    fun register(jda: JDA, guild: Guild? = null): Command?


    /**
     * Invokes the command
     *
     * @return Command execution status
     */
    fun invoke(event: SlashCommandInteractionEvent): CommandStatus
}