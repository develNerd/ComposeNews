package org.fido.fidonews.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.fido.fidonews.android.di.presentationModule
import org.fido.fidonews.di.initKoin
import org.fido.fidonews.di.sharedModule

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
