package org.gbl.checkin

import org.gbl.checkin.application.CheckInCommand

interface CheckInAPI {
    fun checkIn(command: CheckInCommand)
}