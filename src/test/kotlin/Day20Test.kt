import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day20Test {
    private val day = Day20()
    private val testInput1 = """
        broadcaster -> a, b, c
        %a -> b
        %b -> c
        %c -> inv
        &inv -> a
    """.trimIndent().split("\n")
    private val testInput2 = """
        broadcaster -> a
        %a -> inv, con
        &inv -> b
        %b -> con
        &con -> output
    """.trimIndent().split("\n")
    @Test
    fun testCase1() {
        assertEquals(32000000, day.part1(testInput1))
    }
    @Test
    fun testCase2() {
        assertEquals(11687500, day.part1(testInput2))
    }
}