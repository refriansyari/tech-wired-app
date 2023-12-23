package com.refri.techwired.ui.newslist

import androidx.lifecycle.LiveData
import com.refri.techwired.base.BaseContract
import com.refri.techwired.base.Resource
import com.refri.techwired.data.model.response.Article
import com.refri.techwired.data.model.response.NewsListResponse

interface NewsListContract {

    interface View : BaseContract.BaseView {
        fun initList()
        fun getData()
        fun initSwipeRefresh()
        fun setDataAdapter(data: List<Article>?)
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun getNewsList()
        fun getNewsListLiveData(): LiveData<Resource<List<Article>>>
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun getNewsList(): NewsListResponse
    }

}