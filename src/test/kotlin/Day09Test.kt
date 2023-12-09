
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day09Test {
    private val day = Day09()
    private val testInput1 = """
        0 3 6 9 12 15
        1 3 6 10 15 21
        10 13 16 21 30 45
    """.trimIndent().split("\n")

    @Test
    fun testCase1() {
        assertEquals(114, day.part1(testInput1))
    }

    @Test
    fun testCase2() {
        assertEquals(2, day.part2(testInput1))
    }
}