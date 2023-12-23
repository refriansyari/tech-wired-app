package com.refri.techwired.data.network.datasource.NewsList

import com.refri.techwired.data.model.response.NewsListResponse
import com.refri.techwired.data.service.ApiServices
import javax.inject.Inject

class NewsListDataSourceImpl @Inject constructor(private val newsApiServices : ApiServices?) : NewsListDataSource {
    override suspend fun getNewsList() : NewsListResponse {
        return newsApiServices!!.getNewsList()
    }
}