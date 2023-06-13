package com.example.jivaproject.util

import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.Random

fun String.letters() = filter { it.isLetter() }

fun calculateLoyaltyIndex(isRegistered: Boolean): Double {
    return if (isRegistered) 1.12 else 0.98
}

fun String.changeTonnesToKg() = this.toInt() * 1000
fun calculateGrossPrice(loyaltyIndex: Double, pricePerKg: Double, weightInKg: Int): Double {
    return loyaltyIndex * pricePerKg * weightInKg
}

fun generateLoyaltyNumber(): String {
    val number = randomNumberGenerator()
    return "S$number"
}

fun randomNumberGenerator(): Int {
    val r = Random(System.currentTimeMillis())
    return (1 + r.nextInt(2)) * 10000 + r.nextInt(10000)
}
fun roundOff(value:Double): String {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.DOWN
    return df.format(value)
}