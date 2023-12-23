package com.refri.techwired.di

import com.refri.techwired.data.network.datasource.NewsDetail.NewsDetailDataSource
import com.refri.techwired.data.network.datasource.NewsList.NewsListDataSource
import com.refri.techwired.ui.detail.NewsDetailRepository
import com.refri.techwired.ui.newslist.NewsListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsListRepository(
        newsListDataSource: NewsListDataSource
    ): NewsListRepository {
        return NewsListRepository(newsListDataSource)
    }

    @Provides
    @Singleton
    fun provideNewsDetailRepository(
        newsDetailDataSource: NewsDetailDataSource
    ): NewsDetailRepository {
        return NewsDetailRepository(newsDetailDataSource)
    }
}