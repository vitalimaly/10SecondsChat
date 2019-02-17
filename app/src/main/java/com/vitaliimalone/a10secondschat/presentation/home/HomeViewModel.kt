package com.vitaliimalone.a10secondschat.presentation.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.vitaliimalone.a10secondschat.domain.interactors.HomeInteractor
import com.vitaliimalone.a10secondschat.domain.models.Chat
import com.vitaliimalone.a10secondschat.presentation.MainRouter
import com.vitaliimalone.a10secondschat.presentation.base.BaseViewModel
import com.vitaliimalone.a10secondschat.presentation.utils.SingleLiveEvent

class HomeViewModel(
        private val mainRouter: MainRouter,
        private val homeInteractor: HomeInteractor
) : BaseViewModel() {
    val showCreateNewChatDialog by lazy { SingleLiveEvent<Boolean>() }
    val showDeleteChatDialog by lazy { SingleLiveEvent<Chat>() }
    val allChats by lazy { MutableLiveData<List<Chat>>() }

    init {
        getAllChats()
    }

    private fun getAllChats() {
        autoDispose(homeInteractor.getAllChats()
                .subscribe({
                    allChats.value = it
                }, {
                    Log.e(HomeViewModel::class.java.simpleName, it.message, it)
                }))
    }

    fun onFabClick() {
        showCreateNewChatDialog.value = true
    }

    fun onCancelDialogClick() {
        showCreateNewChatDialog.value = false
    }

    fun onCreateDialogClick(title: String) {
        // todo add empty check
        val chat = Chat(title = title)
        autoDispose(homeInteractor.saveChat(chat)
                .subscribe({
                    showCreateNewChatDialog.value = false
                    mainRouter.navigateToChat(chat.id)
                }, {
                    Log.e(HomeViewModel::class.java.simpleName, it.message, it)
                }))
    }

    fun onChatClick(chat: Chat) {
        mainRouter.navigateToChat(chat.id)
    }

    fun onChatLongClick(chat: Chat): Boolean {
        showDeleteChatDialog.value = chat
        return true
    }

    fun deleteChat(chat: Chat) {
        autoDispose(homeInteractor.deleteChat(chat)
                .subscribe({
                }, {
                    Log.e(HomeViewModel::class.java.simpleName, it.message, it)
                }))
    }
}
