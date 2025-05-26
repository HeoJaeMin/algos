import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun shift(
    schedules: IntArray,
    timelogs: Array<IntArray>,
    startday: Int,
): Int {
    var answer = 0
    val saturdayIndex = 7 - (startday - 1)
    val holiday = intArrayOf(saturdayIndex, (saturdayIndex + 1) % 7)

    for (i in schedules.indices) {
        if (timelogs[i]
                .indices
                .filterNot { holiday.contains(it) }
                .map { timelogs[i][it] }
                .any {
                    it <=
                        LocalTime
                            .of(schedules[i] / 100, schedules[i] % 100)
                            .plusMinutes(10)
                            .format(DateTimeFormatter.ofPattern("HHmm"))
                            .toInt()
                }
        ) {
            answer++
        }
    }

    return answer
}

fun main() {
    shift(intArrayOf(), arrayOf(intArrayOf()), 5)
}
