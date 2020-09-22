package com.itt.libraryapp.dagger

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 * Created by Shrirang Andurkar on 5/31/2018.
 */
@Module
class AppModule(private val app: Application){
    @Provides
    @Singleton
    fun provideContext(): Context = app
}