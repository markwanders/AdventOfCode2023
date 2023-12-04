import java.io.File
import kotlin.math.pow

class Day04 {
    private val winningRegex = Regex("^Card\\s+\\d+: (?<winning>[\\d\\s]+) | [\\d\\s]+\$")
    private val haveRegex = Regex("^Card\\s+\\d+: [\\d\\s] | (?<have>[\\d\\s]+)\$")

    fun part1(input: List<String>): Int =
        input.sumOf { line ->
            val winning = winningRegex.find(line)?.groups?.get("winning")?.value?.split(" ")?.filter { it.isNotBlank() }
                ?.map { it.toInt() }?.toSet() ?: emptySet()
            val have = haveRegex.find(line)?.groups?.get("have")?.value?.split(" ")?.filter { it.isNotBlank() }
                ?.map { it.toInt() }?.toSet() ?: emptySet()
            val intersect = winning.intersect(have).size
            if (intersect >0) 2.0.pow(intersect - 1.0).toInt() else 0
        }
    companion object {
        private val input = File("src/main/resources/day04.txt").readLines()
        private val day = Day04()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
        }
    }
}