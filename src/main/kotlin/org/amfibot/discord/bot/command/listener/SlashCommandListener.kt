package org.amfibot.discord.bot.command.listener

import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import org.amfibot.discord.bot.EventListener
import org.amfibot.discord.bot.command.commands
import org.amfibot.discord.bot.exceptions.NoBotGuildException
import org.amfibot.discord.bot.guild.Guild
import org.slf4j.LoggerFactory


object SlashCommandListener : EventListener {

    override fun onEvent(event: GenericEvent, botGuild: Guild?): Boolean {
        if (event !is SlashCommandInteractionEvent) return false

        if (botGuild == null) throw NoBotGuildException()

        val commandName = event.name

        val command = commands[commandName] ?: return false
        
        val status = command.invoke(event, botGuild)

        LoggerFactory.getLogger(this.javaClass).debug(status.statusCode.toString())

        return true
    }

}