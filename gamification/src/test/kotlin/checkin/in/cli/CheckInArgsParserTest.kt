package checkin.`in`.cli

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CheckInArgsParserTest {

    private lateinit var sut: CheckInArgsParser

    @BeforeEach
    fun setUp() {
        sut = CheckInArgsParser()
    }

    @Test
    fun fewArguments() {
        val result = sut.parse(emptyArray())
        assertTrue(result.isFailure)
        val throwable = result.exceptionOrNull()!!
        assertEquals("Invalid arguments, too few arguments", throwable.message)
    }

    @Test
    fun invalidUserId() {
        val result = sut.parse(arrayOf("123.5"))
        assertTrue(result.isFailure)
        val throwable = result.exceptionOrNull()!!
        assertEquals("Invalid userId, it need to be a non negative integer", throwable.message)
    }

    @Test
    fun success() {
        val result = sut.parse(arrayOf("123"))
        assertTrue(result.isSuccess)
        assertEquals(123, result.getOrThrow())
    }
}