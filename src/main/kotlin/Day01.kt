import java.io.File

class Day01 {

    fun part1(input: List<String>): Int =
        input.map { line -> line.filter { char -> char.isDigit() } }.sumOf { string: String ->
            (string.first() + string.last().toString()).toInt()
        }

    fun part2(input: List<String>): Int {
        return 0
    }

    companion object {
        private val day = Day01()
        private val input = File("src/main/resources/day01.txt").readLines()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
        }
    }
}