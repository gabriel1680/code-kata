package org.gbl.checkin.`in`.cli

import org.gbl.checkin.CheckInApiImpl
import org.gbl.checkin.app.usecase.CheckIn
import org.gbl.checkin.out.MemoryCheckInRepository
import java.time.Clock

fun main(args: Array<String>) {
    val repository = MemoryCheckInRepository()
    val checkInUseCase = CheckIn(repository, Clock.systemUTC())
    val api = CheckInApiImpl(checkInUseCase, repository)
    val presenter = SystemCheckInCliPresenter()
    val cli = CLI(api, ::println, presenter)
    cli.run(args)
}