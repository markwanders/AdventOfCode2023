import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day21Test {
    private val day = Day21()
    private val testInput1 = """
        ...........
        .....###.#.
        .###.##..#.
        ..#.#...#..
        ....#.#....
        .##..S####.
        .##..#...#.
        .......##..
        .##.#.####.
        .##..##.##.
        ...........
    """.trimIndent().split("\n")
    @Test
    fun testCase1() {
        assertEquals(16, day.part1(testInput1))
    }
}