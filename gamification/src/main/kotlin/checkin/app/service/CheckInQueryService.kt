package checkin.app.service

import checkin.CheckInDTO

interface CheckInQueryService {
    fun getLastCheckInFor(userId: Long): CheckInDTO?
}