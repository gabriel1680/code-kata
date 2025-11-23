package org.gbl.checkin.`in`.cli

import org.gbl.checkin.CheckInAPI
import org.gbl.checkin.CheckInCommand
import org.gbl.checkin.CheckInDTO
import org.gbl.checkin.GetLastCheckInQuery

class CLI(
    private val checkInApi: CheckInAPI,
    private val printLine: (s: Any) -> Unit,
    private val presenter: CheckInCliPresenter
) {
    fun run(args: Array<String>) {
        require(args.isNotEmpty()) { "Invalid arguments" }
        val userId: Long? = parseInput(args)
        require(userId != null) { "Invalid userId value" }
        val message = try {
            checkInApi.checkIn(CheckInCommand(userId))
            presenter.success()
        } catch (e: Exception) {
            presenter.error(e)
        }
        printLine(message)
        checkInApi.getLastCheckIn(GetLastCheckInQuery(userId))?.let {
            printLine(presenter.presentDto(it))
        }
        printLine(presenter.bye())
    }

    private fun parseInput(args: Array<String>): Long? {
        val userId =args[0]
        return userId.toLongOrNull()
    }
}

interface CheckInCliPresenter {
    fun success(): String
    fun error(e: Exception): String
    fun presentDto(dto: CheckInDTO): String
    fun bye(): String
}

class SystemCheckInCliPresenter : CheckInCliPresenter {

    override fun success() = "Check-in accepted"

    override fun presentDto(dto: CheckInDTO): String {
        return """
            Last Check-in Details:
            Date: ${dto.date}
            Streak: ${dto.streak}
            Reward: ${dto.reward}
        """.trimIndent()
    }

    override fun bye() = "Bye!"

    override fun error(e: Exception): String {
        return """
            Check-in Error: ${e.message}
        """.trimIndent()
    }
}