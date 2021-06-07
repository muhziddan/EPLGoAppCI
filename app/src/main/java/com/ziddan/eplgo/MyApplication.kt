package com.ziddan.eplgo

import android.app.Application
import com.ziddan.eplgo.core.di.databaseModule
import com.ziddan.eplgo.core.di.networkModule
import com.ziddan.eplgo.core.di.repositoryModule
import com.ziddan.eplgo.di.useCaseModule
import com.ziddan.eplgo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}