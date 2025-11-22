package checkin.app

import org.gbl.checkin.application.CheckInCommand
import org.gbl.checkin.application.domain.CheckInRepository
import org.gbl.checkin.application.CheckInService
import org.gbl.checkin.application.domain.CheckIn
import org.gbl.checkin.application.domain.DailyCheckInMission
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import java.time.Clock
import java.time.Duration
import java.time.Instant
import kotlin.test.assertEquals

class CheckInServiceTest {

    companion object Fixture {
        private const val USER_ID = 123L
        private val NOW = Instant.parse("2025-11-15T00:00:00Z")
        private val A_COMMAND = CheckInCommand(USER_ID)
    }

    @Test
    fun firstCheckIn() {
        val repository = mock<CheckInRepository> {
            on { getFor(any()) } doReturn null
        }
        val clock = mock<Clock> {
            on { instant() } doReturn NOW
        }
        val sut = CheckInService(repository, clock)
        sut.checkIn(A_COMMAND)
        verify(repository).save(any<DailyCheckInMission>())
    }

    @Test
    fun secondCheckIn() {
        val mission = DailyCheckInMission(USER_ID, listOf(CheckIn.first(NOW)))
        val repository = mock<CheckInRepository> {
            on { getFor(any()) } doReturn mission
        }
        val dayAfterFirstCheckIn = NOW.plus(Duration.ofDays(1))
        val clock = mock<Clock> {
            on { instant() } doReturn dayAfterFirstCheckIn
        }
        val sut = CheckInService(repository, clock)
        sut.checkIn(A_COMMAND)
        val captor = argumentCaptor<DailyCheckInMission> { }
        verify(repository).save(captor.capture())
        assertEquals(2, captor.firstValue.checkIns().size)
    }
}