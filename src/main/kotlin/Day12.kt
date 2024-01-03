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

    private val cache = hashMapOf<Pair<String, List<Int>>, Long>()

    fun part2(input: List<String>): Long =
        input.sumOf { line ->
            val parts = line.split(" ")
            val springs = List(5) { parts.first() }.joinToString("?")
            val groups = List(5) { parts.last() }.joinToString(",").split(",").map { it.toInt() }
            permutate(springs, groups)
        }

    private fun permutate(springs: String, groups: List<Int>): Long {
        if (groups.isEmpty()) {
            return if ("#" in springs) 0 else 1
        }
        if (springs.isEmpty()) {
            return 0
        }
        return cache.getOrPut(springs to groups) {
            var count = 0L
            if (springs.first() in ".?") {
                count += permutate(springs.drop(1), groups)
            }
            val group = groups.first()
            if (springs.first() in "#?" && group <= springs.length && "." !in springs.take(group) && (group == springs.length || springs[group] != '#')) {
                count += permutate(springs.drop(group + 1), groups.drop(1))
            }
            count
        }
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
            println(day.part2(input))
        }
    }
}