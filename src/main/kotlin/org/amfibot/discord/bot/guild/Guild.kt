package org.amfibot.discord.bot.guild

import org.amfibot.discord.bot.guild.modules.general.GeneralModule

/**
 * Discord guild instance
 */
data class Guild(val id: String, val generalModule: GeneralModule = GeneralModule())