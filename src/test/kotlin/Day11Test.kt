import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day11Test {
    private val day = Day11()
    private val testInput1 = """
        ...#......
        .......#..
        #.........
        ..........
        ......#...
        .#........
        .........#
        ..........
        .......#..
        #...#.....
    """.trimIndent().split("\n")

    @Test
    fun testCase1() {
        assertEquals(374, day.part1(testInput1))
    }

    @Test
    fun testCase2() {
        assertEquals(1030, day.part2(testInput1, 10L - 1))
    }

    @Test
    fun testCase3() {
        assertEquals(8410, day.part2(testInput1, 100L - 1))
    }
}