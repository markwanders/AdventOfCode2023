import java.io.File

class Day13 {
    fun part1(input: String): Int {
        val patterns = input.split("\r\n\r\n")
        return patterns.sumOf { pattern ->
            val rows = pattern.split("\n").map { it.trim() }
            val y = findReflection(rows)
            val columns = rows.first().indices.map { readColumn(rows, it) }
            val x = findReflection(columns)
            x + 100 * y
        }
    }

    fun part2(input: String): Int {
        val patterns = input.split("\r\n\r\n")
        return patterns.sumOf { pattern ->
            val rows = pattern.split("\n").map { it.trim() }
            val y = findSmudgedReflection(rows)
            val columns = rows.first().indices.map { readColumn(rows, it) }
            val x = findSmudgedReflection(columns)
            x + 100 * y
        }
    }

    private fun findReflection(input: List<String>) = (1..<input.size).sumOf { i ->
        val left = input.subList(0, i)
        val right = input.subList(i, input.size)
        val max = minOf(left.size, right.size)
        val trimmedLeft = left.takeLast(max)
        val trimmedRight = right.take(max).reversed()
        if (trimmedRight == trimmedLeft) {
            i
        } else {
            0
        }
    }

    private fun findSmudgedReflection(input: List<String>) = (1..<input.size).sumOf { i ->
        val left = input.subList(0, i)
        val right = input.subList(i, input.size)
        val max = minOf(left.size, right.size)
        val trimmedLeft = left.takeLast(max)
        val trimmedRight = right.take(max).reversed()
        var misses = 0
        for (j in trimmedRight.indices) {
            for (k in trimmedRight[j].indices) {
                if (trimmedRight[j][k] != trimmedLeft[j][k]) {
                    misses++
                }
            }
        }
        if (misses == 1) {
            i
        } else {
            0
        }
    }

    private fun readColumn(lines: List<String>, number: Int): String =
        lines.map { line -> line[number] }.joinToString("")

    companion object {
        private val day = Day13()
        private val input = File("src/main/resources/day13.txt").readText()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
            println(day.part2(input))
        }
    }
}