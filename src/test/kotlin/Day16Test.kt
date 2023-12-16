import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day16Test {
    private val day = Day16()
    private val testInput1 = """
        .|...\....
        |.-.\.....
        .....|-...
        ........|.
        ..........
        .........\
        ..../.\\..
        .-.-/..|..
        .|....-|.\
        ..//.|....
    """.trimIndent().split("\n")

    @Test
    fun testCase1() {
        assertEquals(1320, day.part1(testInput1))
    }

}