
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day10Test {
    private val day = Day10()
    private val testInput1 = """
        .....
        .S-7.
        .|.|.
        .L-J.
        .....
    """.trimIndent().split("\n")
    private val testInput2 = """
        ..F7.
        .FJ|.
        SJ.L7
        |F--J
        LJ...
    """.trimIndent().split("\n")

    @Test
    fun testCase1() {
        assertEquals(4, day.part1(testInput1))
    }

    @Test
    fun testCase2() {
        assertEquals(8, day.part1(testInput2))
    }
}