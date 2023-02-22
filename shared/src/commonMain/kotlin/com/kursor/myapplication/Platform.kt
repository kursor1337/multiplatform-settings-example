package com.kursor.myapplication

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform