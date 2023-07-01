package org.amfibot.discord.bot.config

enum class RabbitQueues(val queueName: String) {
    DISCORD_GUILD_REGISTERED("discord_guild.registered"),
    DISCORD_GUILD_UPDATED("discord_guild.updated"),
}