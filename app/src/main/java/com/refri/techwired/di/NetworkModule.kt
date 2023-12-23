package com.refri.techwired.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.refri.techwired.data.service.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideNewsApiServices(chuckerInterceptor: ChuckerInterceptor): ApiServices {
        return ApiServices.invoke(chuckerInterceptor)
    }

    @Singleton
    @Provides
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context).build()
    }
}