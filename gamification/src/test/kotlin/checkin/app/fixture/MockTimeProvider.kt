package checkin.app.fixture

import org.gbl.checkin.app.service.TimeProvider
import java.time.Instant

class MockTimeProvider(private var now: Instant) : TimeProvider {

    fun withInstant(date: Instant) {
        now = date
    }

    override fun instant() = now
}