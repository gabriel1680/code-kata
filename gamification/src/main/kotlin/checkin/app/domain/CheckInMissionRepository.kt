package org.gbl.checkin.application.domain

interface CheckInMissionRepository {
    fun getFor(userId: Long): DailyCheckInMission?
    fun save(mission: DailyCheckInMission)
}