import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day01Test {
    private val day = Day01()

    private val testInput1= """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
    """.trimIndent().split("\n")
    private val testInput2 = """
        two1nine
        eightwothree
        abcone2threexyz
        xtwone3four
        4nineeightseven2
        zoneight234
        7pqrstsixteen
    """.trimIndent().split("\n")
    @Test
    fun testCase1() {
        assertEquals(142, day.part1(testInput1))
    }
    @Test
    fun testCase2() {
        assertEquals(281, day.part2(testInput2))
    }
}