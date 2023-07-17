package org.amfibot.discord.bot.guild.modules.general.logging

data class Log(
    val enabled: Boolean = false,
    val defaultChannel: String? = null,
    val loggers: Loggers = Loggers(),
)