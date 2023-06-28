package org.amfibot.discord.bot

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.utils.data.DataObject

val emptyJda = JDABuilder.createDefault(null).build()

val emptyEvent = object : GenericEvent {
    override fun getJDA(): JDA {
        return emptyJda
    }

    override fun getResponseNumber(): Long {
        return 0
    }

    override fun getRawData(): DataObject? {
        return null
    }

}