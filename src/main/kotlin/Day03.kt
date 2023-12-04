import java.io.File

class Day03(input: List<String>) {
    private val allNumbers = mutableMapOf<Int, Map<IntRange, Int>>()
    private val allSymbols = mutableMapOf<Int, Map<Int, Char>>()

    init {
        for ((y, line) in input.withIndex()) {
            allNumbers[y] = Regex("\\d+").findAll(line).associateBy({ it.range }, { it.value.toInt() })
            allSymbols[y] = Regex("[^\\d\\s.:]").findAll(line).associateBy({ it.range.first }, { it.value.first() })
        }
    }

    fun part1(): Int {
        val adjacentNumbers = allNumbers.map { (y, numbers) ->
            numbers.entries.filter {
                anyAdjacent(
                    it.key,
                    allSymbols[y]?.keys,
                    allSymbols[y - 1]?.keys,
                    allSymbols[y + 1]?.keys
                )
            }.sumOf { it.value }
        }
        return adjacentNumbers.sum()
    }

    private fun anyAdjacent(number: IntRange, row: Set<Int>?, previousRow: Set<Int>?, nextRow: Set<Int>?): Boolean {
        return row?.any { number.contains(it + 1) || number.contains(it - 1) } ?: false ||
                previousRow?.any { number.contains(it) || number.contains(it + 1) || number.contains(it - 1) } ?: false ||
                nextRow?.any { number.contains(it) || number.contains(it + 1) || number.contains(it - 1) } ?: false
    }

    private fun calculateRatio(
        gears: Set<Int>,
        row: Map<IntRange, Int>,
        previousRow: Map<IntRange, Int>?,
        nextRow: Map<IntRange, Int>?
    ): Int {
        return gears.sumOf { x ->
            val sameRowAdjacent = row.keys.filter { intRange -> intRange.last == x - 1 || intRange.first == x + 1 }
            val previousRowAdjacent = previousRow?.keys?.filter { intRange ->
                intRange.contains(x) || intRange.contains(x + 1) || intRange.contains(x - 1)
            } ?: emptySet()
            val nextRowAdjacent = nextRow?.keys?.filter { intRange ->
                intRange.contains(x) || intRange.contains(x + 1) || intRange.contains(x - 1)
            } ?: emptySet()
            val allAdjacentNumbers = sameRowAdjacent.map { row[it] }
                .plus(previousRowAdjacent.map { previousRow?.get(it)!! }
                    .plus(nextRowAdjacent.map { nextRow?.get(it)!! }))
            if (allAdjacentNumbers.size == 2) {
                allAdjacentNumbers.reduce { acc, i -> acc!! * i!! }!!
            } else {
                0
            }
        }
    }

    fun part2(): Int {
        val allGears =
            allSymbols.entries.associateBy({ it.key }, { it.value.filter { entry -> entry.value == '*' }.keys })
        return allGears.map { (y, gears) ->
            calculateRatio(
                gears,
                allNumbers[y]!!,
                allNumbers[y - 1],
                allNumbers[y + 1]
            )
        }.sum()
    }

    companion object {
        private val input = File("src/main/resources/day03.txt").readLines()
        private val day = Day03(input)

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1())
            println(day.part2())
        }
    }
}