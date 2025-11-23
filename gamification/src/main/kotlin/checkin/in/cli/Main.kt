package org.gbl.checkin.`in`.cli

import org.gbl.checkin.CheckInApiImpl
import org.gbl.checkin.app.usecase.CheckIn
import org.gbl.checkin.out.MemoryCheckInRepository
import java.time.Instant

fun main(args: Array<String>) {
    val repository = MemoryCheckInRepository()
    val checkInUseCase = CheckIn(repository, Instant::now)
    val api = CheckInApiImpl(checkInUseCase, repository)
    val presenter = CheckInCliPresenterImpl()
    val cli = CLIApplication(api, ::println, presenter)
    cli.run(args)
}