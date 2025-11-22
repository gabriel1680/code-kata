package org.gbl.checkin.application

import org.gbl.checkin.CheckInDTO

interface CheckInQueryService {
    fun getLastCheckInFor(userId: Long): CheckInDTO?
}