class OddEvenNode {
    fun solution(
        nodes: IntArray,
        edges: Array<IntArray>,
    ): IntArray {
        val graph =
            mutableMapOf<Int, MutableList<Int>>().apply {
                for ((a, b) in edges) {
                    computeIfAbsent(a) { mutableListOf() }.add(b)
                    computeIfAbsent(b) { mutableListOf() }.add(a)
                }
            }

        val visited = mutableMapOf<Int, Int>()

        for (node in nodes) {
            visited[node] = graph[node]?.size ?: 0
        }

        val isEvenOdd: (Int, Int) -> Boolean = { node, degree -> node % 2 == degree % 2 }
        val isReversed: (Int, Int) -> Boolean = { node, degree -> node % 2 != degree % 2 }

        fun dfs(
            parentNode: Int,
            currentNode: Int,
            checking: (Int, Int) -> Boolean,
        ): Boolean {
            val connected =
                (visited[currentNode] ?: 0).let {
                    if (parentNode != -1 && it != 0) {
                        it - 1
                    } else {
                        it
                    }
                }

            return checking(currentNode, connected) &&
                (
                    graph[currentNode]?.filter { it != currentNode }?.all {
                        dfs(
                            currentNode,
                            it,
                            checking,
                        )
                    } ?: true
                )
        }

        var evenOddTreeNumber = 0
        var reversedEvenOddTreeNumber = 0

        graph.forEach {
            if (dfs(-1, it.key, isEvenOdd)) {
                evenOddTreeNumber++
            } else if (dfs(-1, it.key, isReversed)) {
                reversedEvenOddTreeNumber++
            }
        }

        return intArrayOf(evenOddTreeNumber, reversedEvenOddTreeNumber)
    }
}

fun main() {
    OddEvenNode()
        .solution(
            intArrayOf(11, 9, 3, 2, 4, 6),
            arrayOf(intArrayOf(9, 11), intArrayOf(2, 3), intArrayOf(6, 3), intArrayOf(3, 4)),
        ).also {
            print(it.first())
            print(it[1])
        }
}
