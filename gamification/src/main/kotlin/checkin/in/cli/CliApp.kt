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

    suspend fun run(args: Array<String>) {
        argsParser.parse(args).onSuccess { executeCheckInFor(it) }
            .onFailure { io.println(presenter.error(it)) }
    }

    private suspend fun executeCheckInFor(userId: Long) {
        val message = try {
            checkInApi.checkIn(CheckInCommand(userId))
            presenter.success()
        } catch (e: Exception) {
            presenter.error(e)
        }
        io.println(message)
        checkInApi.getLastCheckIn(GetLastCheckInQuery(userId))?.let {
            io.println(presenter.presentDto(it))
        }.also {
            io.println(presenter.bye())
        }
    }
}
