package org.amfibot.discord.bot.guild.modules.general.logging

import com.fasterxml.jackson.annotation.JsonValue

enum class LogType(@JsonValue val id: String) {
    MESSAGE_CREATE("message_create")
}