import java.io.File

class Day17 {
    data class Point(val x: Int, val y: Int)
    fun part1(input: List<String>): Int {
        val grid = mutableMapOf<Point, Int>()
        input.forEachIndexed { y, s ->
            s.forEachIndexed { x, c ->
                grid[Point(x, y)] = c.digitToInt()
            }
        }
        val position = Point(0, 0)
        val finish = grid.keys.maxBy { it.x + it.y }
        println(finish)

        return 0
    }
    companion object {
        private val day = Day17()
        private val input = File("src/main/resources/day17.txt").readLines()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
        }
    }
}