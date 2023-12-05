import java.io.File

class Day05 {
    class Mapping(val destination: Long, val source: Long, val length: Long)
    fun part1(input: String): Long {
        val parts = input.split("\n\n")
        val seeds = parts[0].substringAfter("seeds: ").split(" ").map { it.toLong() }
        val seedToSoil = generateMapping(parts[1])
        val soilToFertilizer = generateMapping(parts[2])
        val fertilizerToWater = generateMapping(parts[3])
        val waterToLight = generateMapping(parts[4])
        val lightToTemperature = generateMapping(parts[5])
        val temperatureToHumidity = generateMapping(parts[6])
        val humidityToLocation = generateMapping(parts[7])
        val soils = doMapping(seeds, seedToSoil)
        val fertilizers = doMapping(soils, soilToFertilizer)
        val waters = doMapping(fertilizers, fertilizerToWater)
        val lights = doMapping(waters, waterToLight)
        val temperature = doMapping(lights, lightToTemperature)
        val humidity = doMapping(temperature, temperatureToHumidity)
        val location = doMapping(humidity, humidityToLocation)
        return location.min()
    }

    private fun generateMapping(part: String): List<Mapping> =
        part.split("\n").drop(1).filter { it.isNotBlank() }.map { line ->
            val nrs = line.split(" ").map { it.toLong() }
            Mapping(nrs[0], nrs[1], nrs[2])
        }

    private fun doMapping(input: List<Long>, mappings: List<Mapping>) =
        input.map { seed ->
            val mapping =
                mappings.firstOrNull { mapping -> mapping.source <= seed && seed <= (mapping.source + mapping.length) }
            if(mapping != null) {
                seed + (mapping.destination - mapping.source)
            } else {
                seed
            }
    }
    companion object {
        private val day = Day05()
        private val input = File("src/main/resources/day05.txt").readText()

        @JvmStatic
        fun main(args: Array<String>) {
            println(day.part1(input))
        }
    }
}