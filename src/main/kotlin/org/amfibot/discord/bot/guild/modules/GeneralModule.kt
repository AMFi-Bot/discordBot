package org.amfibot.discord.bot.guild.modules

data class GeneralModule(
    var logEnabled: Boolean = false,
    var logTypes: List<String> = listOf(),
    var logChannel: String? = null,
)