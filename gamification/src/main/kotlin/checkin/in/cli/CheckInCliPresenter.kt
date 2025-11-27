package checkin.`in`.cli

import checkin.CheckInDTO
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

open class CheckInCliPresenter {

    companion object {
        private const val DATE_FORMAT = "dd/MM/yyyy"
    }

    open fun success() = "Check-in accepted"

    open fun presentDto(dto: CheckInDTO) = """
            Last Check-in Details:
            Date: ${formatDate(dto)}
            Streak: ${dto.streak}
            Reward: ${dto.reward} points
        """.trimIndent()

    private fun formatDate(dto: CheckInDTO): String? = with(dto) {
        date.atZone(ZoneOffset.UTC).let {
            return DateTimeFormatter.ofPattern(DATE_FORMAT).format(it)
        }
    }

    open fun bye() = "Bye!"

    open fun error(e: Throwable) = "Check-in Error: ${e.message}"
}