package io.homeassistant.companion.android.sensors

import android.net.wifi.WifiManager
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class WifiSyncTest {
    @Test
    fun `wifi connection changes can skip sensor sync when network sensors are disabled`() {
        val method = SensorReceiver::class.java.getDeclaredMethod("getSkippableActions")
        method.isAccessible = true
        @Suppress("UNCHECKED_CAST")
        val actions = method.invoke(SensorReceiver()) as Map<String, List<String>>
        val sensors = actions[WifiManager.NETWORK_STATE_CHANGED_ACTION]
        assertTrue(sensors != null && sensors.isNotEmpty())
        assertTrue(sensors!!.contains("wifi_connection"))
    }
}
