package checkin.app.usecase

import checkin.app.fixture.MockClock
import org.gbl.checkin.app.usecase.CheckIn
import org.gbl.checkin.app.usecase.CheckInInput
import org.gbl.checkin.application.domain.DailyCheckInMission
import org.gbl.checkin.out.MemoryCheckInRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import java.time.Duration
import java.time.Instant
import kotlin.test.assertEquals

class CheckInTest {

    companion object Fixture {
        private const val USER_ID = 123L
        private val NOW = Instant.parse("2025-11-15T00:00:00Z")
        private val INPUT = CheckInInput(USER_ID)
    }

    private lateinit var clock: MockClock
    private lateinit var repository: MemoryCheckInRepository
    private lateinit var checkIn: CheckIn

    @BeforeEach
    fun setUp() {
        repository = MemoryCheckInRepository()
        clock = MockClock(NOW)
        checkIn = CheckIn(repository, clock)
    }

    @Test
    fun `first check-in of a user`() {
        checkIn(INPUT)
       assertEquals(1, repository.getFor(USER_ID)!!.checkIns().size)
    }

    @Test
    fun `second check-in of a user`() {
        val mission = DailyCheckInMission.start(USER_ID)
        mission.checkIn(NOW)
        repository.save(mission)
        val dayAfterFirstCheckIn = NOW.plus(Duration.ofDays(1))
        clock.withInstant(dayAfterFirstCheckIn)
        checkIn(INPUT)
        assertEquals(2, mission.checkIns().size)
    }
}
