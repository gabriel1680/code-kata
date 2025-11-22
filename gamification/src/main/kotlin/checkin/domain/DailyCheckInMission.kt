package org.gbl.checkin.domain

import java.time.Instant
import java.time.temporal.ChronoUnit

class DailyCheckInMission(val userId: Long, checkIns: List<CheckIn>) {

    private val checkIns: MutableList<CheckIn> = checkIns.toMutableList()

    fun checkIn(date: Instant): CheckIn {
        val checkIn = createCheckIn(date)
        checkIns.add(checkIn)
        return checkIn
    }

    fun checkIns(): List<CheckIn> = checkIns.toMutableList()

    private fun createCheckIn(date: Instant): CheckIn {
        return when {
            isFirstCheckIn() -> CheckIn.create(date)
            isSameDay(date) -> throw RuntimeException("Invalid check-in")
            alreadyCheckedInForAWeek() -> CheckIn.create(date)
            else -> CheckIn.from(checkIns.last(), date)
        }
    }

    private fun isFirstCheckIn() = checkIns.size == 0

    private fun isSameDay(date: Instant): Boolean {
        val lastCheckIn = checkIns.last()
        val truncatedLastCheckInDate = lastCheckIn.date.truncatedTo(ChronoUnit.DAYS)
        val truncatedCheckInDate = date.truncatedTo(ChronoUnit.DAYS)
        return truncatedLastCheckInDate.compareTo(truncatedCheckInDate) == 0
    }

    private fun alreadyCheckedInForAWeek() = checkIns.last().streak == 7
}
