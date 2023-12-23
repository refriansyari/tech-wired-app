package com.refri.techwired.di

import com.refri.techwired.data.network.datasource.NewsDetail.NewsDetailDataSource
import com.refri.techwired.data.network.datasource.NewsDetail.NewsDetailDataSourceImpl
import com.refri.techwired.data.network.datasource.NewsList.NewsListDataSource
import com.refri.techwired.data.network.datasource.NewsList.NewsListDataSourceImpl
import com.refri.techwired.data.service.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideNewsListDataSource(articleApiService: ApiServices): NewsListDataSource {
        return NewsListDataSourceImpl(articleApiService)
    }

    @Singleton
    @Provides
    fun provideNewsDetailDataSource(): NewsDetailDataSource {
        return NewsDetailDataSourceImpl()
    }

}