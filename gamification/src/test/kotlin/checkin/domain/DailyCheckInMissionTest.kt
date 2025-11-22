package checkin.domain

import org.gbl.checkin.domain.CheckIn
import org.gbl.checkin.domain.DailyCheckInMission
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

    fun assertCheckIns(total: Int, checkIns: List<Any>) {
        assertEquals(total, checkIns.size)
    }

    @BeforeEach
    fun setUp() {
        sut = DailyCheckInMission(USER_ID, emptyList())
    }

    @Test
    fun create() {
        sut.checkIn(FIRST_DATE)
        assertCheckIns(1, sut.checkIns())
        assertEquals(USER_ID, sut.userId)
    }

    @Test
    fun firstCheckIn() {
        val checkIn = sut.checkIn(FIRST_DATE)
        assertCheckIns(1, sut.checkIns())
        assertEquals(1, checkIn.streak)
        assertEquals(50, checkIn.rewards)
    }

    @Nested
    inner class TwoCheckIns {

        private lateinit var sut: DailyCheckInMission

        @BeforeEach
        fun setUp() {
            sut = DailyCheckInMission(USER_ID, listOf(CheckIn.first(FIRST_DATE)))
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
            val date = Instant.parse("2018-11-15T23:59:00.00Z")
            sut = DailyCheckInMission(USER_ID, listOf(CheckIn.first(date)))
            val checkIn = sut.checkIn(date.plus(Duration.ofMinutes(1)))
            assertEquals(2, checkIn.streak)
            assertEquals(100, checkIn.rewards)
            assertCheckIns(2, sut.checkIns())
        }

        @Test
        fun consecutiveDays() {
            val nextDay = FIRST_DATE.plus(Duration.ofDays(1))
            val checkIn = sut.checkIn(nextDay)
            assertEquals(2, checkIn.streak)
            assertEquals(100, checkIn.rewards)
            assertCheckIns(2, sut.checkIns())
        }
    }

    @Nested
    inner class WeekStreak {

        private lateinit var lastCheckInDate: Instant

        @BeforeEach
        fun setUp() {
            val checkIns = createWithAWeekStreak()
            sut = DailyCheckInMission(USER_ID, checkIns)
            assertEquals(7, sut.checkIns().size)
            lastCheckInDate = checkIns.last().date
        }

        private fun createWithAWeekStreak() = createWithAStreak(6)

        private fun createWithAStreak(streak: Int): List<CheckIn> {
            var lastDate = FIRST_DATE
            var lastCheckIn = CheckIn.first(lastDate)
            val previousCheckIns = mutableListOf(lastCheckIn)
            for (i in 0..<streak) {
                lastDate = FIRST_DATE.plus(Duration.ofDays(1))
                lastCheckIn = CheckIn.from(lastCheckIn, lastDate)
                previousCheckIns.add(lastCheckIn)
            }
            return previousCheckIns
        }

        @Test
        fun restartFromTheBeginning() {
            val checkIn = sut.checkIn(lastCheckInDate.plus(Duration.ofDays(1)))
            assertEquals(1, checkIn.streak)
            assertEquals(50, checkIn.rewards)
        }

        @Test
        fun andInvalidDate() {
            assertThrows<RuntimeException> { sut.checkIn(lastCheckInDate) }
        }
    }
}