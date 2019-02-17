package com.vitaliimalone.a10secondschat.presentation.home.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.a10secondschat.R
import com.vitaliimalone.a10secondschat.databinding.ListItemChatBinding
import com.vitaliimalone.a10secondschat.domain.models.Chat
import com.vitaliimalone.a10secondschat.presentation.base.BindingViewHolder
import com.vitaliimalone.a10secondschat.presentation.home.HomeViewModel

class ChatAdapter(
        private val viewModel: HomeViewModel
) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    var list: List<Chat> = emptyList()
        set(value) {
            DiffUtil.calculateDiff(ChatDiffUtilCallback(field, value)).dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListItemChatBinding>(inflater, R.layout.list_item_chat, parent, false)
        return ChatViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(list[position], viewModel)
    }

    class ChatViewHolder(
            private val binding: ListItemChatBinding
    ) : BindingViewHolder<Chat, HomeViewModel>(binding.root) {
        override fun bind(item: Chat, viewModel: HomeViewModel) {
            binding.item = item
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }
}
