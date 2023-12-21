
import java.io.File
import java.util.*

class Day17 {
    enum class Direction {
        RIGHT, LEFT, UP, DOWN, NONE
    }

    data class Point(val x: Int, val y: Int)
    data class Node(val point: Point, val previousDirection: Direction, val directionCounter: Int, val cost: Int)
    data class State(val point: Point, val previousDirection: Direction, val directionCounter: Int)

    fun part1(input: List<String>): Int {
        return findHeatLoss(input)
    }

    fun part2(input: List<String>): Int {
        return findHeatLoss(input, true)
    }

    private fun findHeatLoss(input: List<String>, part2: Boolean = false): Int {
        val grid = mutableMapOf<Point, Int>()
        input.forEachIndexed { y, s ->
            s.forEachIndexed { x, c ->
                grid[Point(x, y)] = c.digitToInt()
            }
        }
        val position = Point(0, 0)
        val finish = grid.keys.maxBy { it.x + it.y }
        val initialNode = Node(position, Direction.NONE, 0, 0)
        val queue: PriorityQueue<Node> = PriorityQueue(setOf(initialNode).toSortedSet(compareBy { it.cost }))
        val seen = mutableMapOf<State, Int>()
        while (queue.isNotEmpty()) {
            val node = queue.poll()
            val neighbours = neighbours(node, grid, part2)
            for(n in neighbours) {
                val state = State(n.point, n.previousDirection, n.directionCounter)
                if (seen.getOrDefault(state, Int.MAX_VALUE) > n.cost ) {
                    seen[state] = n.cost
                    queue.add(n)
                }
            }
        }
        return seen.entries.filter { it.key.point == finish }.minOf { it.value }
    }

    private fun neighbours(node: Node, grid: Map<Point, Int>, part2: Boolean = false): List<Node> {
        val range = if(part2) (4..10) else (1..3)
        val nodes = mutableListOf<Node>()
        when (node.previousDirection) {
            Direction.RIGHT, Direction.LEFT -> {
                // add three steps up and three steps down to queue, if in range
                range.forEach { i ->
                    val nextPointUp = Point(node.point.x, node.point.y + i)
                    if (grid.containsKey(nextPointUp)) {
                        val cost = (1..i).sumOf { grid.getValue(Point(node.point.x, node.point.y + it)) }
                        nodes.add(Node(nextPointUp, Direction.UP, i, node.cost + cost))
                    }
                    val nextPointDown = Point(node.point.x, node.point.y - i)
                    if (grid.containsKey(nextPointDown)) {
                        val cost = (1..i).sumOf { grid.getValue(Point(node.point.x, node.point.y - it)) }
                        nodes.add(Node(nextPointDown, Direction.DOWN, i, node.cost + cost))
                    }
                }
            }

            Direction.UP, Direction.DOWN -> {
                // add three steps left and three steps right to queue, if in range
                range.forEach { i ->
                    val nextPointRight = Point(node.point.x + i, node.point.y)
                    if (grid.containsKey(nextPointRight)) {
                        val cost = (1..i).sumOf { grid.getValue(Point(node.point.x + it, node.point.y)) }
                        nodes.add(Node(nextPointRight, Direction.RIGHT, i, node.cost + cost))
                    }
                    val nextPointLeft = Point(node.point.x - i, node.point.y)
                    if (grid.containsKey(nextPointLeft)) {
                        val cost = (1..i).sumOf { grid.getValue(Point(node.point.x - it, node.point.y)) }
                        nodes.add(Node(nextPointLeft, Direction.LEFT, i, node.cost + cost))
                    }
                }
            }

            Direction.NONE ->
                range.forEach { i ->
                    val nextPointUp = Point(node.point.x, node.point.y + i)
                    if (grid.containsKey(nextPointUp)) {
                        val cost = (1..i).sumOf { grid.getValue(Point(node.point.x, node.point.y + it)) }
                        nodes.add(Node(nextPointUp, Direction.UP, i, node.cost + cost))
                    }
                    val nextPointDown = Point(node.point.x, node.point.y - i)
                    if (grid.containsKey(nextPointDown)) {
                        val cost = (1..i).sumOf { grid.getValue(Point(node.point.x, node.point.y - it)) }
                        nodes.add(Node(nextPointDown, Direction.DOWN, i, node.cost + cost))
                    }
                    val nextPointRight = Point(node.point.x + i, node.point.y)
                    if (grid.containsKey(nextPointRight)) {
                        val cost = (1..i).sumOf { grid.getValue(Point(node.point.x + it, node.point.y)) }
                        nodes.add(Node(nextPointRight, Direction.RIGHT, i, node.cost + cost))
                    }
                    val nextPointLeft = Point(node.point.x - i, node.point.y)
                    if (grid.containsKey(nextPointLeft)) {
                        val cost = (1..i).sumOf { grid.getValue(Point(node.point.x - it, node.point.y)) }
                        nodes.add(Node(nextPointLeft, Direction.LEFT, i, node.cost + cost))
                    }
                }
        }
        return nodes
    }

    companion object {
        private val day = Day17()
        private val input = File("src/main/resources/day17.txt").readLines()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
            println(day.part2(input))
        }
    }
}