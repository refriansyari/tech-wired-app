package com.refri.techwired.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import javax.inject.Inject

abstract class BaseFragment<B : ViewBinding, VM : ViewModel>(
    val bindingFactory: (LayoutInflater, ViewGroup?, Boolean) -> B)
    : Fragment() {

    private lateinit var binding: B

    @Inject
    lateinit var viewModelInstance: VM

    fun getViewBinding(): B = binding
    fun getViewModel(): VM = viewModelInstance

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingFactory(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeData()
    }

    abstract fun observeData()
    abstract fun initView()

}