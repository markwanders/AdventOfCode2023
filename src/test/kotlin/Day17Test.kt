import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day17Test {
    private val day = Day17()
    private val testInput1 = """
        2413432311323
        3215453535623
        3255245654254
        3446585845452
        4546657867536
        1438598798454
        4457876987766
        3637877979653
        4654967986887
        4564679986453
        1224686865563
        2546548887735
        4322674655533
    """.trimIndent().split("\n")

    @Test
    fun testCase1() {
        assertEquals(1320, day.part1(testInput1))
    }

}