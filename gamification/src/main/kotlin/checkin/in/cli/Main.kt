package org.gbl.checkin.`in`.cli

import kotlinx.coroutines.runBlocking
import org.gbl.checkin.CheckInApiImpl
import org.gbl.checkin.app.usecase.CheckIn
import org.gbl.checkin.out.MemoryCheckInRepository
import java.time.Instant

fun main(args: Array<String>) = runBlocking {
    val repository = MemoryCheckInRepository()
    val checkInUseCase = CheckIn(repository, Instant::now)
    val api = CheckInApiImpl(checkInUseCase, repository)
    val presenter = CheckInCliPresenter()
    val parser = CheckInArgsParser()
    val cli = CliApp(api, parser, ::println, presenter)
    cli.run(args)
}