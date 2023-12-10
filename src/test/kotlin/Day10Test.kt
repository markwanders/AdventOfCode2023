
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day10Test {
    private val day = Day10()
    private val testInput1 = """
        .....
        .S-7.
        .|.|.
        .L-J.
        .....
    """.trimIndent().split("\n")
    private val testInput2 = """
        ..F7.
        .FJ|.
        SJ.L7
        |F--J
        LJ...
    """.trimIndent().split("\n")
    private val testInput3 = """
        -L|F7
        7S-7|
        L|7||
        -L-J|
        L|-JF
    """.trimIndent().split("\n")
    private val testInput4 = """
        7-F7-
        .FJ|7
        SJLL7
        |F--J
        LJ.LJ
    """.trimIndent().split("\n")
    @Test
    fun testCase1() {
        assertEquals(4, day.part1(testInput1))
    }

    @Test
    fun testCase2() {
        assertEquals(8, day.part1(testInput2))
    }
    @Test
    fun testCase3() {
        assertEquals(4, day.part1(testInput3))
    }

    @Test
    fun testCase4() {
        assertEquals(8, day.part1(testInput4))
    }
}