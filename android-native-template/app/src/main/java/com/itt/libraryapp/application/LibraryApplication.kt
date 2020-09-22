package com.itt.libraryapp.application

import android.app.Application
import com.github.ajalt.timberkt.Timber
import com.itt.libraryapp.dagger.AppComponent
import com.itt.libraryapp.dagger.AppModule
import com.itt.libraryapp.dagger.DaggerAppComponent


/**
 *
 * Created by Shrirang Andurkar on 5/31/2018.
 */

class LibraryApplication: Application(){

    lateinit var libraryComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        libraryComponent = initDagger(this)
        if (com.itt.libraryapp.BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initDagger(app:LibraryApplication): AppComponent =
            DaggerAppComponent.builder()
                    .appModule(AppModule(app))
                    .build()
}