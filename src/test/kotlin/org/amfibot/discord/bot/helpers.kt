package org.amfibot.discord.bot

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.utils.data.DataObject

/**
 * May be used to fill getJDA methods. Fails when the method executed
 */
fun emptyJda(): JDA {return JDABuilder.createDefault(null).build()}

val emptyEvent = object : GenericEvent {
    override fun getJDA(): JDA {
        return emptyJda()
    }

    override fun getResponseNumber(): Long {
        return 0
    }

    override fun getRawData(): DataObject? {
        return null
    }

}