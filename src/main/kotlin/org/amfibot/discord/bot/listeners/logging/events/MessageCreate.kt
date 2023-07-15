package org.amfibot.discord.bot.listeners.logging.events

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import org.amfibot.discord.bot.guild.Guild
import org.amfibot.discord.bot.guild.modules.general.logging.LogType
import org.amfibot.discord.bot.listeners.logging.LogsEventListener

object MessageCreate : LogsEventListener {
    override val logType: LogType = LogType.MESSAGE_CREATE
    override val eventClass: Class<MessageReceivedEvent> = MessageReceivedEvent::class.java
    override fun onEvent(event: GenericEvent, botGuild: Guild) {
        if (event is MessageReceivedEvent && event.author.id != event.jda.selfUser.id) {
            val message =
                "A message has been create by user <@${event.author.id}> with contents\n" +
                        event.message.contentRaw

            val channel =
                (event.guild.channels.find { it.id == botGuild.generalModule.log.channel }
                        as? MessageChannel?) ?: return

            channel.sendMessage(message).queue()
        }
    }
}