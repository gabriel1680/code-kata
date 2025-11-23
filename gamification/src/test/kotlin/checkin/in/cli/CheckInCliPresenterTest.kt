package checkin.`in`.cli

import org.gbl.checkin.CheckInDTO
import org.gbl.checkin.`in`.cli.CheckInCliPresenter
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Instant
import kotlin.test.assertEquals

class CheckInCliPresenterTest {

    private lateinit var sut: CheckInCliPresenter

    @BeforeEach
    fun setUp() {
        sut = CheckInCliPresenter()
    }

    @Test
    fun success() {
        assertEquals("Check-in accepted", sut.success())
    }

    @Test
    fun bye() {
        assertEquals("Bye!", sut.bye())
    }

    @Test
    fun error() {
        val e = Exception("test exception")
        assertEquals("Check-in Error: test exception", sut.error(e))
    }

    @Test
    fun presentDto() {
        val dto = CheckInDTO(Instant.parse("2020-11-15T00:00:00Z"), 1, 50)
        assertEquals("Last Check-in Details:\nDate: 15/11/2020\nStreak: 1\nReward: 50 points", sut.presentDto(dto))
    }
}