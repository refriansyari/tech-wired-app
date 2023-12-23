package com.refri.techwired.di

import com.refri.techwired.base.GenericViewModelFactory
import com.refri.techwired.ui.detail.NewsDetailRepository
import com.refri.techwired.ui.detail.NewsDetailViewModel
import com.refri.techwired.ui.newslist.NewsListRepository
import com.refri.techwired.ui.newslist.NewsListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ViewModelModule {
    @Provides
    @ActivityScoped
    fun provideNewsListViewModel(
       newsListRepository: NewsListRepository
    ): NewsListViewModel {
        return GenericViewModelFactory(NewsListViewModel(newsListRepository)).create(
            NewsListViewModel::class.java
        )
    }

    @Provides
    @ActivityScoped
    fun provideNewsDetailViewModel(
        newsDetailRepository: NewsDetailRepository
    ): NewsDetailViewModel {
        return GenericViewModelFactory(NewsDetailViewModel(newsDetailRepository)).create(
            NewsDetailViewModel::class.java
        )
    }
}