import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day07Test {
    private val day = Day07()
    private val testInput1 = """
        32T3K 765
        T55J5 684
        KK677 28
        KTJJT 220
        QQQJA 483
    """.trimIndent().split("\n")
    @Test
    fun testCase1() {
        assertEquals(6440, day.part1(testInput1))
    }

    @Test
    fun testCase2() {
        assertEquals(5905, day.part2(testInput1))
    }

    /**
     * 251694264 too high
     * 251369282 too high
     * 251914857 too high
     * 251691065 too high
     * 251567630 too high
     */
}