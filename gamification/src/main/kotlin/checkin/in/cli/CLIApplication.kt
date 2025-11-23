package org.gbl.checkin.`in`.cli

import org.gbl.checkin.CheckInAPI
import org.gbl.checkin.CheckInCommand
import org.gbl.checkin.GetLastCheckInQuery
import org.gbl.org.gbl.checkin.`in`.cli.ConsoleIO

class CLIApplication(
    private val checkInApi: CheckInAPI,
    private val io: ConsoleIO,
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
        io.println(message)
        checkInApi.getLastCheckIn(GetLastCheckInQuery(userId))?.let {
            io.println(presenter.presentDto(it))
        }
        io.println(presenter.bye())
    }

    private fun parseInput(args: Array<String>): Long? {
        val userId = args[0]
        return userId.toLongOrNull()
    }
}
