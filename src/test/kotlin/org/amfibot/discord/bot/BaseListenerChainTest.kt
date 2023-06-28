package org.amfibot.discord.bot

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BaseListenerChainTest {

    @Test
    fun iteratesChainCorrectly() {
        val buildExpected = BooleanArray(3) { true }
        val expected = buildExpected.plus(BooleanArray(2) {false})
        val actual = BooleanArray(5) {false}

        val chain: Collection<EventListener> = listOf(
            EventListener { actual[0] = true; false },
            EventListener { actual[1] = true; false },
            EventListener { actual[2] = true; true },
            EventListener { actual[3] = true; false },
            EventListener { actual[4] = true; false },
        )

        val listenerChain = BaseListenerChain(chain)
        assertEquals(listenerChain.processEvent(emptyEvent), true)


        assertArrayEquals(expected, actual)
    }
}