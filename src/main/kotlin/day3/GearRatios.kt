package day3

import java.io.File

fun main() {
    println(getSumOfPartNumber("src/main/kotlin/day3/input.txt"))
}

fun getSumOfPartNumber(filename: String): Int {
    val schematic = File(filename).readLines()

    var sum = 0

    for (row in schematic.indices) {
        var minCol = 0
        for (col in schematic[0].indices) {
            if (col < minCol) {
                continue
            }
            val currentChar = schematic[row][col]

            if (currentChar.isDigit() && hasAdjacentSymbol(row, col, schematic)) {
                sum += getTheWholeNumber(row, col, schematic)
                minCol = getEndIndexOfNumber(row, col, schematic) + 1
            }
        }
    }
    return sum
}

fun hasAdjacentSymbol(row: Int, col: Int, schematic: List<String>): Boolean {
    for (i in -1..1) {
        for (j in -1..1) {
            try {
                if (!schematic[row + i][col + j].isDigit() && schematic[row + i][col + j] != '.') {
                    return true
                }
            } catch (e: IndexOutOfBoundsException) {
                continue
            }
        }
    }
    return false
}

fun getTheWholeNumber(row: Int, col: Int, schematic: List<String>): Int {
    val startAt = getStartIndexOfNumber(row, col, schematic)
    val endAt = getEndIndexOfNumber(row, col, schematic)
    return schematic[row].substring(startAt, endAt + 1).toInt()
}

fun getStartIndexOfNumber(row: Int, col: Int, schematic: List<String>): Int {
    var prevCol = col - 1
    while (prevCol > -1 && schematic[row][prevCol].isDigit()) {
        prevCol -= 1
    }
    return prevCol + 1
}

fun getEndIndexOfNumber(row: Int, col: Int, schematic: List<String>): Int {
    var nextCol = col + 1
    while (nextCol < schematic[0].length && schematic[row][nextCol].isDigit()) {
        nextCol += 1
    }
    return nextCol - 1
}