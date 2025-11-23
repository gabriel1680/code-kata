package checkin.app

import org.gbl.checkin.CheckInDTO
import org.gbl.checkin.GetLastCheckInQuery
import org.gbl.checkin.app.usecase.CheckIn
import org.gbl.checkin.CheckInApiImpl
import org.gbl.checkin.out.MemoryCheckInRepository
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.time.Clock
import java.time.Instant
import kotlin.test.assertEquals

class CheckInApiImplTest {

    companion object Fixture {
        private const val USER_ID = 123L
        private val NOW = Instant.parse("2025-11-15T00:00:00Z")
    }

    @Test
    fun `get lsat check-in of a user`() {
        val checkInDTO = CheckInDTO(NOW, 1, 50)
        val queryService = mock<CheckInQueryService>() {
            on { getLastCheckInFor(USER_ID) } doReturn checkInDTO
        }
        val checkInUseCase = CheckIn(MemoryCheckInRepository(), Clock.systemUTC())
        val sut = CheckInApiImpl(checkInUseCase, queryService)
        val dto = sut.getLastCheckIn(GetLastCheckInQuery(123L))
        assertEquals(dto, checkInDTO)
    }

    @Test
    @Disabled
    fun `list the current check-ins of a user`() {
        TODO("Add test case implementation")
    }
}

