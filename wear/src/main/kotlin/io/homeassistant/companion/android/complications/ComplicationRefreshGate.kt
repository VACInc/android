package io.homeassistant.companion.android.complications

import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Clock
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Instant
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/** Limits automatic refreshes across receiver instances; explicit taps bypass this gate. */
@Singleton
class ComplicationRefreshGate @Inject constructor(private val clock: Clock) {
    private val mutex = Mutex()
    private var lastRefresh: Instant? = null

    suspend fun shouldRefresh(): Boolean = mutex.withLock {
        val now = clock.now()
        if (lastRefresh?.let { now - it < 5.minutes } == true) return@withLock false
        lastRefresh = now
        true
    }
}
