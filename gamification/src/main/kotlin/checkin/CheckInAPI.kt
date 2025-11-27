package org.gbl.checkin

import java.time.Instant

interface CheckInAPI {
    suspend fun checkIn(command: CheckInCommand)
    suspend fun getLastCheckIn(query: GetLastCheckInQuery): CheckInDTO?
}

data class CheckInCommand(val userId: Long)

data class GetLastCheckInQuery(val userId: Long)

data class CheckInDTO(val date: Instant, val streak: Int, val reward: Long)
