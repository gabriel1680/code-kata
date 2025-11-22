package org.gbl.checkin

interface CheckInAPI {
    fun checkIn(command: CheckInCommand)
}