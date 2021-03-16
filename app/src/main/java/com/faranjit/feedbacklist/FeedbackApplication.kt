package com.faranjit.feedbacklist

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.faranjit.feedbacklist.di.appModule
import com.faranjit.feedbacklist.di.detailModule
import com.faranjit.feedbacklist.di.filterModule
import com.faranjit.feedbacklist.di.homeModule
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Bulent Turkmen on 15.03.2021.
 */
class FeedbackApplication : MultiDexApplication() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    @ExperimentalSerializationApi
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@FeedbackApplication)
            modules(appModule, homeModule, filterModule, detailModule)
        }
    }

    private fun readDummyJson() {

    }
}