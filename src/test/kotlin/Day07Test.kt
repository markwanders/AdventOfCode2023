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
    private val testInput2 = """
        2345A 1
        Q2KJJ 13
        Q2Q2Q 19
        T3T3J 17
        T3Q33 11
        2345J 3
        J345A 2
        32T3K 5
        T55J5 29
        KK677 7
        KTJJT 34
        QQQJA 31
        JJJJJ 37
        JAAAA 43
        AAAAJ 59
        AAAAA 61
        2AAAA 23
        2JJJJ 53
        JJJJ2 41
    """.trimIndent().split("\n")
    @Test
    fun testCase1() {
        assertEquals(6440, day.part1(testInput1))
    }

    @Test
    fun testCase2() {
        assertEquals(5905, day.part2(testInput1))
    }

    @Test
    fun testCase3() {
        assertEquals(6839, day.part2(testInput2))
    }

    /**
     * 251694264 too high
     * 251369282 too high
     * 251914857 too high
     * 251691065 too high
     * 251567630 too high
     */
}