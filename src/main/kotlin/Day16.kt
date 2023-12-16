class Day16 {
    data class Point(val x: Int, val y: Int)
    data class Beam(var location: Point, var direction: Char)
    fun part1(input: List<String>): Int {
        val grid = mutableMapOf<Point, Char>()
        input.forEachIndexed { y, s ->
            s.forEachIndexed { x, c -> grid[Point(x, y)] = c }
        }
        val energized = mutableSetOf<Point>()
        val beams = mutableListOf(Beam(Point(0, 0), 'r'))
        while (beams.all { grid.containsKey(it.location) }) {
            beams.forEach { beam ->
                beam.location = when(beam.direction) {
                    'l' -> Point(beam.location.x - 1, beam.location.y)
                    'r' -> Point(beam.location.x + 1, beam.location.y)
                    'u' -> Point(beam.location.x, beam.location.y + 1)
                    'd' -> Point(beam.location.x, beam.location.y - 1)
                    else -> beam.location
                }
                energized.add(beam.location)
                when(grid[beam.location]) {
                    '\\' -> {}
                    '/' -> {}
                    '|' -> {}
                    '-' -> {}
                    '.' -> {}
                }
            }
        }
        return  0
    }
}