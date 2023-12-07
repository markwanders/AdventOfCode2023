import java.io.File
import kotlin.math.pow

class Day07 {
    fun part1(input: List<String>): Int {
        val parts = input.map { it.split(" ") }
        val cardComparator = Comparator<String> { a, b ->
            val valA = valueOfHand(a)
            val valB = valueOfHand(b)
            when {
                valA > valB -> 1
                valB > valA -> -1
                else -> compareByCardInOrder(a, b)
            }
        }
        val hands = parts.associateBy({it.first()}, {it.last().toInt()}).toSortedMap(cardComparator)
        return hands.entries.mapIndexed { index, mutableEntry -> (1 + index) * mutableEntry.value}.sum()
    }

    private fun valueOfHand(hand: String): Int {
        val combos = hand.toList().groupingBy { char -> char }.eachCount().mapKeys { (key, _) -> valueOfCard(key) }
        return combos.values.sumOf { 10.0.pow(it).toInt()}
    }

    private fun valueOfCard(card: Char): Int {
        return if(card.isDigit()) {
            card.digitToInt()
        } else {
            when(card) {
                'A' -> 14
                'K' -> 13
                'Q' -> 12
                'J' -> 11
                'T' -> 10
                else -> throw Exception("Unknown card value")
            }
        }
    }

    private fun compareByCardInOrder(a: String, b: String) =
        when {
            valueOfCard(a[0]) > valueOfCard(b[0]) -> 1
            valueOfCard(b[0]) > valueOfCard(a[0]) -> -1
            else -> when {
                valueOfCard(a[1]) > valueOfCard(b[1]) -> 1
                valueOfCard(b[1]) > valueOfCard(a[1]) -> -1
                else -> when {
                    valueOfCard(a[2]) > valueOfCard(b[2]) -> 1
                    valueOfCard(b[2]) > valueOfCard(a[2]) -> -1
                    else -> when {
                        valueOfCard(a[3]) > valueOfCard(b[3]) -> 1
                        valueOfCard(b[3]) > valueOfCard(a[3]) -> -1
                        else -> when {
                            valueOfCard(a[4]) > valueOfCard(b[4]) -> 1
                            valueOfCard(b[4]) > valueOfCard(a[4]) -> -1
                            else -> 0
                        }
                    }
                }
            }
    }
    companion object {
        private val day = Day07()
        private val input = File("src/main/resources/day07.txt").readLines()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
        }
    }
}