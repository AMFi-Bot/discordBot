package org.amfibot.discord.bot.command.listener

import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import org.amfibot.discord.bot.EventListener
import org.amfibot.discord.bot.command.CommandContext
import org.amfibot.discord.bot.command.commands
import org.slf4j.LoggerFactory


object SlashCommandListener: EventListener {

    /**
     * TODO: Not yet implemented
     */
    override fun onEvent(event: GenericEvent): Boolean {
        if (event !is SlashCommandInteractionEvent) return false

        val commandName = event.name

        val command = commands.get(commandName) ?: return false

        val status = command.invoke(CommandContext(event))

        LoggerFactory.getLogger(this.javaClass).debug(status.statusCode.toString())

        return true
    }

}