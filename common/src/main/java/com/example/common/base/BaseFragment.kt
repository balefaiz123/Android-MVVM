package com.example.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.common.BR
import com.example.common.entity.ResponseApp
import com.example.common.entity.ResponseError
import com.example.common.entity.ResponseLoading
import com.example.common.entity.ResponseSuccess

abstract class BaseFragment<VM : BaseViewModel, Binding : ViewDataBinding> : Fragment() {

    abstract val vm: VM
    lateinit var binding: Binding
    abstract val layoutResourceId: Int


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<Binding>(inflater, layoutResourceId, container, false)
        binding.setVariable(BR.vm, vm)
        binding.lifecycleOwner = this
        initBinding(binding)
        return binding.root
    }


    open fun initBinding(binding: Binding) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentFragment?.let {
            if (it is BaseFragment<*, *>) {
                vm.parent = it.vm
            }
        }
        vm.navigationtEvent.observe(this) {
            findNavController().navigate(it)
        }
        vm.popBackStackEvent.observe(this) {
            findNavController().popBackStack()
        }
    }

    fun <T> observeResponseData(
        data: MutableLiveData<ResponseApp<T>>,
        success: ((T) -> Unit),
        error: ((Throwable) -> Unit)?,
        loading: (() -> Unit)? = {}
    ) {
        data.observe(this) { response ->
            when (response) {
                is ResponseSuccess -> {
                    response.data?.let { success.invoke(it) }
                }
                is ResponseError -> {
                    response.error?.let {
                        error?.invoke(it)
                    }
                }
                is ResponseLoading -> {
                    loading?.invoke()
                }
            }
        }

    }
}
