import java.io.File

class Day12 {
    fun part1(input: List<String>) : Int =
        input.sumOf { line ->
            val parts = line.split(" ")
            val springs = parts.first()
            val groups = parts.last().split(",").map { it.toInt() }
            val corrupted = springs.indices.filter { springs[it] == '?' }
            val permutations = permutate(springs, corrupted, mutableSetOf(springs.replace('?', '.')), groups.sum())
            permutations.map { toPermutation(it) }.filter { permutation -> permutation == groups }.size
    }

    private fun permutate(springs: String, corrupted: List<Int>, permutations: MutableSet<String>, maxSprings: Int): Set<String> {
        for (it in corrupted) {
            if(springs.count { it == '#' } < maxSprings) {
                val newSprings = springs.replace('?', '.').replaceRange(it, it + 1, "#")
                if (permutations.contains(newSprings)) {
                    continue
                }
                permutations.add(newSprings)
                permutate(newSprings, corrupted, permutations, maxSprings)
            }
        }
        return permutations
    }

    private fun toPermutation(spring: String) : List<Int> =
        spring.split(".").filter{ it.isNotEmpty() }.map { it.length }


    companion object {
        private val day = Day12()
        private val input = File("src/main/resources/day12.txt").readLines()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
        }
    }
}