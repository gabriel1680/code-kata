package checkin.`in`.cli

import checkin.CheckInApiImpl
import checkin.app.usecase.CheckIn
import checkin.out.MemoryCheckInRepository
import java.time.Instant

fun main(args: Array<String>) {
    val repository = MemoryCheckInRepository()
    val checkInUseCase = CheckIn(repository, Instant::now)
    val api = CheckInApiImpl(checkInUseCase, repository)
    val presenter = CheckInCliPresenter()
    val parser = CheckInArgsParser()
    val cli = CliApp(api, parser, ::println, presenter)
    cli.run(args)
}