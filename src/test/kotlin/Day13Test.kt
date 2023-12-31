import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day13Test {
    private val day = Day13()
    private val testInput1 = """
        #.##..##.
        ..#.##.#.
        ##......#
        ##......#
        ..#.##.#.
        ..##..##.
        #.#.##.#.

        #...##..#
        #....#..#
        ..##..###
        #####.##.
        #####.##.
        ..##..###
        #....#..#
    """.trimIndent()

    @Test
    fun testCase1() {
        assertEquals(405, day.part1(testInput1))
    }
    @Test
    fun testCase2() {
        assertEquals(400, day.part2(testInput1))
    }
}