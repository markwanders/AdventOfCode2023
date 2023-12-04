import java.io.File
import kotlin.math.pow

class Day04 {
    private val winningRegex = Regex("^Card\\s+\\d+: (?<winning>[\\d\\s]+) | [\\d\\s]+\$")
    private val haveRegex = Regex("^Card\\s+\\d+: [\\d\\s] | (?<have>[\\d\\s]+)\$")

    fun part1(input: List<String>): Int =
        input.sumOf { line ->
            val intersect = winningNumbers(line)
            if (intersect > 0) 2.0.pow(intersect - 1.0).toInt() else 0
        }

    fun part2(input: List<String>): Int {
        val cards = List(input.size) { index -> index to 1 }.toMap().toMutableMap()
        input.forEachIndexed { cardNumber, card ->
            val cardsWon = winningNumbers(card)
            repeat(cardsWon) {
                cards[cardNumber + it + 1] = cards[cardNumber + it + 1]!! + cards[cardNumber]!!
            }
        }
        return cards.values.sum()
    }

    private fun winningNumbers(card: String): Int {
        val winning = winningRegex.find(card)?.groups?.get("winning")?.value?.split(" ")?.filter { it.isNotBlank() }
            ?.map { it.toInt() }?.toSet() ?: emptySet()
        val have = haveRegex.find(card)?.groups?.get("have")?.value?.split(" ")?.filter { it.isNotBlank() }
            ?.map { it.toInt() }?.toSet() ?: emptySet()
        return winning.intersect(have).size
    }

    companion object {
        private val input = File("src/main/resources/day04.txt").readLines()
        private val day = Day04()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
            println(day.part2(input))
        }
    }
}