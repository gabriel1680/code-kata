package checkin

import java.time.Instant

interface CheckInAPI {
    fun checkIn(command: CheckInCommand)
    fun getLastCheckIn(query: GetLastCheckInQuery): CheckInDTO?
}

data class CheckInCommand(val userId: Long)

data class GetLastCheckInQuery(val userId: Long)

data class CheckInDTO(val date: Instant, val streak: Int, val reward: Long)
