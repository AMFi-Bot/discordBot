package org.amfibot.discord.bot

import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool

fun getJedisPool(): JedisPool {
    val (host, port) = getRedisCredentials()
    return JedisPool(host, port)
}

fun getJedis(pool: JedisPool? = null): Jedis = (pool ?: getJedisPool()).resource

fun getRedisCredentials(): JedisCredentials {
    val host = System.getenv("REDIS_HOST") ?: throw Exception("REDIS_HOST is undefined")
    val port = System.getenv("REDIS_PORT") ?: "6379"

    return JedisCredentials(host = host, port = port.toInt())
}

data class JedisCredentials(val host: String, val port: Int)