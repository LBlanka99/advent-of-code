package day2

import java.io.File

fun main() {
    println(getSumOfPossibleGameIDs("src/main/kotlin/day2/input.txt"))
    println(getSumOfPowers("src/main/kotlin/day2/input.txt"))
}

fun getSumOfPossibleGameIDs(filename: String) :Int {
    val data = File(filename).readText().trim()
    val list = data.split("\n")
    val list2 = list.map { it.split(":")[1] }
    val list3 = list2.map { it.split(";").map { it.trim() } }
    val games = list3.map { it.map { it.split(",").map { it.trim().split(" ") } } }

    var sumOfIDs = 0

    for (i in games.indices) {
        val inTheBag = mapOf(
            "red" to 12,
            "green" to 13,
            "blue" to 14
        )

        var isPossible = true

        for (record in games[i]) {
            for (element in record) {
                val quantity = element[0].toInt()
                val color = element[1]

                if (quantity > inTheBag[color]!!) {
                    isPossible = false
                    break
                }
            }
        }

        if (isPossible) {
            sumOfIDs += i+1
        }
    }
    return sumOfIDs
}

fun getSumOfPowers(filename: String): Int {
    val data = File(filename).readText().trim()
    val list = data.split("\n")
    val list2 = list.map { it.split(":")[1] }
    val list3 = list2.map { it.split(";").map { it.trim() } }
    val games = list3.map { it.map { it.split(",").map { it.trim().split(" ") } } }

    var sumOfPowers = 0

    for (i in games.indices) {
        val inTheBag = mutableMapOf(
            "red" to 0,
            "green" to 0,
            "blue" to 0
        )

        for (record in games[i]) {
            for (element in record) {
                val quantity = element[0].toInt()
                val color = element[1]

                if (quantity > inTheBag[color]!!) {
                    inTheBag[color] = quantity
                }
            }
        }

        var power = 1
        inTheBag.values.forEach { power *= it }

        sumOfPowers += power
    }

    return sumOfPowers
}