package org.gbl.checkin.app.service

import org.gbl.checkin.CheckInDTO

interface CheckInQueryService {
    fun getLastCheckInFor(userId: Long): CheckInDTO?
}