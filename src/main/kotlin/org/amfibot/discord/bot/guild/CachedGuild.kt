package org.amfibot.discord.bot.guild

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.util.*
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.DurationUnit

/**
 * Represents the discord guild that is cached to redis
 *
 * @param guild Bot guild or null if the guild is not registered with bot
 *
 * @param expirationDuration cache life duration. Defaults to 5 minutes
 */
class CachedGuild(
    val guild: Guild? = null,
    expirationDuration: Duration = 5.minutes
) {
    @JsonProperty("cached_at")
    val cachedAt: LocalDateTime = LocalDateTime.now()

    @JsonProperty("expires_at")
    val expiresAt: LocalDateTime = cachedAt.plusSeconds(
        expirationDuration.toLong(DurationUnit.SECONDS)
    )
}