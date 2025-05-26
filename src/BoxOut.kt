fun boxOut(
    n: Int,
    w: Int,
    num: Int,
): Int {
    var answer = 0
    val array: Array<Array<Int>> = Array(n / w + 1) { Array(w) { 0 } }
    var row = 0
    for (i in 1..n) {
        if (row % 2 == 0) {
            array[row][(i - 1) % w] = i
        } else {
            array[row][w - ((i - 1) % w) - 1] = i
        }

        if (i % w == 0) {
            row++
        }
    }

    val startRow = (num - 1) / w
    val targetCol = if (startRow % 2 == 0) (num - 1) % w else w - ((num - 1) % w) - 1

    for (i in array.size - 1 downTo startRow) {
        if (array[i][targetCol] != 0) {
            answer++
        }
    }

    return answer
}

fun main() {
    println(boxOut(13, 3, 6))
}
