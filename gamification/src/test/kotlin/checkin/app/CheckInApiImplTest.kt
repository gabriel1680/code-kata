package checkin.app

import checkin.CheckInApiImpl
import checkin.CheckInDTO
import checkin.GetLastCheckInQuery
import checkin.app.service.CheckInQueryService
import checkin.app.usecase.CheckIn
import checkin.out.MemoryCheckInRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.time.Instant
import kotlin.test.assertEquals

class CheckInApiImplTest {

    companion object Fixture {
        private const val USER_ID = 123L
        private val NOW = Instant.parse("2025-11-15T00:00:00Z")
    }

    @Test
    fun `get lsat check-in of a user`() = runBlocking {
        val checkInDTO = CheckInDTO(NOW, 1, 50)
        val queryService = mock<CheckInQueryService> {
            onBlocking {  getLastCheckInFor(USER_ID)  } doReturn checkInDTO
        }
        val checkInUseCase = CheckIn(MemoryCheckInRepository(), Instant::now)
        val sut = CheckInApiImpl(checkInUseCase, queryService)
        val dto = sut.getLastCheckIn(GetLastCheckInQuery(123L))
        assertEquals(dto, checkInDTO)
    }
}

