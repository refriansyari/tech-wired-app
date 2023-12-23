package com.refri.techwired.data.network.datasource.NewsList

import com.refri.techwired.data.model.response.NewsListResponse

interface NewsListDataSource {
    suspend fun getNewsList(): NewsListResponse

}