import java.io.File

class Day19 {
    data class Rule(val category: String, val operation: String, val value: Int, val target: String)
    data class Workflow(val rules: List<Rule>, val default: String)

    fun part1(input: String): Int {
        val parts = input.split("\n\n")
        val workFlows = parts.first().split("\n")
        val ratings = parts.last().split("\n")
        val regex = Regex("^(?<name>[a-z]+)\\{(?<rules>.*)}\$")
        val ruleRegex = Regex("(?<category>[xmas])(?<operation>[<>])(?<value>\\d+):(?<target>[A-z]+)")
        val workFlowMap = mutableMapOf<String, Workflow>()
        workFlows.forEach { workFlow ->
            val name = regex.find(workFlow)?.groups?.get("name")?.value.orEmpty()
            val rules = regex.find(workFlow)?.groups?.get("rules")?.value.orEmpty().split(",")
            val mappedRules = rules.mapNotNull { rule ->
                val found = ruleRegex.find(rule)
                val category = found?.groups?.get("category")?.value.orEmpty()
                val operation = found?.groups?.get("operation")?.value.orEmpty()
                val value = found?.groups?.get("value")?.value.orEmpty()
                val target = found?.groups?.get("target")?.value.orEmpty()
                if (value.isNotEmpty()) {
                    Rule(category, operation, value.toInt(), target)
                } else {
                    null
                }
            }
            workFlowMap[name] = Workflow(mappedRules, rules.last())
        }
        val valueRegex = Regex("x=(?<x>\\d*),m=(?<m>\\d*),a=(?<a>\\d*),s=(?<s>\\d*)")
        return ratings.sumOf { rating ->
            val groups = valueRegex.find(rating)?.groups!!
            val mappedRatings = mapOf(
                "x" to groups["x"]!!.value.toInt(),
                "m" to groups["m"]!!.value.toInt(),
                "a" to groups["a"]!!.value.toInt(),
                "s" to groups["s"]!!.value.toInt()
            )
            var next = applyWorkflow(workFlowMap["in"]!!, mappedRatings)
            while (next in workFlowMap.keys) {
                next = applyWorkflow(workFlowMap[next]!!, mappedRatings)
            }
            if (next == "A") {
                mappedRatings.values.sum()
            } else {
                0
            }
        }
    }

    private fun applyWorkflow(workflow: Workflow, ratings: Map<String, Int>): String {
        val result = workflow.rules.firstNotNullOfOrNull { rule ->
            val match = ratings.entries.find { (category, value) ->
                rule.category == category && ((rule.operation == "<" && value < rule.value) || (rule.operation == ">" && value > rule.value))
            }
            if (match != null) {
                rule.target
            } else {
                null
            }
        }
        return result ?: workflow.default
    }

    companion object {
        private val day = Day19()
        private val input = File("src/main/resources/day19.txt").readText()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
        }
    }
}