import java.io.File

class Day06 {
    fun part1(input: List<String>): Int {
        val times =
            Regex("\\d+").findAll(input.first()).flatMap { match -> match.groupValues.map { it.toInt() } }.toList()
        val distances =
            Regex("\\d+").findAll(input.last()).flatMap { match -> match.groupValues.map { it.toLong() } }.toList()
        return times.mapIndexed { race, time ->
            doRace(time, distances[race])
        }.reduce { acc, i -> acc * i }
    }

    fun part2(input: List<String>): Int {
        val time = input.first().filter { it.isDigit() }.toInt()
        val distanceToBeat = input.last().filter { it.isDigit() }.toLong()
        return doRace(time, distanceToBeat)
    }

    private fun doRace(time: Int, distanceToBeat: Long): Int {
        var timeSpentCharging = 1
        var winningDistances = 0
        while (timeSpentCharging < time) {
            val timeRemaining = time - timeSpentCharging
            val distance = timeRemaining * timeSpentCharging.toLong()
            if (distance > distanceToBeat) {
                winningDistances++
            }
            timeSpentCharging++
        }
        return winningDistances
    }

    companion object {
        private val day = Day06()
        private val input = File("src/main/resources/day06.txt").readLines()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
            println(day.part2(input))
        }
    }
}