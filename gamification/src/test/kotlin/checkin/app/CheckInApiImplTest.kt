package checkin.app

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.gbl.checkin.CheckInApiImpl
import org.gbl.checkin.CheckInDTO
import org.gbl.checkin.GetLastCheckInQuery
import org.gbl.checkin.app.service.CheckInQueryService
import org.gbl.checkin.app.usecase.CheckIn
import org.gbl.checkin.out.MemoryCheckInRepository
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
            on { async { getLastCheckInFor(USER_ID) } } doReturn CompletableDeferred(checkInDTO)
        }
        val checkInUseCase = CheckIn(MemoryCheckInRepository(), Instant::now)
        val sut = CheckInApiImpl(checkInUseCase, queryService)
        val dto = sut.getLastCheckIn(GetLastCheckInQuery(123L))
        assertEquals(dto, checkInDTO)
    }
}

