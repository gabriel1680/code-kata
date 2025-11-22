package org.gbl.checkin.application.domain

interface CheckInRepository {
    fun getFor(userId: Long): DailyCheckInMission?
    fun save(checkIn: DailyCheckInMission)
}