package com.faranjit.feedbacklist.di

import com.faranjit.feedbacklist.network.MockServer
import com.faranjit.feedbacklist.ui.home.data.HomeApi
import com.faranjit.feedbacklist.ui.home.data.HomeDataRepository
import com.faranjit.feedbacklist.ui.home.data.datasource.HomeRemoteDataSource
import com.faranjit.feedbacklist.ui.home.domain.HomeRepository
import com.faranjit.feedbacklist.ui.home.domain.interactor.GetFeedbacks
import com.faranjit.feedbacklist.ui.home.presentation.HomeViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
val appModule = module {
    single { MockServer.createMockServer(androidContext()) }
    single { createOkHttp(get()) }
    single { createRetrofit(get()) }

    factory { HomeRemoteDataSource(createHomeApi(get())) }
    factory<HomeRepository> { HomeDataRepository(get()) }
}

val homeModule = module {
    factory { GetFeedbacks(get()) }
    viewModel { HomeViewModel(get()) }
}

private fun createHomeApi(retrofit: Retrofit) = retrofit.create(HomeApi::class.java)

private fun createOkHttp(mockServerInterceptor: Interceptor) = OkHttpClient.Builder()
    .connectTimeout(120, TimeUnit.SECONDS)
    .readTimeout(120, TimeUnit.SECONDS)
    .addInterceptor(mockServerInterceptor)
    .build()

private fun createRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
    .baseUrl("https://www.google.com")
    .client(okHttpClient)
    .addConverterFactory(Json {
        ignoreUnknownKeys = true
    }.asConverterFactory(MediaType.get("application/json")))
    .build()