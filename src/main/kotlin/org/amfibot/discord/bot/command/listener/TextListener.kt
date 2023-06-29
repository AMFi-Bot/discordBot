package org.amfibot.discord.bot.command.listener

import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import org.amfibot.discord.bot.EventListener
import org.amfibot.discord.bot.command.CommandContext
import org.amfibot.discord.bot.command.commands
import org.slf4j.LoggerFactory

/**
 * Listens to the message sent event and invokes the bot command
 */
object TextListener: EventListener {
    override fun onEvent(event: GenericEvent): Boolean {
        if (event !is MessageReceivedEvent) return false
        /**
         * TODO: Connect prefix to database
         */
        val prefix = "!"

        val message = event.message

        if (!message.contentRaw.startsWith(prefix)) return false

        val content = message.contentRaw.removePrefix(prefix)
        val commandName = content.split(" ")[0]
        val command = commands[commandName] ?: return false

        val context = CommandContext(event)

        val status = command.invoke(context)

        LoggerFactory.getLogger(this.javaClass).debug(status.statusCode.toString())

        return true
    }
}