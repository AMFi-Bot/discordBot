package org.amfibot.discord.bot.guild

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.DurationUnit

/**
 * Represents the discord guild that is cached to redis
 *
 * @param guild Bot guild or null if the guild is not registered with bot
 * @param cachedAt Epoch time in UTC
 * @param expiresAt Epoch time in UTC
 *
 */
class CachedGuild(
    val guild: Guild?,
    
    @JsonProperty("cached_at")
    val cachedAt: Long,

    @JsonProperty("expires_at")
    val expiresAt: Long

) {
    /**
     * @param expirationDuration cache life duration. Defaults to 5 minutes
     */
    constructor(
        guild: Guild? = null,
        expirationDuration: Duration = 5.minutes
    ) : this(
        guild = guild,
        cachedAt = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
        expiresAt = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) +
                expirationDuration.toLong(DurationUnit.SECONDS)
    )

}