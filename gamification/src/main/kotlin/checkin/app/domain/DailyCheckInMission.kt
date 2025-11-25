package checkin.app.domain

import java.time.Instant
import java.time.temporal.ChronoUnit
import kotlin.math.abs

class DailyCheckInMission(val userId: Long, checkIns: List<CheckIn>) {

    private val checkIns: MutableList<CheckIn> = checkIns.toMutableList()

    fun checkIns(): List<CheckIn> = checkIns.toMutableList()

    fun checkIn(date: Instant) = checkIns.add(createCheckIn(date))

    private fun createCheckIn(date: Instant) = when {
        isFirstCheckIn() -> CheckIn.first(date)
        isSameDay(date) -> throw RuntimeException("Invalid check-in")
        alreadyCheckedInForAWeek() || isAfterTwoDays(date) -> CheckIn.restart(date)
        else -> CheckIn.from(checkIns.last(), date)
    }

    private fun isFirstCheckIn() = checkIns.size == 0

    private fun isSameDay(date: Instant) = getAbsDaysBetweenLastCheckInAnd(date) == 0L

    private fun isAfterTwoDays(date: Instant) = getAbsDaysBetweenLastCheckInAnd(date) > 1

    private fun getAbsDaysBetweenLastCheckInAnd(date: Instant): Long {
        val lastCheckIn = checkIns.last()
        val truncatedLastCheckInDate = lastCheckIn.date.truncatedTo(ChronoUnit.DAYS)
        val truncatedCheckInDate = date.truncatedTo(ChronoUnit.DAYS)
        val daysBetween = ChronoUnit.DAYS.between(truncatedLastCheckInDate, truncatedCheckInDate)
        return abs(daysBetween)
    }

    private fun alreadyCheckedInForAWeek() = checkIns.last().streak == 7

    companion object Factory {
        fun start(userId: Long) = DailyCheckInMission(userId, emptyList())
    }
}
