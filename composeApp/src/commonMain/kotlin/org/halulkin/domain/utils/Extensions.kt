package org.halulkin.domain.utils

fun Double.toOneDecimalString(): String {
    val multiplier = 10.0
    val roundedValue = kotlin.math.round(this * multiplier) / multiplier
    return roundedValue.toString()
}