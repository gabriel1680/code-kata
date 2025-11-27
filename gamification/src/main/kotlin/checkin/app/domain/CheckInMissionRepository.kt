package checkin.app.domain

interface CheckInMissionRepository {
    suspend fun getFor(userId: Long): DailyCheckInMission?
    suspend fun save(mission: DailyCheckInMission)
}