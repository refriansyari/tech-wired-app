package com.refri.techwired.ui.detail

import com.refri.techwired.base.BaseContract
import com.refri.techwired.data.model.response.Article

interface NewsDetailContract {

    interface View : BaseContract.BaseView {
        fun setViewData(newsDetail : Article)
        fun openExternalBrowser (url : String)
        fun convertDateFormat(input: String): String
    }

    interface ViewModel : BaseContract.BaseViewModel {}
    interface Repository : BaseContract.BaseRepository {}

}