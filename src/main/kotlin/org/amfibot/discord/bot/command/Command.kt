package org.amfibot.discord.bot.command

import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData

/**
 * Defines the bot command class skeleton
 */
interface Command {
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
     * @param context The command context
     * @return Command execution status
     */
    fun invoke(context: CommandContext): CommandStatus


}