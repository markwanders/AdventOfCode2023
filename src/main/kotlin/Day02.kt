import java.io.File

class Day02 {
    private val red = Regex("(?<number>\\d+) red")
    private val blue = Regex("(?<number>\\d+) blue")
    private val green = Regex("(?<number>\\d+) green")

    fun part1(input: List<String>): Int {
        val maxRed = 12
        val maxGreen = 13
        val maxBlue = 14
        return input.mapIndexed { i, line ->
            val possible = line.split(";").all { grab ->
                val countRed = count(red, grab)
                val countGreen = count(green, grab)
                val countBlue = count(blue, grab)
                countRed <= maxRed && countBlue <= maxBlue && countGreen <= maxGreen
            }
            if (possible) {
                1 + i
            } else {
                0
            }
        }.sum()
    }

    fun part2(input: List<String>): Int = input.sumOf { line ->
        val maxRed = line.split(";").maxOfOrNull { grab ->
            count(red, grab)
        }
        val maxGreen = line.split(";").maxOfOrNull { grab ->
            count(green, grab)
        }
        val maxBlue = line.split(";").maxOfOrNull { grab ->
            count(blue, grab)
        }
        maxRed!! * maxGreen!! * maxBlue!!
    }

    private fun count(color: Regex, line: String) =
        color.findAll(line).sumOf { matchResult ->
            matchResult.groups["number"]?.value?.toInt() ?: 0
        }

    companion object {
        private val day = Day02()
        private val input = File("src/main/resources/day02.txt").readLines()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
            println(day.part2(input))
        }
    }
}