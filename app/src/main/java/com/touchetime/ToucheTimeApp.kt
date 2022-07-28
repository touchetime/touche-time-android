package com.touchetime

import android.app.Application
import com.touchetime.di.dataModule
import com.touchetime.di.domainModule
import com.touchetime.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ToucheTimeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ToucheTimeApp)
            modules(dataModule, domainModule, presentationModule)
        }
    }
}
