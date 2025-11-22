package org.gbl.checkin.app.usecase

import org.gbl.checkin.application.domain.CheckInMissionRepository
import org.gbl.checkin.application.domain.DailyCheckInMission
import java.time.Clock

class CheckIn(private val checkInRepository: CheckInMissionRepository, private val clock: Clock) {

    operator fun invoke(input: CheckInInput) {
        val mission =
            checkInRepository.getFor(input.userId) ?: DailyCheckInMission.start(input.userId)
        mission.checkIn(clock.instant())
        checkInRepository.save(mission)
    }
}

data class CheckInInput(val userId: Long)
