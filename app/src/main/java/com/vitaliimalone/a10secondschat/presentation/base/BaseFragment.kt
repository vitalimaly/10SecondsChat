package com.vitaliimalone.a10secondschat.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<BINDING : ViewDataBinding>(private val layoutResourceId: Int) : Fragment() {

    protected lateinit var binding: BINDING
    protected abstract val viewModel: BaseViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return if (layoutResourceId > 0) {
            binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
            binding.lifecycleOwner = this
            binding.root
        } else {
            null
        }
    }

}