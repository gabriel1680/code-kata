package checkin.app.service

import checkin.CheckInDTO

interface CheckInQueryService {
    suspend fun getLastCheckInFor(userId: Long): CheckInDTO?
}