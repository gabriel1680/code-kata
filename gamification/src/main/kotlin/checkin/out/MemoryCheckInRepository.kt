package org.gbl.checkin.out

import org.gbl.checkin.application.domain.CheckInMissionRepository
import org.gbl.checkin.application.domain.DailyCheckInMission

class MemoryCheckInRepository : CheckInMissionRepository {
    private val missions = mutableMapOf<Long, DailyCheckInMission>()

    override fun getFor(userId: Long) = missions.get(userId)

    override fun save(mission: DailyCheckInMission) {
        missions.put(mission.userId, mission)
    }
}