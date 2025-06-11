fun main() {
    val cake =
        arrayOf(
            "KKKK",
            "KKKK",
            "KKKK",
            "FGHI",
        )
    val cutRows = intArrayOf(2, 3)
    val cutColumns = intArrayOf(1)

    println(maxFlavorCount(cake, cutRows, cutColumns))
}

fun maxFlavorCount(
    cakes: Array<String>,
    cut_rows: IntArray,
    cut_columns: IntArray,
): Int {
    val n = cakes.size
    val allRows = listOf(0) + cut_rows.toList() + listOf(n)
    val allCols = listOf(0) + cut_columns.toList() + listOf(cakes[0].length)

    var maxFlavor = 0

    for (i in 0 until allRows.size - 1) {
        for (j in 0 until allCols.size - 1) {
            val rowStart = allRows[i]
            val rowEnd = allRows[i + 1]
            val colStart = allCols[j]
            val colEnd = allCols[j + 1]

            val flavors = mutableSetOf<Char>()

            for (r in rowStart until rowEnd) {
                for (c in colStart until colEnd) {
                    flavors.add(cakes[r][c])
                }
            }

            maxFlavor = maxOf(maxFlavor, flavors.size)
        }
    }

    return maxFlavor
}

fun maxScore(board: Array<IntArray>): Int {
    val n = board.size
    val m = board[0].size
    val dp = Array(n) { IntArray(m) { Int.MIN_VALUE } }

    // 시작점 초기화
    dp[0][0] = board[0][0]

    for (i in 0 until n) {
        for (j in 0 until n) {
            if (i == 0 && j == 0) continue // 시작점은 이미 초기화됨

            // 왼쪽에서 올 경우
            if (j > 0) {
                val scoreFromLeft = dp[i][j - 1] + board[i][j]
                dp[i][j] = maxOf(dp[i][j], scoreFromLeft)

                // board[i][j]가 0일 때 부호를 바꾸는 경우
                if (board[i][j] == 0) {
                    val scoreFromLeftWithSignChange = dp[i][j - 1] * -1 // 부호 반전
                    dp[i][j] = maxOf(dp[i][j], scoreFromLeftWithSignChange)
                }
            }

            // 위에서 올 경우
            if (i > 0) {
                val scoreFromTop = dp[i - 1][j] + board[i][j]
                dp[i][j] = maxOf(dp[i][j], scoreFromTop)

                // board[i][j]가 0일 때 부호를 바꾸는 경우
                if (board[i][j] == 0) {
                    val scoreFromTopWithSignChange = dp[i - 1][j] * -1 // 부호 반전
                    dp[i][j] = maxOf(dp[i][j], scoreFromTopWithSignChange)
                }
            }
        }
    }

    // 우측 하단 (n-1, m-1) 값 반환
    return dp[n - 1][m - 1]
}

fun solve(
    value: IntArray,
    projects: Array<IntArray>,
): Int {
    // 트리 구성
    val tree = mutableMapOf<Int, MutableList<Int>>()
    for (unit in projects) {
        val parent = unit[0]
        val child = unit[1]
        tree.computeIfAbsent(parent) { mutableListOf() }.add(child)
    }

    val parents = projects.map { it[0] }.toSet()
    val children = projects.map { it[1] }.toSet()
    val root = (parents - children).first()

    // DFS 함수
    fun dfs(node: Int): Int {
        val children = tree[node] ?: return value[node - 1]
        val maxChildValue = children.maxOf { dfs(it) }
        return value[node - 1] + maxChildValue
    }

    // 루트 노드 1에서 시작
    return dfs(root)
}
