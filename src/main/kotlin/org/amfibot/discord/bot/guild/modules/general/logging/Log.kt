package org.amfibot.discord.bot.guild.modules.general.logging

data class Log(
    var enabled: Boolean = false,
    var types: Collection<LogType> = listOf(),
    var channel: String? = null,
)