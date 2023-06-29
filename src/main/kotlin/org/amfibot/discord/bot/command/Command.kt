package org.amfibot.discord.bot.command

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
     * Invokes the command
     * @param context The command context
     * @return Command execution status
     */
    fun invoke(context: CommandContext): CommandStatus
}