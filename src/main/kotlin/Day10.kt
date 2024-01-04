import java.io.File
import kotlin.math.abs

class Day10 {
    data class Point(val x: Int, val y: Int)
    fun part1(input: List<String>): Int {
        val grid = mutableMapOf<Point, Char>()
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, c -> grid[Point(x, y)] = c }
        }
        val start = grid.entries.first { it.value == 'S' }.key
        val path = mutableSetOf(start)
        var position = start
        while (true) {
            val neighbours = neighbours(position, grid)
            val nextPoint = neighbours.firstOrNull { !path.contains(it.key) } ?: break
            position = nextPoint.key
            path.add(position)
        }
        return path.size/2
    }

    fun part2(input: List<String>): Int {
        val grid = mutableMapOf<Point, Char>()
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, c -> grid[Point(x, y)] = c }
        }
        val start = grid.entries.first { it.value == 'S' }.key
        val path = mutableSetOf(start)
        val corners = mutableListOf(start)
        var position = start
        while (true) {
            val neighbours = neighbours(position, grid)
            val nextPoint = neighbours.firstOrNull { !path.contains(it.key) } ?: break
            position = nextPoint.key
            path.add(position)
            if (grid[position]!! in "LJ7FS") {
                corners.add(position)
            }
        }
        // Pick's theorem: A = i + b/2 - 1 where i is the number of interior points, A is the area and b are the boundary points
        // So i = A + 1 - b/2, where we can derive i from the shoelace formula
        var area = 0
        for (i in corners.indices) {
            if (i + 1 in corners.indices) {
                area += (corners[i].y + corners[i + 1].y) * (corners[i].x - corners[i + 1].x)
            }
        }
        area /= 2
        return abs(area) - (path.size/2 - 1)
    }

    private fun neighbours(position: Point, grid: Map<Point, Char>) : List<Map.Entry<Point, Char>> =
        grid.entries.filter { (key, value) ->
            ((key.x == position.x && key.y == position.y + 1 && allowedToGoDown(grid[position]!!, value)) ||
                    (key.x == position.x && key.y == position.y - 1 && allowedToGoUp(grid[position]!!, value)) ||
                    (key.x == position.x + 1 && key.y == position.y && allowedToGoRight(grid[position]!!, value)) ||
                    (key.x == position.x - 1 && key.y == position.y && allowedToGoLeft(grid[position]!!, value)))
    }

    private fun allowedToGoDown(a: Char, b: Char): Boolean {
        return listOf('|', '7', 'S', 'F').contains(a) && listOf('|', 'J', 'L', 'S').contains(b)
    }

    private fun allowedToGoUp(a: Char, b: Char): Boolean {
        return listOf('|', 'J', 'S', 'L').contains(a) && listOf('|', 'F', '7', 'S').contains(b)
    }

    private fun allowedToGoRight(a: Char, b: Char): Boolean {
        return listOf('-', 'F', 'S', 'L').contains(a) && listOf('-', 'J', '7', 'S').contains(b)
    }

    private fun allowedToGoLeft(a: Char, b: Char): Boolean {
        return listOf('-', 'J', 'S', '7').contains(a) && listOf('-', 'F', 'L', 'S').contains(b)
    }

    companion object {
        private val day = Day10()
        private val input = File("src/main/resources/day10.txt").readLines()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
            println(day.part2(input))
        }
    }
}