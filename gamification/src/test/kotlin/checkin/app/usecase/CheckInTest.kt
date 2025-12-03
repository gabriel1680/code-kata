package checkin.app.usecase

import checkin.app.domain.DailyCheckInMission
import checkin.app.fixture.MockTimeProvider
import checkin.out.MemoryCheckInRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Duration
import java.time.Instant
import kotlin.test.assertEquals

class CheckInTest {

    companion object Fixture {
        private const val USER_ID = 123L
        private val NOW = Instant.parse("2025-11-15T00:00:00Z")
        private val INPUT = CheckInInput(USER_ID)
    }

    private lateinit var timeProvider: MockTimeProvider
    private lateinit var repository: MemoryCheckInRepository
    private lateinit var checkIn: CheckIn

    @BeforeEach
    fun setUp() {
        repository = MemoryCheckInRepository()
        timeProvider = MockTimeProvider(NOW)
        checkIn = CheckIn(repository, timeProvider)
    }

    private fun assertCheckIns(mission: DailyCheckInMission, total: Int) =
        assertEquals(total, mission.checkIns().size)

    @Test
    fun `first check-in of a user`() {
        checkIn(INPUT)
        val mission = repository.getFor(USER_ID)!!
        assertCheckIns(mission, 1)
    }

    @Test
    fun `second check-in of a user`() {
        val mission = DailyCheckInMission.start(USER_ID)
        mission.checkIn(NOW)
        repository.save(mission)
        val dayAfterFirstCheckIn = NOW.plus(Duration.ofDays(1))
        timeProvider.withInstant(dayAfterFirstCheckIn)
        checkIn(INPUT)
        assertCheckIns(mission, 2)
    }
}
