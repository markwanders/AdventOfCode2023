import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day12Test {
    private val day = Day12()
    private val testInput1 = """
        ???.### 1,1,3
        .??..??...?##. 1,1,3
        ?#?#?#?#?#?#?#? 1,3,1,6
        ????.#...#... 4,1,1
        ????.######..#####. 1,6,5
        ?###???????? 3,2,1
    """.trimIndent().split("\n")

    @Test
    fun testCase1() {
        assertEquals(21, day.part1(testInput1))
    }
    @Test
    fun testCase2() {
        assertEquals(525152, day.part2(testInput1))
    }
}