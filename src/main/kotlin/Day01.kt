import java.io.File

private val input = File("src/main/resources/day01.txt").readLines()

fun main() {
    println(input)
    val answer = input.map { line -> line.filter { char -> char.isDigit() } }.sumOf { string: String ->
        (string.first() + string.last().toString()).toInt()
    }
    println(answer)
}