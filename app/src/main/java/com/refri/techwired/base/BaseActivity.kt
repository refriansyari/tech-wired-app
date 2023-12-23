package com.refri.techwired.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import javax.inject.Inject

abstract class BaseActivity<B : ViewBinding, VM : ViewModel>(
    val bindingFactory: (LayoutInflater) -> B
) : AppCompatActivity(), BaseContract.BaseView {

    private lateinit var binding: B

    @Inject
    lateinit var viewModelInstance: VM

    fun getViewBinding(): B = binding
    fun getViewModel(): VM = viewModelInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
        initView()
        observeData()

        changeStatusBarColor("#0C59B3")
    }

    abstract fun initView()
    override fun observeData() {
        //do nothing
    }

    override fun showContent(isVisible: Boolean) {
        //do nothing
    }

    override fun showLoading(isVisible: Boolean) {
        //do nothing
    }

    override fun showError(isErrorEnabled: Boolean, msg: String?) {
        if (isErrorEnabled) Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun changeStatusBarColor(hexColor: String) {
        // Color must be in hexadecimal format
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor(hexColor)
    }
}