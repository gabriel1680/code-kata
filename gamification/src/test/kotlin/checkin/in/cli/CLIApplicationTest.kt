package checkin.`in`.cli

import org.gbl.checkin.CheckInAPI
import org.gbl.checkin.CheckInDTO
import org.gbl.checkin.`in`.cli.CLIApplication
import org.gbl.checkin.`in`.cli.CheckInCliPresenter
import org.gbl.org.gbl.checkin.`in`.cli.ConsoleIO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.any
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.time.Instant
import kotlin.test.assertEquals

class CLIApplicationTest {

    private lateinit var sut: CLIApplication
    private lateinit var api: CheckInAPI
    private lateinit var io: SpyConsoleIO
    private lateinit var presenter: DummyCLIPresenter

    internal class SpyConsoleIO : ConsoleIO {
        private val printed:  MutableList<Any> = mutableListOf()

        override fun println(s: Any) {
            printed.add(s)
        }

        fun printed(): List<Any> = printed.toList()
    }

    internal class DummyCLIPresenter : CheckInCliPresenter {
        override fun success() = "success"

        override fun error(e: Exception) = "error"

        override fun presentDto(dto: CheckInDTO) = "presentDto"

        override fun bye() = "bye"
    }

    @BeforeEach
    fun setUp() {
        api = mock<CheckInAPI>()
        io = SpyConsoleIO()
        presenter = DummyCLIPresenter()
        sut = CLIApplication(api, io, presenter)
    }

    private fun assertPrintedValues(vararg values: String) {
        val printedValues = io.printed()
        for (i in 0..<printedValues.size) {
            assertEquals(values[i], printedValues[i])
        }
    }

    @Test
    fun invalidArgsLength() {
        assertThrows<RuntimeException> { sut.run(emptyArray()) }
    }

    @Test
    fun invalidUserId() {
        assertThrows<RuntimeException> { sut.run(arrayOf("123.5")) }
    }

    companion object Fixture {
        private val ARGS = arrayOf("123")
    }

    @Test
    fun checkInSuccess() {
        whenever(api.getLastCheckIn(any())).thenReturn(CheckInDTO(Instant.now(), 1, 50))
        sut.run(ARGS)
        assertPrintedValues("success", "presentDto", "bye")
    }

    @Test
    fun checkInError() {
        doThrow(RuntimeException("some random error")).`when`(api).checkIn(any())
        sut.run(ARGS)
        assertPrintedValues("error", "bye")
    }
}