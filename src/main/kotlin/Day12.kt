import java.io.File

class Day12 {
    fun part1(input: List<String>) : Int =
        input.sumOf { line ->
            val parts = line.split(" ")
            val springs = parts.first()
            val groups = parts.last().split(",").map { it.toInt() }
            val corrupted = springs.indices.filter { springs[it] == '?' }
            var regex = "^\\.*"
            groups.forEachIndexed { index, number ->
                val str = "#".repeat(number)
                regex += "($str)"
                if (index < groups.size - 1) {
                    regex+="\\.+"
                }
            }
            regex += "\\.*$"
            val permutations = permutate(springs, corrupted, mutableSetOf(springs.replace('?', '.')))
            permutations.filter { it.matches(Regex(regex)) }.size
    }

    private fun permutate(springs: String, corrupted: List<Int>, permutations: MutableSet<String>): Set<String> {
        for (it in corrupted) {
            val newSprings = springs.replace('?', '.').replaceRange(it, it + 1, "#")
            if (permutations.contains(newSprings)) {
                continue
            }
            permutations.add(newSprings)
            permutate(newSprings, corrupted, permutations)
        }
        return permutations
    }

    companion object {
        private val day = Day12()
        private val input = File("src/main/resources/day12.txt").readLines()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
        }
    }
}