package com.vitaliimalone.a10secondschat.presentation.chat.common

import androidx.recyclerview.widget.DiffUtil
import com.vitaliimalone.a10secondschat.domain.models.Chat

class MessagesDiffUtilCallback(
        private val old: List<Chat.Message>,
        private val new: List<Chat.Message>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = old.size
    override fun getNewListSize(): Int = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition].id == new[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }
}
