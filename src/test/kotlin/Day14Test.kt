import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day14Test {
    private val day = Day14()
    private val testInput1 = """
        O....#....
        O.OO#....#
        .....##...
        OO.#O....O
        .O.....O#.
        O.#..O.#.#
        ..O..#O..O
        .......O..
        #....###..
        #OO..#....
    """.trimIndent().split("\n")

    @Test
    fun testCase1() {
        assertEquals(136, day.part1(testInput1))
    }

}