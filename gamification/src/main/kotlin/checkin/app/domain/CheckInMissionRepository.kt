package checkin.app.domain

interface CheckInMissionRepository {
    fun getFor(userId: Long): DailyCheckInMission?
    fun save(mission: DailyCheckInMission)
}