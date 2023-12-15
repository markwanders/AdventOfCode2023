import java.io.File

class Day15 {
    data class Lens(val label: String, var focal: Int)

    fun part1(input: String): Int {
        val steps = input.split(",")
        return steps.sumOf { hash(it) }
    }

    fun part2(input: String): Int {
        val steps = input.split(",")
        val boxes = List(size = 256) { mutableSetOf<Lens>() }
        steps.forEach { step ->
            val opIndex = step.indexOfAny(listOf("-", "="))
            val operation = step[opIndex]
            val lensBuilder = StringBuilder(step)
            lensBuilder.setCharAt(opIndex, ' ')
            val lens = lensBuilder.toString()
            val label = lens.split(" ").first()
            val box = hash(label)
            if (operation == '-') {
                boxes[box].removeIf { it.label == label }
            } else if (operation == '=') {
                val focalLength = lens.split(" ").last().toInt()
                if (boxes[box].any { it.label == label }) {
                    boxes[box].first { it.label == label }.focal = focalLength
                } else {
                    boxes[box].add(Lens(label, focalLength))
                }

            }
        }
        return boxes.mapIndexed { i, box -> (i + 1) * boxValue(box) }.sum()
    }

    private fun hash(string: String): Int {
        var currentValue = 0
        for (char in string) {
            val asciiCode = char.code
            currentValue += asciiCode
            currentValue *= 17
            currentValue %= 256
        }
        return currentValue
    }

    private fun boxValue(lenses: Set<Lens>): Int =
        lenses.mapIndexed { index, lens -> (index + 1) * lens.focal }.sum()

    companion object {
        private val day = Day15()
        private val input = File("src/main/resources/day15.txt").readText()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
            println(day.part2(input))
        }
    }
}