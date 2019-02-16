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
import com.vitaliimalone.a10secondschat.presentation.base.BaseFragment
import com.vitaliimalone.a10secondschat.presentation.home.common.ChatAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeFragmentBinding>(R.layout.home_fragment) {
    companion object {
        fun newInstance() = HomeFragment()
    }

    override val viewModel: HomeViewModel by viewModel()
    private var dialog: AlertDialog? = null
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
            showDialog.observe(this@HomeFragment, Observer {
                if (it) showCreateNewChatDialog()
                else dialog?.dismiss()
            })
            allChats.observe(this@HomeFragment, Observer {
                chatAdapter.list = it
            })
        }
    }

    private fun showCreateNewChatDialog() {
        val dialogBinding = DataBindingUtil.inflate<CreateNewChatDialogBinding>(
                LayoutInflater.from(requireContext()), R.layout.create_new_chat_dialog, null, false)
        dialogBinding.viewModel = viewModel
        dialog = AlertDialog.Builder(requireContext())
                .setView(dialogBinding.root)
                .create()
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        dialog?.show()
    }
}
