package checkin.app

import org.gbl.checkin.CheckInDTO

interface CheckInQueryService {
    fun getLastCheckInFor(userId: Long): CheckInDTO?
}