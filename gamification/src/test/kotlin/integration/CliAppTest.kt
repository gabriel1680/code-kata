package integration

import kotlinx.coroutines.runBlocking
import checkin.CheckInApiImpl
import checkin.app.service.TimeProvider
import checkin.app.usecase.CheckIn
import checkin.`in`.cli.CliApp
import checkin.`in`.cli.CheckInCliPresenter
import checkin.out.MemoryCheckInRepository
import checkin.`in`.cli.CheckInArgsParser
import checkin.`in`.cli.Console
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.time.Instant

class CliAppTest {

    private lateinit var sut: CliApp
    private lateinit var io: Console
    private lateinit var timeProvider: TimeProvider

    companion object Fixture {
        private val ARGS = arrayOf("123")
        private val INSTANT = Instant.parse("2025-11-15T00:00:00Z")
    }

    @BeforeEach
    fun setUp() {
        timeProvider = mock()
        val repository = MemoryCheckInRepository()
        val checkInUseCase = CheckIn(repository, timeProvider)
        val api = CheckInApiImpl(checkInUseCase, repository)
        val presenter = CheckInCliPresenter()
        io = mock()
        val parser = CheckInArgsParser()
        sut = CliApp(api, parser, io, presenter)
    }

    @Test
    fun checkIn() = runBlocking {
        whenever(timeProvider.instant()).thenReturn(INSTANT)
        sut.run(ARGS)
        val inOrder = inOrder(io)
        inOrder.verify(io).println("Check-in accepted")
        inOrder.verify(io).println("Last Check-in Details:\nDate: 15/11/2025\nStreak: 1\nReward: 50 points")
        inOrder.verify(io).println("Bye!")
    }

    @Test
    fun inputParsingError() = runBlocking {
        sut.run(emptyArray())
        val inOrder = inOrder(io)
        inOrder.verify(io).println("Check-in Error: Invalid arguments, too few arguments")
    }
}