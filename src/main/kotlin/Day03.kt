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

    private fun countAdjacent(gears: Set<Int>, row: Set<IntRange>?, previousRow: Set<IntRange>?, nextRow: Set<IntRange>?): Int {
        var adjacent = 0
        var factor = 1
        //TODO: should filter numbers on intranges that have an adjacent gear
        if (gears.any { gear -> row?.any { it.contains(gear + 1) } == true || row?.any { it.contains(gear + 1) } == true }) {
            adjacent++
            factor *= 1
        }
        if (gears.any { gear -> previousRow?.any { it.contains(gear) } == true || previousRow?.any { it.contains(gear + 1) } == true || previousRow?.any { it.contains(gear + 1) } == true }) {
            adjacent++
            factor += 1
        }
        if (gears.any { gear -> nextRow?.any { it.contains(gear) } == true || nextRow?.any { it.contains(gear + 1) } == true || nextRow?.any { it.contains(gear + 1) } == true }) {
            adjacent++
            factor += 1
        }
        return if (adjacent == 2) factor else 0
    }

    fun part2(): Int {
        val allGears = allSymbols.entries.associateBy({ it.key }, { it.value.filter { entry -> entry.value == '*' }.keys })
        return allGears.map { (y, gears) -> countAdjacent(gears, allNumbers[y]?.keys, allNumbers[y-1]?.keys, allNumbers[y+1]?.keys)}.sum()
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