package com.vitaliimalone.a10secondschat.presentation.home

import androidx.lifecycle.MutableLiveData
import com.vitaliimalone.a10secondschat.presentation.MainRouter
import com.vitaliimalone.a10secondschat.presentation.base.BaseViewModel

class HomeViewModel(
        private val mainRouter: MainRouter
) : BaseViewModel() {
    val showDialog by lazy { MutableLiveData<Boolean>() }

    fun onFabClick() {
        showDialog.value = true
    }

    fun onCancelDialogClick() {
        showDialog.value = false
    }

    fun onCreateDialogClick(title: String) {
        showDialog.value = false
    }
}
