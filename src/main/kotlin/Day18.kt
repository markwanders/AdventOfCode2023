import java.io.File
import kotlin.math.abs

class Day18 {
    data class Point(var x: Long, var y: Long)

    fun part1(input: List<String>): Long {
        val point = Point(0, 0)
        var perimeter = 0L
        val corners = input.map { line ->
            val parts = line.split(" ")
            val direction = parts[0]
            val distance = parts[1].toInt()
            perimeter += distance
            when (direction) {
                "R" -> point.x += distance
                "L" -> point.x -= distance
                "U" -> point.y += distance
                "D" -> point.y -= distance
            }
            point.copy()
        }
        return areaOfPolygon(corners, perimeter)
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun part2(input: List<String>): Long {
        val point = Point(0, 0)
        var perimeter = 0L
        val corners = input.map { line ->
            val parts = line.split(" ")
            val hex = parts[2]
            val distance = hex.substring(2, hex.length - 2).hexToInt()
            val direction = when(hex.takeLast(2).first()) {
                '0' -> "R"
                '1' -> "D"
                '2' -> "L"
                '3' -> "U"
                else -> ""
            }
            perimeter += distance
            when (direction) {
                "R" -> point.x += distance
                "L" -> point.x -= distance
                "U" -> point.y += distance
                "D" -> point.y -= distance
            }
            point.copy()
        }
        return areaOfPolygon(corners, perimeter)
    }

    private fun areaOfPolygon(corners: List<Point>, perimeter: Long): Long {
        var sum = 0L
        for (i in corners.indices) {
            if (i + 1 in corners.indices) {
                sum += corners[i].x * corners[i + 1].y - corners[i].y * corners[i + 1].x
            }
        }

        return (0.5 * abs(sum) + perimeter / 2 + 1).toLong()
    }

    companion object {
        private val day = Day18()
        private val input = File("src/main/resources/day18.txt").readLines()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
            println(day.part2(input))
        }
    }
}