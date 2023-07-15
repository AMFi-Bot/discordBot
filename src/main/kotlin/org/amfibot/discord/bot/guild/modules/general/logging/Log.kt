package org.amfibot.discord.bot.guild.modules.general.logging

data class Log(
    val enabled: Boolean = false,
    val baseChannel: String? = null,
    val loggers: Loggers = Loggers(),
)