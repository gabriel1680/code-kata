package org.gbl.checkin.out

import org.gbl.checkin.CheckInDTO
import org.gbl.checkin.application.CheckInQueryService
import org.gbl.checkin.application.domain.CheckInMissionRepository
import org.gbl.checkin.application.domain.DailyCheckInMission

class MemoryCheckInRepository : CheckInMissionRepository, CheckInQueryService {

    private val missions = mutableMapOf<Long, DailyCheckInMission>()

    override fun getFor(userId: Long) = missions[userId]

    override fun save(mission: DailyCheckInMission) {
        missions[mission.userId] = mission
    }

    override fun getLastCheckInFor(userId: Long): CheckInDTO? {
        return missions[userId]?.checkIns()?.last()?.let { checkIn ->
            return CheckInDTO(checkIn.date, checkIn.streak, checkIn.rewards)
        }
    }
}