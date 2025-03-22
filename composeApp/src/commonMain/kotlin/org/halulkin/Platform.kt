package org.halulkin

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform