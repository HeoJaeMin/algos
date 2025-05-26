fun maxWeightUnderLimit(
    weights: List<Int>,
    limit: Int,
): Int {
    val dp = BooleanArray(limit + 1)
    dp[0] = true

    for (weight in weights) {
        for (i in limit downTo weight) {
            if (dp[i - weight]) {
                dp[i] = true
            }
        }
    }

    for (i in limit downTo 0) {
        if (dp[i]) return i
    }

    return 0
}

fun main() {
    val weights = listOf(2, 5, 7, 11, 15)
    val limit = 19
    println(maxWeightUnderLimit(weights, limit))
}
