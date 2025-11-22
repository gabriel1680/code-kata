package checkin.app.fixture

import java.time.Clock
import java.time.Instant
import java.time.ZoneId

class MockClock(private var now: Instant) : Clock() {

    fun withInstant(date: Instant) {
        now = date
    }

    override fun instant() = now

    override fun withZone(zone: ZoneId?): Clock {
        TODO("Not yet implemented")
    }

    override fun getZone(): ZoneId {
        TODO("Not yet implemented")
    }
}