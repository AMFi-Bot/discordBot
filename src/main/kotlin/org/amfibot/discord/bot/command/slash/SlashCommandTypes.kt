package org.amfibot.discord.bot.command.slash

/**
 * Represents type of slash command of the command
 */
enum class SlashCommandTypes {
    /**
     * Do not register a slash command
     */
    NONE,

    /**
     * Register a slash command globally, for all guilds
     */
    GLOBAL,

    /**
     * Register a slash command for a bunch of guilds, but not all
     */
    LOCAL
}