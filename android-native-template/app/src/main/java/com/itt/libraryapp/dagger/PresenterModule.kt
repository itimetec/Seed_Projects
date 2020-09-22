package com.itt.libraryapp.dagger

import com.itt.libraryapp.ui.homepage.HomePagePresenter
import com.itt.libraryapp.ui.homepage.HomePagePresenterImpl
import com.itt.libraryapp.ui.loginpage.LoginPagePresenter
import com.itt.libraryapp.ui.loginpage.LoginPagePresenterImpl
import com.itt.libraryapp.ui.registrationpage.RegistrationPagePresenter
import com.itt.libraryapp.ui.registrationpage.RegistrationPagePresenterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 * Created by Shrirang Andurkar on 5/31/2018.
 */
@Module
class PresenterModule{
    @Provides
    @Singleton
    fun provideRegistrationPagePresenter(): RegistrationPagePresenter = RegistrationPagePresenterImpl()

    @Provides
    @Singleton
    fun provideLoginPagePresenter(): LoginPagePresenter = LoginPagePresenterImpl()

    @Provides
    @Singleton
    fun provideHomePagePresenter(): HomePagePresenter = HomePagePresenterImpl()
}