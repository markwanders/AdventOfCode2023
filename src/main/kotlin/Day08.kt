import java.io.File

class Day08 {
    fun part1(input: String): Int {
        val parts = input.split("\r\n\r\n")
        val directions = parts.first()
        val nodes = parts.last().split("\n")
        val map = mutableMapOf<String, List<String>>()
        nodes.filter { it.isNotBlank() }.forEach{ node ->
            val labels = Regex("([A-Z]{3})").findAll(node).map { match -> match.value }.toList()
            map[labels.first()] = labels.drop(1)
        }
        var position = "AAA"
        var currentStep = 0
        var totalSteps = 0
        while(position != "ZZZ") {
            if (currentStep == directions.length ) currentStep = 0
            if (directions[currentStep] == 'R') {
                position = map[position]?.last()!!
            } else if (directions[currentStep] == 'L') {
                position = map[position]?.first()!!
            }
            currentStep++
            totalSteps++
        }
        return totalSteps
    }

    fun part2(input: String): Long {
        val parts = input.split("\r\n\r\n")
        val directions = parts.first()
        val nodes = parts.last().split("\n")
        val map = mutableMapOf<String, List<String>>()
        nodes.filter { it.isNotBlank() }.forEach{ node ->
            val labels = Regex("([\\dA-Z]{3})").findAll(node).map { match -> match.value }.toList()
            map[labels.first()] = labels.drop(1)
        }
        val positions = map.keys.filter { it.endsWith('A') }.associateWith { it }.toMutableMap()
        val stepsToReachEnd = mutableMapOf<String, Int>()
        var currentStep = 0
        var totalSteps = 0
        while(positions.keys.size != stepsToReachEnd.keys.size) {
            if (currentStep == directions.length ) currentStep = 0
            positions.forEach { (startPosition, position) ->
                if (directions[currentStep] == 'R') {
                    val nextPosition = map[position]?.last()!!
                    if (nextPosition.endsWith('Z')) stepsToReachEnd[startPosition] = totalSteps + 1
                    positions[startPosition] = nextPosition
                } else {
                    val nextPosition = map[position]?.first()!!
                    if (nextPosition.endsWith('Z')) stepsToReachEnd[startPosition] = totalSteps + 1
                    positions[startPosition] = nextPosition
                }
            }
            currentStep++
            totalSteps++
        }
        return lcm(stepsToReachEnd.values.map { it.toLong() })
    }

    private fun gcd(a: Long, b: Long): Long {
        var a = a
        var b = b
        while (b > 0) {
            val temp = b
            b = a % b
            a = temp
        }
        return a
    }

    private fun lcm(a: Long, b: Long): Long {
        return a * (b / gcd(a, b))
    }

    private fun lcm(input: List<Long>): Long {
        var result = input[0]
        for (i in 1..<input.size) result = lcm(result, input[i])
        return result
    }

    companion object {
        private val day = Day08()
        private val input = File("src/main/resources/day08.txt").readText()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
            println(day.part2(input))
        }
    }
}