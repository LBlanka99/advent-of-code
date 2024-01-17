package day1

import java.io.File

fun main() {
    println(getSumOfCalibrationValues("src/main/kotlin/day1/input.txt"))
    println(getSumOfCalibrationValuesWithWords("src/main/kotlin/day1/input.txt"))
}

fun getSumOfCalibrationValues(filename : String) : Int {
    val list = File(filename).readLines()

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

fun getSumOfCalibrationValuesWithWords(filename: String) : Int {
    val list = File(filename).readLines()

    var sum = 0

    val numbers = arrayOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    for (line in list) {
        var indexOfCurrentFirstNumber = line.length
        var currentFirstNumber = 0
        var indexOfCurrentLastNumber = 0
        var currentLastNumber : Int? = null

        for (n in numbers.indices) {
            val indexOfNumber = line.indexOf(numbers[n])
            if (indexOfNumber > -1) {
                if (indexOfNumber < indexOfCurrentFirstNumber) {
                    currentFirstNumber = n + 1
                    indexOfCurrentFirstNumber = indexOfNumber
                }
                if (line.lastIndexOf(numbers[n]) > indexOfCurrentLastNumber) {
                    currentLastNumber = n + 1
                    indexOfCurrentLastNumber = line.lastIndexOf(numbers[n])
                }
            }
        }

        for (j in line.indices) {
            if (line[j].isDigit()) {
                if (j < indexOfCurrentFirstNumber) {
                    currentFirstNumber = line[j].toString().toInt()
                    //indexOfCurrentFirstNumber = j
                }
                if (j > indexOfCurrentLastNumber) {
                    currentLastNumber = line[j].toString().toInt()
                    indexOfCurrentLastNumber = j
                }
            }
        }

        sum += if (currentLastNumber != null) {
            (10 * currentFirstNumber) + currentLastNumber
        } else {
            (10 * currentFirstNumber) + currentFirstNumber
        }
    }
    return sum
}