import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day15Test {
    private val day = Day15()
    private val testInput1 = """
        rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7
    """.trimIndent()

    @Test
    fun testCase1() {
        assertEquals(1320, day.part1(testInput1))
    }
    @Test
    fun testCase2() {
        assertEquals(145, day.part2(testInput1))
    }
}