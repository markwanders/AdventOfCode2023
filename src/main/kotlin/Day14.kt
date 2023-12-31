import java.io.File
import java.util.*

class Day14 {
    data class Point(var x: Int, var y: Int)

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
        val rolled = rollNorth(round, cubed)
        return rolled.sumOf { input.size - it.y }
    }

    fun part2(input: List<String>): Int {
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
        val seen = mutableMapOf<String, Int>()
        var stones = round
        var cycle = 0
        var length = 0
        val total = 1_000_000_000
        while (cycle < total) {
            if (stones.toString() in seen) {
                length = cycle - seen[stones.toString()]!!
                break
            }
            seen[stones.toString()] = cycle
            stones = cycle(stones, cubed, input.size)
            cycle++
        }
        val remainingCycles =  (total - cycle) % length
        repeat(remainingCycles) {
            stones = cycle(stones, cubed, input.size)
        }
        return stones.sumOf { input.size - it.y }
    }

    private fun cycle(round: Set<Point>, cubed: Set<Point>, gridSize: Int) =
        rollEast(rollSouth(rollWest(rollNorth(round, cubed), cubed), cubed, gridSize), cubed, gridSize)

    private fun rollNorth(round: Set<Point>, cubed: Set<Point>): SortedSet<Point> {
        val newRocks = mutableSetOf<Point>()
        for (rock in round.sortedBy { it.y }) {
            var y= rock.y
            while (y - 1 >= 0 && newRocks.none { it.x == rock.x && it.y == y - 1 } && cubed.none { it.x == rock.x && it.y == y - 1 }) {
                y--
            }
            newRocks.add(Point(rock.x, y))
        }
        return newRocks.toSortedSet(compareBy( { it.x }, {it.y}))
    }

    private fun rollSouth(round: Set<Point>, cubed: Set<Point>, gridSize: Int): SortedSet<Point> {
        val newRocks = mutableSetOf<Point>()
        for (rock in round.sortedByDescending { it.y }) {
            var y= rock.y
            while (y + 1 < gridSize && newRocks.none { it.x == rock.x && it.y == y + 1 } && cubed.none { it.x == rock.x && it.y == y + 1 }) {
                y++
            }
            newRocks.add(Point(rock.x, y))
        }
        return newRocks.toSortedSet(compareBy( { it.x }, {it.y}))
    }

    private fun rollWest(round: Set<Point>, cubed: Set<Point>): SortedSet<Point> {
        val newRocks = mutableSetOf<Point>()
        for (rock in round.sortedBy { it.x }) {
            var x= rock.x
            while (x - 1 >= 0 && newRocks.none { it.x == x - 1 && it.y == rock.y } && cubed.none { it.x == x - 1 && it.y == rock.y }) {
                x--
            }
            newRocks.add(Point(x, rock.y))
        }
        return newRocks.toSortedSet(compareBy( { it.x }, {it.y}))
    }

    private fun rollEast(round: Set<Point>, cubed: Set<Point>, gridSize: Int): SortedSet<Point> {
        val newRocks = mutableSetOf<Point>()
        for (rock in round.sortedByDescending { it.x }) {
            var x= rock.x
            while (x + 1 < gridSize && newRocks.none { it.x == x + 1 && it.y == rock.y } && cubed.none { it.x == x + 1 && it.y == rock.y }) {
                x++
            }
            newRocks.add(Point(x, rock.y))
        }
        return newRocks.toSortedSet(compareBy( { it.x }, {it.y}))
    }

    companion object {
        private val day = Day14()
        private val input = File("src/main/resources/day14.txt").readLines()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
            println(day.part2(input))
        }
    }
}