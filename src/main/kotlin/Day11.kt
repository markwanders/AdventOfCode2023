import java.io.File
import kotlin.math.abs

class Day11 {
    data class Galaxy(val x: Int, val y: Int)

    fun part1(input: List<String>): Long = sumDistances(input)

    fun part2(input: List<String>, factor: Long = 1000000 - 1): Long = sumDistances(input, factor)

    private fun sumDistances(input: List<String>, factor: Long = 1): Long {
        val galaxies = mutableListOf<Galaxy>()
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, c -> if (c == '#') galaxies.add(Galaxy(x, y)) }
        }
        val emptyColumns =
            (0..galaxies.maxOfOrNull { it.x }!!).filterNot { x -> galaxies.map { galaxy -> galaxy.x }.contains(x) }
        val emptyRows =
            (0..galaxies.maxOfOrNull { it.y }!!).filterNot { y -> galaxies.map { galaxy -> galaxy.y }.contains(y) }
        val seen = mutableSetOf<Pair<Galaxy, Galaxy>>()
        return galaxies.sumOf { a ->
            galaxies.mapNotNull { b ->
                if (a != b && !seen.contains(Pair(a, b)) && !seen.contains(Pair(b, a))) {
                    val rows = emptyRows.filter { row -> (a.y < row && row < b.y || b.y < row && row < a.y) }.size
                    val columns = emptyColumns.filter { row -> (a.x < row && row < b.x || b.x < row && row < a.x) }.size
                    val d =
                        abs(b.x - a.x) + columns * factor + abs(b.y - a.y) + rows * factor
                    seen.add(Pair(a, b))
                    d
                } else {
                    null
                }
            }
                .sum()
        }
    }

    companion object {
        private val day = Day11()
        private val input = File("src/main/resources/day11.txt").readLines()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
            println(day.part2(input))
        }
    }
}