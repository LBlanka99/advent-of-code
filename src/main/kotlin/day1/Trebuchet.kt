package day1

import java.io.File

fun main() {
    println(getSumOfCalibrationValues("src/main/kotlin/day1/input.txt"))
}

fun getSumOfCalibrationValues(filename : String) : Int {
    val data = File(filename).readText().trim()
    val list = data.split("\n")

    var sum = 0

    for (line in list) {
        var firstNumber = 0
        var currentLastNumber : Int? = null
        var foundNumber = false

        for (char in line) {
            if (char.isDigit()) {
                if (!foundNumber) {
                    firstNumber = char.toString().toInt()
                    foundNumber = true
                } else {
                    currentLastNumber = char.toString().toInt()
                }
            }
        }

        sum += if (currentLastNumber != null) {
            (10 * firstNumber) + currentLastNumber
        } else {
            (10 * firstNumber) + firstNumber
        }
    }
    return sum
}