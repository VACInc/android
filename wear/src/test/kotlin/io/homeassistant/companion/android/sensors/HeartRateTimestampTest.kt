package io.homeassistant.companion.android.sensors

import org.junit.Assert.assertEquals
import org.junit.Test

class HeartRateTimestampTest {
    @Test
    fun `heart rate listener timestamp preserves epoch milliseconds`() {
        val field = HeartRateSensorManager::class.java.getDeclaredField("listenerLastRegistered")
        field.isAccessible = true
        val epochMillis = 1793462400000L
        field.setLong(null, epochMillis)
        assertEquals(epochMillis, field.getLong(null))
        field.setLong(null, 0L)
    }
}
