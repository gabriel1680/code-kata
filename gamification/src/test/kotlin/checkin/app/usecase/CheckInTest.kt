package checkin.app.usecase

import org.gbl.checkin.app.usecase.CheckInInput
import org.gbl.checkin.application.domain.CheckIn
import org.gbl.checkin.application.domain.CheckInMissionRepository
import org.gbl.checkin.application.domain.DailyCheckInMission
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import java.time.Clock
import java.time.Duration
import java.time.Instant

class CheckInTest {

    companion object Fixture {
        private const val USER_ID = 123L
        private val NOW = Instant.parse("2025-11-15T00:00:00Z")
        private val A_COMMAND = CheckInInput(USER_ID)
    }

    @Test
    fun `first check-in of a user`() {
        val repository = mock<CheckInMissionRepository> {
            on { getFor(any()) } doReturn null
        }
        val clock = mock<Clock> {
            on { instant() } doReturn NOW
        }
        val act = org.gbl.checkin.app.usecase.CheckIn(repository, clock)
        act(A_COMMAND)
        verify(repository).save(any<DailyCheckInMission>())
    }

    @Test
    fun `second check-in of a user`() {
        val mission = DailyCheckInMission(USER_ID, listOf(CheckIn.first(NOW)))
        val repository = mock<CheckInMissionRepository> {
            on { getFor(any()) } doReturn mission
        }
        val dayAfterFirstCheckIn = NOW.plus(Duration.ofDays(1))
        val clock = mock<Clock> {
            on { instant() } doReturn dayAfterFirstCheckIn
        }
        val act = org.gbl.checkin.app.usecase.CheckIn(repository, clock)
        act(A_COMMAND)
        val captor = argumentCaptor<DailyCheckInMission> { }
        verify(repository).save(captor.capture())
        kotlin.test.assertEquals(2, captor.firstValue.checkIns().size)
    }
}