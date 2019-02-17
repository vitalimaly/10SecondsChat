package com.vitaliimalone.a10secondschat.presentation.utils

import kotlin.random.Random
import kotlin.random.nextInt

private const val ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyz "
fun getRandomString(): String {
    val randomLength = Random.nextInt(5..20)
    val sb = StringBuilder(randomLength)
    for (i in 0 until randomLength)
        sb.append(ALLOWED_CHARACTERS[Random.nextInt(ALLOWED_CHARACTERS.length)])
    return sb.toString()
}
