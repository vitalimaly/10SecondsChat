package com.vitaliimalone.a10secondschat.presentation.home.common

import androidx.recyclerview.widget.DiffUtil
import com.vitaliimalone.a10secondschat.domain.models.Chat

class ChatDiffUtilCallback(
        private val old: List<Chat>,
        private val new: List<Chat>
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
