package com.vitaliimalone.a10secondschat.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.vitaliimalone.a10secondschat.R
import com.vitaliimalone.a10secondschat.databinding.CreateNewChatDialogBinding
import com.vitaliimalone.a10secondschat.databinding.HomeFragmentBinding
import com.vitaliimalone.a10secondschat.domain.models.Chat
import com.vitaliimalone.a10secondschat.presentation.base.BaseFragment
import com.vitaliimalone.a10secondschat.presentation.home.common.ChatAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeFragmentBinding>(R.layout.home_fragment) {
    companion object {
        fun newInstance() = HomeFragment()
    }

    override val viewModel: HomeViewModel by viewModel()
    private var createNewChatDialog: AlertDialog? = null
    private var deleteChatDialog: AlertDialog? = null
    private val chatAdapter by lazy { ChatAdapter(viewModel) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.apply {
            viewModel = this@HomeFragment.viewModel
            chatRecyclerView.adapter = chatAdapter
            chatRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.apply {
            showCreateNewChatDialog.observe(viewLifecycleOwner, Observer {
                if (it) showCreateNewChatDialog()
                else createNewChatDialog?.dismiss()
            })
            allChats.observe(viewLifecycleOwner, Observer {
                chatAdapter.list = it
            })
            showDeleteChatDialog.observe(viewLifecycleOwner, Observer {
                showDeleteChatDialog(it)
            })
        }
    }

    private fun showCreateNewChatDialog() {
        val dialogBinding = DataBindingUtil.inflate<CreateNewChatDialogBinding>(
                LayoutInflater.from(requireContext()), R.layout.create_new_chat_dialog, null, false)
        dialogBinding.viewModel = viewModel
        createNewChatDialog = AlertDialog.Builder(requireContext())
                .setView(dialogBinding.root)
                .create()
        createNewChatDialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        createNewChatDialog?.show()
    }

    private fun showDeleteChatDialog(chat: Chat) {
        deleteChatDialog = AlertDialog.Builder(requireContext())
                .setTitle("Are you sure you want to delete this chat?")
                .setPositiveButton("Delete chat") { dialog, _ ->
                    viewModel.deleteChat(chat)
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
        deleteChatDialog?.show()
    }
}
