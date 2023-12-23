package com.refri.techwired.ui.newslist

import com.refri.techwired.data.model.response.NewsListResponse
import com.refri.techwired.data.network.datasource.NewsList.NewsListDataSource
import javax.inject.Inject

class NewsListRepository
    @Inject constructor (private val newsListDataSource: NewsListDataSource) : NewsListContract.Repository {

    override suspend fun getNewsList(): NewsListResponse {
        return newsListDataSource.getNewsList()
    }
    override fun logResponse(msg: String?) {}
}