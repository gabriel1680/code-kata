package org.gbl.checkin.`in`.cli

import org.gbl.checkin.CheckInDTO

interface CheckInCliPresenter {
    fun success(): String
    fun error(e: Exception): String
    fun presentDto(dto: CheckInDTO): String
    fun bye(): String
}