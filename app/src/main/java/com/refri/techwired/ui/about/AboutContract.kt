package com.refri.techwired.ui.about

import com.refri.techwired.base.BaseContract
import com.refri.techwired.data.model.response.Article

interface AboutContract {

    interface View : BaseContract.BaseView {
        fun setClickListeners()
        fun navigateToPortfolio(urls:String)
    }

    interface ViewModel : BaseContract.BaseViewModel {}
    interface Repository : BaseContract.BaseRepository {}
}