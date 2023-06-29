package org.amfibot.discord.bot.command.slash

import net.dv8tion.jda.api.JDA
import org.amfibot.discord.bot.command.commands

fun registerAllSlashCommands(jda: JDA) {

    for ((_, command) in commands){
        val cmd =
            jda
                .upsertCommand(
                    command.getAsSlashCommand()
                )
                .complete()
        println("Registered a slash command with name ${cmd.name} and id ${cmd.id}")
    }
}
fun deleteAllSlashCommands(jda: JDA) {
    for (cmd in jda.retrieveCommands().complete()){
        println("Deleting a slash command with id ${cmd.id} and name ${cmd.name}")
        jda.deleteCommandById(cmd.id).complete()
    }
}