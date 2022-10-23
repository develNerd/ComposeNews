package org.paypay.fidonews

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform