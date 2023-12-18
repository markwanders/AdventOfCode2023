
import java.io.File
import java.util.*

class Day17 {
    data class Point(val x: Int, val y: Int)
    data class Node(val point: Point, val previousDirection: Char, val directionCounter: Int, val cost: Int)
    fun part1(input: List<String>): Int {
        val grid = mutableMapOf<Point, Int>()
        input.forEachIndexed { y, s ->
            s.forEachIndexed { x, c ->
                grid[Point(x, y)] = c.digitToInt()
            }
        }
        val position = Point(0, 0)
        val finish = grid.keys.maxBy { it.x + it.y }
        val initialNode = Node(position, 'c', 0, 0)
        val queue: PriorityQueue<Node> = PriorityQueue(setOf(initialNode).toSortedSet(compareBy { it.cost }))
        val seen = mutableSetOf(initialNode)
        while (seen.none { it.point == finish }) {
            val node = queue.poll()
            val neighbours = neighbours(node, grid)
//            println("Neighbours for $node are $neighbours")
            val minima = neighbours.filterNot { n -> seen.any { s -> s.point == n.point && s.previousDirection == n.previousDirection && s.directionCounter == n.directionCounter && s.cost < n.cost } }
//            println("New minimal distances: $minima")
            seen.addAll(minima)
            queue.addAll(minima)
        }
        return seen.first { it.point == finish }.cost
    }

    private fun neighbours(node: Node, grid: Map<Point, Int>): List<Node> {
        val nodes = mutableListOf<Node>()
        //right
        val right = Point(node.point.x + 1, node.point.y)
        if (grid.containsKey(right) && node.previousDirection != 'l') {
            if (node.previousDirection == 'r') {
                if (node.directionCounter < 3) {
                    nodes.add(Node(right, 'r', node.directionCounter + 1, node.cost + grid[right]!!))
                }
            } else {
                nodes.add(Node(right, 'r', 1, node.cost + grid[right]!!))
            }
        }
        //left
        val left = Point(node.point.x - 1, node.point.y)
        if (grid.containsKey(left) && node.previousDirection != 'r') {
            if (node.previousDirection == 'l') {
                if (node.directionCounter < 3) {
                    nodes.add(Node(left, 'l', node.directionCounter + 1, node.cost + grid[left]!!))
                }
            } else {
                nodes.add(Node(left, 'l', 1, node.cost + grid[left]!!))
            }
        }
        //up
        val up = Point(node.point.x, node.point.y + 1)
        if (grid.containsKey(up) && node.previousDirection != 'd') {
            if (node.previousDirection == 'u') {
                if (node.directionCounter < 3) {
                    nodes.add(Node(up, 'u', node.directionCounter + 1, node.cost + grid[up]!!))
                }
            } else {
                nodes.add(Node(up, 'u', 1, node.cost + grid[up]!!))
            }
        }
        //down
        val down = Point(node.point.x, node.point.y - 1)
        if (grid.containsKey(down) && node.previousDirection != 'u') {
            if (node.previousDirection == 'd') {
                if (node.directionCounter < 3) {
                    nodes.add(Node(down, 'd', node.directionCounter + 1, node.cost + grid[down]!!))
                }
            } else {
                nodes.add(Node(down, 'd', 1, node.cost + grid[down]!!))
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
        }
    }
}