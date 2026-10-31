package io.homeassistant.companion.android.complications

import kotlin.time.Clock
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Instant
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ComplicationRefreshGateTest {
    @Test
    fun `screen wake refreshes at most once per five minutes`() = runTest {
        var now = Instant.fromEpochMilliseconds(1793462400000L)
        val gate = ComplicationRefreshGate(object : Clock {
            override fun now() = now
        })
        assertTrue(gate.shouldRefresh())
        assertFalse(gate.shouldRefresh())
        now += 4.minutes
        assertFalse(gate.shouldRefresh())
        now += 1.minutes
        assertTrue(gate.shouldRefresh())
    }
}
