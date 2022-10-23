package org.fido.fidonews

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform