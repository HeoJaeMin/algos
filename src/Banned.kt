fun banned(
    n: Long,
    bans: Array<String>,
): String {
    var target = n

    fun toNumber(ban: String): Long {
        var res = 0L
        for (ch in ban) {
            res = res * 26 + (ch - 'a' + 1)
        }
        return res
    }

    fun toAlpha(index: Long): String {
        var number = index
        val sb = StringBuilder()
        while (number > 0) {
            number--
            sb.append(('a' + (number % 26).toInt()))
            number /= 26
        }
        return sb.reverse().toString()
    }
    val banNumberList =
        bans
            .map {
                toNumber(it)
            }.sorted()

    for (banNumber in banNumberList) {
        if (banNumber <= target) {
            target++
        } else {
            break
        }
    }

    return toAlpha(target)
}

fun main() {
    println(banned(30, arrayOf()))
}
