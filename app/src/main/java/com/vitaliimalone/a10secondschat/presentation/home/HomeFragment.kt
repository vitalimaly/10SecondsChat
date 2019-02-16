package com.vitaliimalone.a10secondschat.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.vitaliimalone.a10secondschat.R
import com.vitaliimalone.a10secondschat.databinding.CreateNewChatDialogBinding
import com.vitaliimalone.a10secondschat.databinding.HomeFragmentBinding
import com.vitaliimalone.a10secondschat.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeFragmentBinding>(R.layout.home_fragment) {
    override val viewModel: HomeViewModel by viewModel()
    private var dialog: AlertDialog? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.showDialog.observe(this, Observer {
            if (it) showCreateNewChatDialog()
            else dialog?.dismiss()
        })
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

    companion object {
        fun newInstance() = HomeFragment()
    }
}
