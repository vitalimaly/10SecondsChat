package com.vitaliimalone.a10secondschat.presentation

import com.vitaliimalone.a10secondschat.R
import com.vitaliimalone.a10secondschat.presentation.home.HomeFragment
import com.vitaliimalone.a10secondschat.presentation.utils.replaceWithoutBackStack

class MainRouter(private val mainActivity: MainActivity) {
    fun navigateToHome() {
        mainActivity.replaceWithoutBackStack(R.id.mainContainer, HomeFragment.newInstance())
    }
}
