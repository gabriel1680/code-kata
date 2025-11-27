package checkin

import checkin.app.service.CheckInQueryService
import checkin.app.usecase.CheckIn
import checkin.app.usecase.CheckInInput

class CheckInApiImpl(private val checkInUseCase: CheckIn, private val queryService: CheckInQueryService) : CheckInAPI {

    override suspend fun checkIn(command: CheckInCommand) = checkInUseCase(CheckInInput(command.userId))

    override suspend fun getLastCheckIn(query: GetLastCheckInQuery) = queryService.getLastCheckInFor(query.userId)
}
