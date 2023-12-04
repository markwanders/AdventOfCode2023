import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day03Test {

    private val testInput1= """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...$.*....
        .664.598..
    """.trimIndent().split("\n")
    private val testInput2 = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...$.*....
        .664.598..
    """.trimIndent().split("\n")

    @Test
    fun testCase1() {
        val day = Day03(testInput1)

        assertEquals(4361, day.part1())
    }

    @Test
    fun testCase2() {
        val day = Day03(testInput2)

        assertEquals(467835, day.part2())
    }

}