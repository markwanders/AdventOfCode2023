import java.io.File

class Day09 {
    fun part1(input: List<String>): Int =
        input.sumOf { line ->
            val values = line.split(" ").map { it.toInt() }
            val diffs = mutableListOf<Int>()
            var nextValues = values
            do {
                nextValues =
                    nextValues.mapIndexed { index, i -> if (index + 1 < nextValues.size) nextValues[index + 1] - i else null }
                        .filterNotNull()
                diffs.add(nextValues.last())
            } while (nextValues.any { it != 0 })
            values.last() + diffs.sum()
        }

    companion object {
        private val day = Day09()
        private val input = File("src/main/resources/day09.txt").readLines()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
        }
    }
}