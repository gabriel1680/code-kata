package org.gbl.checkin.app.service

import org.gbl.checkin.CheckInDTO

interface CheckInQueryService {
    suspend fun getLastCheckInFor(userId: Long): CheckInDTO?
}