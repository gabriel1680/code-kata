package org.gbl.checkin.`in`.cli

import org.gbl.checkin.CheckInDTO
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class CheckInCliPresenterImpl : CheckInCliPresenter {

    override fun success() = "Check-in accepted"

    override fun presentDto(dto: CheckInDTO): String {
        return """
            Last Check-in Details:
            Date: ${formatDate(dto)}
            Streak: ${dto.streak}
            Reward: ${dto.reward} points
        """.trimIndent()
    }

    private fun formatDate(dto: CheckInDTO): String? {
        val zone = ZoneId.systemDefault()
        val localDate = dto.date.atZone(zone)
        val pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val formattedDate = pattern.format(localDate)
        return formattedDate
    }

    override fun bye() = "Bye!"

    override fun error(e: Exception): String {
        return """
            Check-in Error: ${e.message}
        """.trimIndent()
    }
}