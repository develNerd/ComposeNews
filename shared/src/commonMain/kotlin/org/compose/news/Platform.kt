package org.compose.news

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform