package org.gbl.checkin.out

import checkin.app.domain.CheckInMissionRepository
import checkin.app.domain.DailyCheckInMission
import org.gbl.checkin.CheckInDTO
import org.gbl.checkin.app.service.CheckInQueryService

class MemoryCheckInRepository : CheckInMissionRepository, CheckInQueryService {

    private val missions = mutableMapOf<Long, DailyCheckInMission>()

    override suspend fun getFor(userId: Long) = missions[userId]

    override suspend fun save(mission: DailyCheckInMission) = run { missions[mission.userId] = mission }

    override suspend fun getLastCheckInFor(userId: Long): CheckInDTO? {
        return missions[userId]?.checkIns()?.last()?.let { checkIn ->
            return CheckInDTO(checkIn.date, checkIn.streak, checkIn.rewards)
        }
    }
}