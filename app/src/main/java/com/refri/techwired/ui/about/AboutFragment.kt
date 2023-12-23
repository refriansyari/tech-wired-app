package com.refri.techwired.ui.about

import android.content.Intent
import android.net.Uri
import com.refri.techwired.R
import com.refri.techwired.base.BaseFragment
import com.refri.techwired.databinding.FragmentAboutBinding


class AboutFragment : BaseFragment<FragmentAboutBinding, AboutViewModel>(FragmentAboutBinding::inflate),
AboutContract.View{

    override fun showError(isErrorEnabled: Boolean, msg: String?) {}

    override fun initView() {
        setClickListeners()
    }

    override fun setClickListeners(){
        getViewBinding().llPortfolio.setOnClickListener{
            navigateToPortfolio(getString(R.string.text_navigate_to_portfolio))
        }

        getViewBinding().llLinkedin.setOnClickListener{
            navigateToPortfolio(getString(R.string.text_navigate_to_linkedIn))
        }

    }

    override fun navigateToPortfolio(urls:String){

        val openUrl = Uri.parse(urls)
        val intent = Intent(Intent.ACTION_VIEW, openUrl)
        startActivity(intent)
    }

    override fun observeData() {}
    override fun showContent(isVisible: Boolean) {}
    override fun showLoading(isVisible: Boolean) {}

}