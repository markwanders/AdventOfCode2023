import java.io.File

class Day20 {
    data class Module(
        val name: String,
        val type: String,
        val targets: List<String>,
        val inputs: MutableMap<String, Int>,
        var isOn: Boolean = false
    )

    data class Pulse(val highLow: Int, val to: String, val from: String)

    private val moduleRegex = Regex("^(?<type>[&%]*)(?<name>[a-z]+) -> (?<targets>[a-z, ]+)\$")
    fun part1(input: List<String>): Int {
        val modules = input.map { line ->
            val found = moduleRegex.find(line)!!.groups
            val type = found["type"]?.value.orEmpty()
            val name = found["name"]?.value!!
            val targets = found["targets"]?.value!!.split(", ")
            Module(name, type, targets, mutableMapOf())
        }
        modules.filter { it.type == "&" }.forEach { module ->
            modules.filter { module.name in it.targets }.forEach { inputModule -> module.inputs[inputModule.name] = 0 }
        }
        val modulesMap = modules.associateBy { it.name }
        var lowCount = 0
        var highCount = 0
        repeat(1000) {
            val queue = mutableListOf(Pulse(0, "broadcaster", "button"))
            while (queue.isNotEmpty()) {
                val pulse = queue.removeFirst()
                val pulseType = pulse.highLow
                if (pulse.highLow == 1) highCount++ else lowCount++
                val receivingModule = modulesMap[pulse.to]
                when (receivingModule?.type) {
                    "%" ->
                        if (pulseType == 0) {
                            val nextPulse = if (receivingModule.isOn) 0 else 1
                            receivingModule.isOn = !receivingModule.isOn
                            receivingModule.targets.forEach { queue.add(Pulse(nextPulse, it, receivingModule.name)) }
                        }

                    "&" -> {
                        receivingModule.inputs[pulse.from] = pulseType
                        if (receivingModule.inputs.values.all { it == 1 }) {
                            receivingModule.targets.forEach { queue.add(Pulse(0, it, receivingModule.name)) }
                        } else {
                            receivingModule.targets.forEach { queue.add(Pulse(1, it, receivingModule.name)) }
                        }
                    }

                    else -> receivingModule?.targets?.forEach { queue.add(Pulse(pulseType, it, receivingModule.name)) }

                }
            }
        }
        return lowCount * highCount
    }


    companion object {
        private val day = Day20()
        private val input = File("src/main/resources/day20.txt").readLines()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
        }
    }
}