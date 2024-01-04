
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
    private val testInput6 = """
        .F----7F7F7F7F-7....
        .|F--7||||||||FJ....
        .||.FJ||||||||L7....
        FJL7L7LJLJ||LJ.L-7..
        L--J.L7...LJS7F-7L7.
        ....F-J..F7FJ|L7L7L7
        ....L7.F7||L7|.L7L7|
        .....|FJLJ|FJ|F7|.LJ
        ....FJL-7.||.||||...
        ....L---J.LJ.LJLJ...
    """.trimIndent().split("\n")
    private val testInput5 = """
        ...........
        .S-------7.
        .|F-----7|.
        .||.....||.
        .||.....||.
        .|L-7.F-J|.
        .|..|.|..|.
        .L--J.L--J.
        ...........
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
    @Test
    fun testCase5() {
        assertEquals(4, day.part2(testInput5))
    }
    @Test
    fun testCase6() {
        assertEquals(8, day.part2(testInput6))
    }
}