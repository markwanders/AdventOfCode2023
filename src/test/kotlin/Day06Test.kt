import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day06Test {
    private val day = Day06()
    private val testInput1 = """
        Time:      7  15   30
        Distance:  9  40  200
    """.trimIndent().split("\n")
    @Test
    fun testCase1() {
        assertEquals(288, day.part1(testInput1))
    }

    @Test
    fun testCase2() {
        assertEquals(71503, day.part2(testInput1))
    }
}