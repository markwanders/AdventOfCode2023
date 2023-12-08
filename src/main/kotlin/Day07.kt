import java.io.File
import kotlin.math.pow

class Day07 {
    fun part1(input: List<String>): Int {
        val parts = input.map { it.split(" ") }
        val hands = parts.associateBy({ it.first() }, { it.last().toInt() }).toSortedMap(cardComparator())
        return hands.entries.mapIndexed { index, mutableEntry -> (1 + index) * mutableEntry.value }.sum()
    }

    fun part2(input: List<String>): Int {
        val parts = input.map { it.split(" ") }
        val hands = parts.associateBy({ it.first() }, { it.last().toInt() }).toSortedMap(cardComparator(true))
        return hands.entries.mapIndexed { index, mutableEntry -> (1 + index) * mutableEntry.value }.sum()
    }

    private fun cardComparator(part2: Boolean = false): Comparator<String> {
        return Comparator { a, b ->
            val valA = valueOfHand(a, part2)
            val valB = valueOfHand(b, part2)
            when {
                valA > valB -> 1
                valB > valA -> -1
                else -> compareByCardInOrder(a, b, part2)
            }
        }
    }

    private fun valueOfHand(hand: String, part2: Boolean = false): Int {
        val combos =
            hand.toList().groupingBy { char -> char }.eachCount().mapKeys { (key, _) -> valueOfCard(key, part2) }
                .toMutableMap()
        if (part2 && hand.contains("J")) {
            val countJ = combos[1]
            val fourOfAKind = combos.entries.firstOrNull { it.value == 4 && it.key != 1}
            val threeOfAKind = combos.entries.firstOrNull { it.value == 3 && it.key != 1}
            val highPair = combos.entries.filter { it.value == 2 && it.key != 1}.maxByOrNull { entry -> entry.value }
            val highCard = combos.entries.maxBy { it.key }
            when (countJ) {
                1 ->
                    when {
                        fourOfAKind != null -> {
                            combos[fourOfAKind.key] = fourOfAKind.value + 1
                            combos[1] = 0
                        }

                        threeOfAKind != null -> {
                            combos[threeOfAKind.key] = threeOfAKind.value + 1
                            combos[1] = 0
                        }

                        highPair != null -> {
                            combos[highPair.key] = highPair.value + 1
                            combos[1] = 0
                        }

                        else -> {
                            combos[highCard.key] = highCard.value + 1
                            combos[1] = 0
                        }
                    }

                2 ->
                    when {
                        threeOfAKind != null -> {
                            combos[threeOfAKind.key] = threeOfAKind.value + 2
                            combos[1] = 0
                        }

                        highPair != null -> {
                            combos[highPair.key] = highPair.value + 2
                            combos[1] = 0
                        }

                        else -> {
                            combos[highCard.key] = highCard.value + 2
                            combos[1] = 0
                        }
                    }

                3 ->
                    when {
                        highPair != null -> {
                            combos[highPair.key] = highPair.value + 3
                            combos[1] = 0
                        }

                        else -> {
                            combos[highCard.key] = highCard.value + 3
                            combos[1] = 0
                        }
                    }

                4 -> {
                    combos[highCard.key] = 5
                    combos[1] = 0
                }
            }
        }
        return combos.values.filter { it > 0 }.sumOf { 10.0.pow(it).toInt() }
    }

    private fun valueOfCard(card: Char, part2: Boolean = false): Int {
        return if (card.isDigit()) {
            card.digitToInt()
        } else {
            when (card) {
                'A' -> 14
                'K' -> 13
                'Q' -> 12
                'J' -> if (part2) 1 else 11
                'T' -> 10
                else -> throw Exception("Unknown card value")
            }
        }
    }

    private fun compareByCardInOrder(a: String, b: String, part2: Boolean = false) =
        when {
            valueOfCard(a[0], part2) > valueOfCard(b[0], part2) -> 1
            valueOfCard(b[0], part2) > valueOfCard(a[0], part2) -> -1
            else -> when {
                valueOfCard(a[1], part2) > valueOfCard(b[1], part2) -> 1
                valueOfCard(b[1], part2) > valueOfCard(a[1], part2) -> -1
                else -> when {
                    valueOfCard(a[2], part2) > valueOfCard(b[2], part2) -> 1
                    valueOfCard(b[2], part2) > valueOfCard(a[2], part2) -> -1
                    else -> when {
                        valueOfCard(a[3], part2) > valueOfCard(b[3], part2) -> 1
                        valueOfCard(b[3], part2) > valueOfCard(a[3], part2) -> -1
                        else -> when {
                            valueOfCard(a[4], part2) > valueOfCard(b[4], part2) -> 1
                            valueOfCard(b[4], part2) > valueOfCard(a[4], part2) -> -1
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
            println(day.part2(input))
        }
    }
}