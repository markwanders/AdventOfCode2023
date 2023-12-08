import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day08Test {
    private val day = Day08()
    private val testInput1 = """
        RL

        AAA = (BBB, CCC)
        BBB = (DDD, EEE)
        CCC = (ZZZ, GGG)
        DDD = (DDD, DDD)
        EEE = (EEE, EEE)
        GGG = (GGG, GGG)
        ZZZ = (ZZZ, ZZZ)
    """.trimIndent()
    private val testInput2 = """
        LLR

        AAA = (BBB, BBB)
        BBB = (AAA, ZZZ)
        ZZZ = (ZZZ, ZZZ)
    """.trimIndent()
    private val testInput3 = """
        LR

        11A = (11B, XXX)
        11B = (XXX, 11Z)
        11Z = (11B, XXX)
        22A = (22B, XXX)
        22B = (22C, 22C)
        22C = (22Z, 22Z)
        22Z = (22B, 22B)
        XXX = (XXX, XXX)
    """.trimIndent()
    @Test
    fun testCase1() {
        assertEquals(2, day.part1(testInput1))
    }
    @Test
    fun testCase2() {
        assertEquals(6, day.part1(testInput2))
    }
    @Test
    fun testCase3() {
        assertEquals(6, day.part2(testInput3))
    }
}