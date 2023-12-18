import java.io.File

class Day16 {
    data class Point(val x: Int, val y: Int)
    data class Beam(var location: Point, var direction: Char)

    fun part1(input: List<String>): Int {
        val grid = mutableMapOf<Point, Char>()
        input.forEachIndexed { y, s ->
            s.forEachIndexed { x, c -> grid[Point(x, y)] = c }
        }
        return traverseGrid(grid, Beam(Point(-1, 0), 'r'))
    }

    fun part2(input: List<String>): Int {
        val grid = mutableMapOf<Point, Char>()
        input.forEachIndexed { y, s ->
            s.forEachIndexed { x, c -> grid[Point(x, y)] = c }
        }
        val maxX = grid.keys.maxBy { it.x }.x
        val maxY = grid.keys.maxBy { it.y }.y
        val startPositions = (0..maxX).map { x -> Beam(Point(x, -1), 'u') }.toMutableList()
        startPositions.addAll((0..maxX).map { x -> Beam(Point(x, maxY + 1), 'd') })
        startPositions.addAll((0..maxY).map { y -> Beam(Point(-1, y), 'r') })
        startPositions.addAll((0..maxY).map { y -> Beam(Point(maxX + 1, y), 'l') })
        return startPositions.maxOf { traverseGrid(grid, it) }
    }

    private fun traverseGrid(grid: Map<Point, Char>, start: Beam): Int {
        val energized = mutableSetOf<Point>()
        val beams = mutableListOf(start)
        val seen = mutableSetOf<Beam>()
        while (beams.isNotEmpty()) {
            val beam = beams.removeFirst()
            if (seen.any { it == beam }) {
                continue
            }
            seen.add(beam.copy())
            beam.location = when (beam.direction) {
                'l' -> Point(beam.location.x - 1, beam.location.y)
                'r' -> Point(beam.location.x + 1, beam.location.y)
                'u' -> Point(beam.location.x, beam.location.y + 1)
                'd' -> Point(beam.location.x, beam.location.y - 1)
                else -> beam.location
            }
            if (beam.location !in grid.keys) {
                continue
            }
            energized.add(beam.location)
            when (grid[beam.location]) {
                '\\' -> when (beam.direction) {
                    'l' -> beam.direction = 'd'
                    'r' -> beam.direction = 'u'
                    'u' -> beam.direction = 'r'
                    'd' -> beam.direction = 'l'
                }

                '/' -> when (beam.direction) {
                    'l' -> beam.direction = 'u'
                    'r' -> beam.direction = 'd'
                    'u' -> beam.direction = 'l'
                    'd' -> beam.direction = 'r'
                }

                '|' -> if (beam.direction == 'l' || beam.direction == 'r') {
                    beam.direction = 'u'
                    val newBeam = beam.copy(direction = 'd')
                    beams.add(newBeam)
                }

                '-' -> if (beam.direction == 'u' || beam.direction == 'd') {
                    beam.direction = 'l'
                    val newBeam = beam.copy(direction = 'r')
                    beams.add(newBeam)
                }

                '.' -> {}
            }
            beams.add(beam)
        }
        return energized.size
    }

    companion object {
        private val day = Day16()
        private val input = File("src/main/resources/day16.txt").readLines()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
            println(day.part2(input))
        }
    }
}