import java.io.File

class Day10 {
    data class Point(val x: Int, val y: Int)
    fun part1(input: List<String>): Int {
        val grid = mutableMapOf<Point, Char>()
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, c -> grid[Point(x, y)] = c }
        }
        print(grid)
        return 0
    }

    companion object {
        private val day = Day10()
        private val input = File("src/main/resources/day10.txt").readLines()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
        }
    }
}