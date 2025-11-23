package checkin.app.domain

import java.time.Instant

data class CheckIn(val streak: Int, val rewards: Long, val date: Instant) {

    companion object Factory {
        fun first(date: Instant) = CheckIn(1, 50, date)

        fun from(another: CheckIn, date: Instant) =
            CheckIn(another.streak + 1, another.rewards + 50, date)

        fun restart(date: Instant) = first(date)
    }
}