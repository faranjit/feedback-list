package com.faranjit.feedbacklist.di

import android.content.Context
import com.faranjit.feedbacklist.network.FeedbackApi
import com.faranjit.feedbacklist.network.MockServer
import com.faranjit.feedbacklist.ui.detail.data.DetailDataRepository
import com.faranjit.feedbacklist.ui.detail.data.datasource.DetailRemoteDataSource
import com.faranjit.feedbacklist.ui.detail.domain.DetailRepository
import com.faranjit.feedbacklist.ui.detail.domain.interactor.GetDetails
import com.faranjit.feedbacklist.ui.detail.presentation.DetailViewModel
import com.faranjit.feedbacklist.ui.filter.data.FilterDataRepository
import com.faranjit.feedbacklist.ui.filter.data.datasource.FilterRemoteDataSource
import com.faranjit.feedbacklist.ui.filter.domain.FilterRepository
import com.faranjit.feedbacklist.ui.filter.domain.interactor.GetFilterData
import com.faranjit.feedbacklist.ui.filter.domain.interactor.GetFilteredFeedbacks
import com.faranjit.feedbacklist.ui.filter.presentation.FilterViewModel
import com.faranjit.feedbacklist.ui.home.data.HomeDataRepository
import com.faranjit.feedbacklist.ui.home.data.datasource.HomeRemoteDataSource
import com.faranjit.feedbacklist.ui.home.domain.HomeRepository
import com.faranjit.feedbacklist.ui.home.domain.interactor.GetFeedbacks
import com.faranjit.feedbacklist.ui.home.domain.interactor.GetStarredFeedbacks
import com.faranjit.feedbacklist.ui.home.presentation.HomeViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
@ExperimentalSerializationApi
val appModule = module {
    single { readDummyJson(androidContext()) }
    single { MockServer.createMockServer(get()) }
    single { createOkHttp(get()) }
    single { createJson() }
    single { createRetrofit(get(), get()) }
    single { createFeedbackApi(get()) }
}

val homeModule = module {
    factory { HomeRemoteDataSource(get()) }
    factory<HomeRepository> { HomeDataRepository(get()) }
    factory { GetFeedbacks(get()) }
    factory { GetStarredFeedbacks(get()) }

    viewModel { HomeViewModel(getFeedbacks = get(), getStarredFeedbacks = get()) }
}

val filterModule = module {
    factory { FilterRemoteDataSource(get()) }
    factory<FilterRepository> { FilterDataRepository(get()) }
    factory { GetFilterData(get()) }
    factory { GetFilteredFeedbacks(get()) }
    viewModel { FilterViewModel(getFilterData = get(), filter = get()) }
}

val detailModule = module {
    factory { DetailRemoteDataSource(get()) }
    factory<DetailRepository> { DetailDataRepository(get()) }
    factory { GetDetails(get()) }
    viewModel { DetailViewModel(get()) }
}

private fun createFeedbackApi(retrofit: Retrofit) = retrofit.create(FeedbackApi::class.java)

private fun createOkHttp(mockServerInterceptor: Interceptor) = OkHttpClient.Builder()
    .connectTimeout(120, TimeUnit.SECONDS)
    .readTimeout(120, TimeUnit.SECONDS)
    .addInterceptor(mockServerInterceptor)
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    })
    .build()

@ExperimentalSerializationApi
private fun createRetrofit(okHttpClient: OkHttpClient, json: Json) = Retrofit.Builder()
    .baseUrl("https://www.google.com")
    .client(okHttpClient)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()

private fun createJson() = Json {
    ignoreUnknownKeys = true
}

private fun readDummyJson(context: Context) =
    context.assets.open("apidemo.json").bufferedReader().use { it.readText() }