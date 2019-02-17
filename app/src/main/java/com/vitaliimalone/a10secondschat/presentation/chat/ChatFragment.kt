package com.vitaliimalone.a10secondschat.presentation.chat

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.vitaliimalone.a10secondschat.databinding.ChatFragmentBinding
import com.vitaliimalone.a10secondschat.presentation.base.BaseFragment
import com.vitaliimalone.a10secondschat.presentation.chat.common.MessagesAdapter
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatFragment : BaseFragment<ChatFragmentBinding>(com.vitaliimalone.a10secondschat.R.layout.chat_fragment) {
    companion object {
        private const val ARG_CHAT_ID = "ARG_CHAT_ID"

        fun newInstance(chatId: String): ChatFragment {
            val args = Bundle()
            args.putString(ARG_CHAT_ID, chatId)
            val fragment = ChatFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override val viewModel: ChatViewModel by viewModel()
    private val messagesAdapter by lazy { MessagesAdapter(viewModel) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getChat(arguments!!.getString(ARG_CHAT_ID)!!)
        binding.apply {
            viewModel = this@ChatFragment.viewModel
            messagesRecyclerView.adapter = messagesAdapter
            val layoutManager = LinearLayoutManager(requireContext())
            layoutManager.stackFromEnd = true
            messagesRecyclerView.layoutManager = layoutManager
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        }
        setupObservers()
        setupKeyboardListener()
    }

    private fun setupObservers() {
        viewModel.apply {
            chat.observe(viewLifecycleOwner, Observer {
                binding.chat = it
                messagesAdapter.messages = it.messages
                scrollToBottom()
            })
            clearEditText.observe(viewLifecycleOwner, Observer {
                binding.chatBoxEditText.text?.clear()
            })
        }
    }

    private fun setupKeyboardListener() {
        KeyboardVisibilityEvent.setEventListener(requireActivity()) {
            if (it) scrollToBottom()
        }
    }

    private fun scrollToBottom() {
        binding.messagesRecyclerView.scrollToPosition(messagesAdapter.messages.lastIndex)
    }
}
