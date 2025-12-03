package checkin.out

import checkin.CheckInDTO
import checkin.app.domain.CheckInMissionRepository
import checkin.app.domain.DailyCheckInMission
import checkin.app.service.CheckInQueryService

class MemoryCheckInRepository : CheckInMissionRepository, CheckInQueryService {

    private val missions = mutableMapOf<Long, DailyCheckInMission>()

    override fun getFor(userId: Long) = missions[userId]

    override fun save(mission: DailyCheckInMission) = run { missions[mission.userId] = mission }

    override fun getLastCheckInFor(userId: Long): CheckInDTO? {
        return missions[userId]?.checkIns()?.last()?.let { checkIn ->
            return CheckInDTO(checkIn.date, checkIn.streak, checkIn.rewards)
        }
    }
}