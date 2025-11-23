package org.gbl.checkin.`in`.cli

import org.gbl.checkin.CheckInAPI
import org.gbl.checkin.CheckInCommand
import org.gbl.checkin.GetLastCheckInQuery

class CliApp(
    private val checkInApi: CheckInAPI,
    private val argsParser: CheckInArgsParser,
    private val io: Console,
    private val presenter: CheckInCliPresenter
) {

    fun run(args: Array<String>) {
        val result = argsParser.parse(args)
        if (result.isFailure) {
            io.println(presenter.error(result.exceptionOrNull()!!))
            return
        }
        val userId = result.getOrThrow()
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
}
