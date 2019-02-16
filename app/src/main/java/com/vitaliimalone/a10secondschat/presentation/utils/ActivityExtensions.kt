package com.vitaliimalone.a10secondschat.presentation.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


fun AppCompatActivity.replaceWithoutBackStack(containerId: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(containerId, fragment, fragment.javaClass.simpleName)
        .commitAllowingStateLoss()
}

fun AppCompatActivity.replaceWithBackStack(containerId: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(containerId, fragment, fragment.javaClass.simpleName)
        .addToBackStack(fragment.javaClass.simpleName)
        .commitAllowingStateLoss()
}

fun AppCompatActivity.addWithoutBackStack(containerId: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .add(containerId, fragment, fragment.javaClass.simpleName)
        .commitAllowingStateLoss()
}

fun AppCompatActivity.addWithBackStack(containerId: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .add(containerId, fragment, fragment.javaClass.simpleName)
        .addToBackStack(fragment.javaClass.simpleName)
        .commitAllowingStateLoss()
}
