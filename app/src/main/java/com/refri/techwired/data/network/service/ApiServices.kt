package com.refri.techwired.data.service

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.refri.techwired.BuildConfig
import com.refri.techwired.data.model.response.NewsListResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiServices {

    @GET("top-headlines")
    suspend fun getNewsList(
        @Query("country") country: String = "us",
        @Query("category") category: String = "technology",
        @Query("apiKey") key: String = BuildConfig.API_KEY
    ) : NewsListResponse

    companion object {

        @JvmStatic
        operator fun invoke(chuckerInterceptor: ChuckerInterceptor): ApiServices {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(chuckerInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(ApiServices::class.java)
        }
    }
}