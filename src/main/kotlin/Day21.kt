import java.io.File
import java.util.*

class Day21 {
    data class Point(val x: Int, val y: Int)
    data class Node(val point: Point, val steps: Int)

    fun part1(input: List<String>): Int {
        val grid = mutableMapOf<Point, Char>()
        input.forEachIndexed { y, s ->
            s.forEachIndexed { x, c -> grid[Point(x, y)] = c }
        }
        val start = grid.entries.first { it.value == 'S' }.key
        val queue: PriorityQueue<Node> = PriorityQueue(setOf(Node(start, 0)).toSortedSet(compareBy { it.steps }))
        val steps = mutableSetOf<Point>()
        val seen = mutableSetOf(Node(start, 0))
        while (queue.isNotEmpty()) {
            val node = queue.poll()
            val neighbours = neighbours(node.point, grid)
            if (node.steps + 1 == 64) {
                steps.addAll(neighbours)
                continue
            }
            val nodes = neighbours.map { Node(it, node.steps + 1) }
            nodes.forEach {
                if (it !in seen) {
                    queue.add(it)
                    seen.add(it)
                }
            }
        }
        return steps.size + 1
    }

    private fun neighbours(point: Point, grid: Map<Point, Char>): List<Point> {
        return setOf(
            Point(point.x + 1, point.y),
            Point(point.x - 1, point.y),
            Point(point.x, point.y + 1),
            Point(point.x, point.y - 1)
        ).filter { it in grid.keys && grid[it] == '.' }
    }

    companion object {
        private val day = Day21()
        private val input = File("src/main/resources/day21.txt").readLines()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
        }
    }
}