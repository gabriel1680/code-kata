package org.gbl.checkin

import org.gbl.checkin.app.usecase.CheckIn
import org.gbl.checkin.app.usecase.CheckInInput
import org.gbl.checkin.application.CheckInQueryService

class CheckInApiImpl(private val checkInUseCase: CheckIn, private val queryService: CheckInQueryService) : CheckInAPI {

    override fun checkIn(command: CheckInCommand) = checkInUseCase(CheckInInput(command.userId))

    override fun getLastCheckIn(query: GetLastCheckInQuery) = queryService.getLastCheckInFor(query.userId)
}
