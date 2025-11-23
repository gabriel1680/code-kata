package org.gbl.checkin.`in`.cli

import org.gbl.checkin.CheckInDTO
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class CheckInCliPresenter {

    fun success() = "Check-in accepted"

    fun presentDto(dto: CheckInDTO): String {
        return """
            Last Check-in Details:
            Date: ${formatDate(dto)}
            Streak: ${dto.streak}
            Reward: ${dto.reward} points
        """.trimIndent()
    }

    private fun formatDate(dto: CheckInDTO): String? {
        val localDate = dto.date.atZone(ZoneOffset.UTC)
        val pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val formattedDate = pattern.format(localDate)
        return formattedDate
    }

    fun bye() = "Bye!"

    fun error(e: Throwable) = "Check-in Error: ${e.message}"
}