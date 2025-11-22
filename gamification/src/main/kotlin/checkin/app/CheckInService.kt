package org.gbl.checkin.application

import org.gbl.checkin.CheckInAPI
import org.gbl.checkin.application.domain.CheckInRepository
import org.gbl.checkin.application.domain.DailyCheckInMission
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
