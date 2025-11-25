package org.gbl.checkin.`in`.cli

class CheckInArgsParser {

    fun parse(args: Array<String>): Result<Long> = runCatching {
        require(args.isNotEmpty()) { "Invalid arguments, too few arguments" }
        val userId: Long? = parseInput(args)
        require(userId != null) { "Invalid userId, it need to be a non negative integer" }
        return Result.success(userId)
    }

    private fun parseInput(args: Array<String>): Long? {
        val userId = args[0]
        return userId.toLongOrNull()
    }
}
