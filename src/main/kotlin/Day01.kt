import java.io.File

class Day01 {

    fun part1(input: List<String>): Int =
        input.map { line -> line.filter { char -> char.isDigit() } }.sumOf { string: String ->
            (string.first() + string.last().toString()).toInt()
        }

    fun part2(input: List<String>): Int {
        //todo: this doesn't handle duplicates...
        val toSearch = mapOf("one" to "1", "two" to "2", "three" to "3", "four" to "4", "five" to "5", "six" to "6", "seven" to "7", "eight" to "8", "nine" to "9")
        return input.sumOf { line ->
            val foundNumbers = mutableMapOf<Int, String>().toSortedMap()
            toSearch.keys.forEach { number ->
                val at = line.indexOf(number)
                if (at >= 0) foundNumbers[at] = toSearch[number]!!
            }
            line.forEachIndexed { i, it -> if (it.isDigit()) foundNumbers[i] = it.toString() }
            println(foundNumbers.values)
            (foundNumbers.values.first() + foundNumbers.values.last()).toInt()
        }
    }

    companion object {
        private val day = Day01()
        private val input = File("src/main/resources/day01.txt").readLines()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
            println(day.part2(input))
        }
    }
}