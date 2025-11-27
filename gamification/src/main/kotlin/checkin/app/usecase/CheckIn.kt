package org.gbl.checkin.app.usecase

import checkin.app.domain.CheckInMissionRepository
import checkin.app.domain.DailyCheckInMission
import org.gbl.checkin.app.service.TimeProvider

class CheckIn(private val checkInRepository: CheckInMissionRepository, private val timeProvider: TimeProvider) {

    suspend operator fun invoke(input: CheckInInput) {
        val mission =
            checkInRepository.getFor(input.userId) ?: DailyCheckInMission.start(input.userId)
        mission.checkIn(timeProvider.instant())
        checkInRepository.save(mission)
    }
}

data class CheckInInput(val userId: Long)
