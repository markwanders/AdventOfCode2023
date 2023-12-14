import java.io.File

class Day14 {
    data class Point(val x: Int, var y: Int)

    fun part1(input: List<String>): Int {
        val round = mutableSetOf<Point>().toSortedSet(compareBy( { it.x }, {it.y}))
        val cubed = mutableSetOf<Point>().toSortedSet(compareBy( { it.x }, {it.y}))
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                if (c == 'O') {
                    round.add(Point(x, y))
                } else if (c == '#') {
                    cubed.add(Point(x, y))
                }
            }
        }
        for (i in 0..input.size) {
            round.filter { it.y == i }.forEach {
                while (it.y - 1 >= 0 && !round.contains(Point(it.x, it.y -1)) && !cubed.contains(Point(it.x, it.y -1))) {
                    it.y--
                }
            }

        }
        return round.sumOf { input.size - it.y }
    }

    companion object {
        private val day = Day14()
        private val input = File("src/main/resources/day14.txt").readLines()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
        }
    }
}