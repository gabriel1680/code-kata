package checkin.app.domain

import org.gbl.checkin.application.domain.DailyCheckInMission
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.assertThrows
import java.time.Duration
import java.time.Instant
import kotlin.test.Test

internal class DailyCheckInMissionTest {

    companion object Fixture {
        private const val USER_ID: Long = 123
        private val FIRST_DATE: Instant = Instant.parse("2018-11-15T10:15:30.00Z")
    }

    private lateinit var sut: DailyCheckInMission

    fun assertCheckIns(total: Int, checkIns: List<Any>) = assertEquals(total, checkIns.size)

    fun lastCheckIn() = sut.checkIns().last()

    @BeforeEach
    fun setUp() {
        sut = DailyCheckInMission.start(USER_ID)
    }

    @Test
    fun create() {
        sut.checkIn(FIRST_DATE)
        assertCheckIns(1, sut.checkIns())
        assertEquals(USER_ID, sut.userId)
    }

    @Test
    fun firstCheckIn() {
        sut.checkIn(FIRST_DATE)
        val checkIn = lastCheckIn()
        assertCheckIns(1, sut.checkIns())
        assertEquals(1, checkIn.streak)
        assertEquals(50, checkIn.rewards)
    }

    @Nested
    inner class TwoCheckIns {

        @BeforeEach
        fun setUp() {
            sut = DailyCheckInMission.start(USER_ID)
            sut.checkIn(FIRST_DATE)
        }

        @Test
        fun sameDay() {
            assertThrows<RuntimeException> { sut.checkIn(FIRST_DATE) }
            assertCheckIns(1, sut.checkIns())
        }

        @Test
        fun sameDayAfter1Hour() {
            val after1HourSameDay = FIRST_DATE.plus(Duration.ofHours(1))
            assertThrows<RuntimeException> { sut.checkIn(after1HourSameDay) }
            assertCheckIns(1, sut.checkIns())
        }

        @Test
        fun anotherDayAfter1HourStartingAt23PM() {
            sut = DailyCheckInMission.start(USER_ID)
            val date = Instant.parse("2018-11-15T23:59:00.00Z")
            sut.checkIn(date)
            sut.checkIn(date.plus(Duration.ofMinutes(1)))
            val checkIn = lastCheckIn()
            assertEquals(2, checkIn.streak)
            assertEquals(100, checkIn.rewards)
            assertCheckIns(2, sut.checkIns())
        }

        @Test
        fun consecutiveDays() {
            val nextDay = FIRST_DATE.plus(Duration.ofDays(1))
            sut.checkIn(nextDay)
            val checkIn = lastCheckIn()
            assertEquals(2, checkIn.streak)
            assertEquals(100, checkIn.rewards)
            assertCheckIns(2, sut.checkIns())
        }

        @Test
        fun after2DaysCheckIn() {
            val nextDay = FIRST_DATE.plus(Duration.ofDays(2))
            sut.checkIn(nextDay)
            val checkIn = lastCheckIn()
            assertEquals(1, checkIn.streak)
            assertEquals(50, checkIn.rewards)
            assertCheckIns(2, sut.checkIns())
        }
    }

    @Nested
    inner class WeekStreak {

        private lateinit var lastCheckInDate: Instant

        @BeforeEach
        fun setUp() {
            createWithAWeekStreak()
            assertEquals(7, sut.checkIns().size)
            lastCheckInDate = lastCheckIn().date
        }

        private fun createWithAWeekStreak() = createWithAStreak(7)

        private fun createWithAStreak(streak: Int) {
            var lastDate = FIRST_DATE
            for (i in 0..<streak) {
                sut.checkIn(lastDate)
                lastDate = lastDate.plus(Duration.ofDays(1))
            }
        }

        @Test
        fun restartFromTheBeginning() {
            sut.checkIn(lastCheckInDate.plus(Duration.ofDays(1)))
            val checkIn = lastCheckIn()
            assertEquals(1, checkIn.streak)
            assertEquals(50, checkIn.rewards)
        }

        @Test
        fun andInvalidDate() {
            assertThrows<RuntimeException> { sut.checkIn(lastCheckInDate) }
        }
    }
}