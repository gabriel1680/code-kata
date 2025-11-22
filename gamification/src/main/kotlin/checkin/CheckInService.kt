package org.gbl.checkin

import org.gbl.checkin.domain.DailyCheckInMission
import java.time.Clock

class CheckInService(private val checkInRepository: CheckInRepository, private val clock: Clock) : CheckInAPI {

    override fun checkIn(command: CheckInCommand) {
        val mission =
            checkInRepository.getFor(command.userId) ?: DailyCheckInMission.start(command.userId)
        mission.checkIn(clock.instant())
        checkInRepository.save(mission)
    }
}

data class CheckInCommand(val userId: Long)

interface CheckInRepository {
    fun getFor(userId: Long): DailyCheckInMission?
    fun save(checkIn: DailyCheckInMission)
}
