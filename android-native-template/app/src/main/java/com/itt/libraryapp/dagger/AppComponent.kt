package com.itt.libraryapp.dagger

import com.itt.libraryapp.ui.homepage.HomePageActivity
import com.itt.libraryapp.ui.loginpage.LoginPageActivity
import com.itt.libraryapp.ui.registrationpage.RegistrationPageActivity
import dagger.Component
import javax.inject.Singleton

/**
 *
 * Created by Shrirang Andurkar on 5/31/2018.
 */
@Singleton
@Component(modules = [AppModule::class,PresenterModule::class])
interface AppComponent{
    fun inject(target: RegistrationPageActivity)
    fun inject(target: LoginPageActivity)
    fun inject(target: HomePageActivity)
}
