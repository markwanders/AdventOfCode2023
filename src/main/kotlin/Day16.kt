import java.io.File

class Day16 {
    data class Point(val x: Int, val y: Int)
    data class Beam(var location: Point, var direction: Char)
    fun part1(input: List<String>): Int {
        val grid = mutableMapOf<Point, Char>()
        input.forEachIndexed { y, s ->
            s.forEachIndexed { x, c -> grid[Point(x, y)] = c }
        }
        val energized = mutableSetOf<Point>()
        val beams = mutableListOf(Beam(Point(-1, 0), 'r'))
        val seen = mutableSetOf<Beam>()
        while (beams.isNotEmpty()) {
//            println("${beams.size}: $beams")
            val beam = beams.removeFirst()
            if (seen.any { it == beam}) {
//                println("Already seen $beam")
                continue
            }
//            println("Adding $beam to seen")
            seen.add(beam.copy())
            beam.location = when (beam.direction) {
                'l' -> Point(beam.location.x - 1, beam.location.y)
                'r' -> Point(beam.location.x + 1, beam.location.y)
                'u' -> Point(beam.location.x, beam.location.y + 1)
                'd' -> Point(beam.location.x, beam.location.y - 1)
                else -> beam.location
            }
//            println("Moved to ${beam.location} with direction ${beam.direction}")
            if (beam.location !in grid.keys) {
//                println("Out of bounds ${beam.location}")
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
//                    println("Splitting beams into $beam and $newBeam")
                    beams.add(newBeam)
                }

                '-' -> if (beam.direction == 'u' || beam.direction == 'd') {
                    beam.direction = 'l'
                    val newBeam = beam.copy(direction = 'r')
//                    println("Splitting beams into $beam and $newBeam")
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
        }
    }
}