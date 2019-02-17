package com.vitaliimalone.a10secondschat.presentation.chat.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.a10secondschat.R
import com.vitaliimalone.a10secondschat.databinding.ListItemMessageReceivedBinding
import com.vitaliimalone.a10secondschat.databinding.ListItemMessageSentBinding
import com.vitaliimalone.a10secondschat.domain.models.Chat
import com.vitaliimalone.a10secondschat.presentation.base.BindingViewHolder
import com.vitaliimalone.a10secondschat.presentation.chat.ChatViewModel

class MessagesAdapter(
        private val viewModel: ChatViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var messages: List<Chat.Message> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            Chat.Message.MessageType.SENT.ordinal -> {
                val binding = DataBindingUtil.inflate<ListItemMessageSentBinding>(inflater,
                        R.layout.list_item_message_sent, parent, false)
                MessageSentViewHolder(binding)
            }
            Chat.Message.MessageType.RECEIVED.ordinal -> {
                val binding = DataBindingUtil.inflate<ListItemMessageReceivedBinding>(inflater,
                        R.layout.list_item_message_received, parent, false)
                MessageReceivedViewHolder(binding)
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int = messages.size

    override fun getItemViewType(position: Int): Int {
        return messages[position].type.ordinal
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MessageSentViewHolder -> {
                holder.bind(messages[position], viewModel)
            }
            is MessageReceivedViewHolder -> {
                holder.bind(messages[position], viewModel)
            }
            else -> throw IllegalArgumentException()
        }
    }

    class MessageSentViewHolder(
            private val binding: ListItemMessageSentBinding
    ) : BindingViewHolder<Chat.Message, ChatViewModel>(binding.root) {
        override fun bind(item: Chat.Message, viewModel: ChatViewModel) {
            binding.message = item
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }

    class MessageReceivedViewHolder(
            private val binding: ListItemMessageReceivedBinding
    ) : BindingViewHolder<Chat.Message, ChatViewModel>(binding.root) {
        override fun bind(item: Chat.Message, viewModel: ChatViewModel) {
            binding.message = item
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }
}
