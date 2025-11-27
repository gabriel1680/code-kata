package checkin.app.service

import java.time.Instant

fun interface TimeProvider {
    fun instant(): Instant
}