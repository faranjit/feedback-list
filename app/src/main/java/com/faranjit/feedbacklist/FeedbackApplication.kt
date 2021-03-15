package com.faranjit.feedbacklist

import android.app.Application
import com.faranjit.feedbacklist.di.appModule
import com.faranjit.feedbacklist.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Bulent Turkmen on 15.03.2021.
 */
class FeedbackApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@FeedbackApplication)
            modules(appModule, homeModule)
        }
    }

    private fun readDummyJson() {

    }
}