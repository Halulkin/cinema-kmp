package org.halulkin.core.utils

import kotlin.math.round

fun Double.toOneDecimalString(): String {
    val multiplier = 10.0
    val roundedValue = round(this * multiplier) / multiplier
    return roundedValue.toString()
}

fun Int.formatVoteCount(): String {
    return when {
        this > 999 -> {
            "${this / 1000}k"
        }

        else -> "$this"
    }
}
