package org.compose.news.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.compose.news.android.di.presentationModule
import org.compose.news.di.initKoin
import org.compose.news.di.sharedModule

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()

        //Initialise Koin for Dependency Injection
        initKoin {
            allowOverride(true)
            androidContext(this@MainApp)
            // if (BuildConfig.DEBUG)
            androidLogger(Level.ERROR)
            modules(appModules, sharedModule)
        }

    }


}

val appModules = presentationModule
