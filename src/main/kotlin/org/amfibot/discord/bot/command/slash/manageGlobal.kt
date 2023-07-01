package org.amfibot.discord.bot.command.slash

import net.dv8tion.jda.api.JDA
import org.amfibot.discord.bot.command.commands

/**
 * Registers all the slash commands
 */
fun registerAllSlashCommands(jda: JDA) {

    for ((_, command) in commands) {
        if (command.slashCommandType != SlashCommandTypes.GLOBAL) continue

        val cmd = command.register(jda) ?: continue
        println("Registered a slash command with name ${cmd.name} and id ${cmd.id}")
    }
}

/**
 * Deletes all slash commands
 */
fun clearSlashCommands(jda: JDA) {
    for (cmd in jda.retrieveCommands().complete()) {
        println("Deleting a slash command with id ${cmd.id} and name ${cmd.name}")
        jda.deleteCommandById(cmd.id).complete()
    }
}