package checkin.`in`.cli

import kotlinx.coroutines.runBlocking
import checkin.CheckInAPI
import checkin.CheckInDTO
import checkin.app.service.TimeProvider
import org.junit.jupiter.api.BeforeEach
import org.mockito.kotlin.*
import java.time.Instant
import kotlin.test.Test

class CliAppTest {

    private lateinit var sut: CliApp
    private lateinit var io: Console
    private lateinit var api: CheckInAPI
    private lateinit var timeProvider: TimeProvider
    private lateinit var presenter: CheckInCliPresenter
    private lateinit var argsParser: CheckInArgsParser

    companion object Fixture {
        private val ARGS = arrayOf("123")
        private val INSTANT = Instant.parse("2025-11-15T00:00:00Z")
        private const val USER_ID = 123L
    }

    inner class DummyPresenter : CheckInCliPresenter() {

        override fun success() = "success"

        override fun presentDto(dto: CheckInDTO) = "presentDto"

        override fun bye() = "bye"

        override fun error(e: Throwable) = "error"

    }

    private fun dtoFor(date: Instant) = CheckInDTO(date, 1, 50)

    @BeforeEach
    fun setUp() {
        timeProvider = mock()
        api = mock()
        presenter = DummyPresenter()
        io = mock()
        argsParser = mock()
        sut = CliApp(api, argsParser, io, presenter)
    }

    @Test
    fun checkIn() = runBlocking {
        whenever(timeProvider.instant()).thenReturn(INSTANT)
        whenever(api.getLastCheckIn(any())).thenReturn(dtoFor(INSTANT))
        whenever(argsParser.parse(any())).thenReturn(Result.success(USER_ID))
        sut.run(ARGS)
        val inOrder = inOrder(io)
        inOrder.verify(io).println("success")
        inOrder.verify(io).println("presentDto")
        inOrder.verify(io).println("bye")
    }

    @Test
    fun inputParsingError() = runBlocking {
        whenever(argsParser.parse(any())).thenReturn(Result.failure(Exception("test error")))
        sut.run(emptyArray())
        verify(io, times(1)).println("error")
    }

    @Test
    fun checkInError() = runBlocking {
        whenever(timeProvider.instant()).thenReturn(INSTANT)
        doThrow(RuntimeException::class).whenever(api).checkIn(any())
        whenever(api.getLastCheckIn(any())).thenReturn(dtoFor(INSTANT))
        whenever(argsParser.parse(any())).thenReturn(Result.success(USER_ID))
        sut.run(ARGS)
        val inOrder = inOrder(io)
        inOrder.verify(io).println("error")
        inOrder.verify(io).println("presentDto")
        inOrder.verify(io).println("bye")
    }

    @Test
    fun checkInErrorWithoutLastCheckIn() = runBlocking {
        whenever(timeProvider.instant()).thenReturn(INSTANT)
        doThrow(RuntimeException::class).whenever(api).checkIn(any())
        whenever(api.getLastCheckIn(any())).thenReturn(null)
        whenever(argsParser.parse(any())).thenReturn(Result.success(USER_ID))
        sut.run(ARGS)
        val inOrder = inOrder(io)
        inOrder.verify(io).println("error")
        inOrder.verify(io).println("bye")
    }
}