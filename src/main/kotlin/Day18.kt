import java.io.File
import kotlin.math.abs

class Day18 {
    data class Point(var x: Int, var y: Int)
    fun part1(input: List<String>): Int {
        val corners = mutableListOf<Point>()
        val point = Point(0, 0)
        var perimeter = 0
        input.forEach { line ->
            val parts = line.split(" ")
            val direction = parts[0]
            val distance = parts[1].toInt()
            perimeter += distance
            when(direction) {
                "R" -> point.x += distance
                "L" -> point.x -= distance
                "U" -> point.y += distance
                "D" -> point.y -= distance
            }
            corners.add(point.copy())
        }
        var sum = 0
        for (i in corners.indices) {
            if (i + 1 in corners.indices) {
                sum += corners[i].x * corners[i + 1].y - corners[i].y * corners[i + 1].x
            }
        }

        return (0.5 * abs(sum) + perimeter/2 + 1).toInt()
    }

    companion object {
        private val day = Day18()
        private val input = File("src/main/resources/day18.txt").readLines()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
        }
    }
}