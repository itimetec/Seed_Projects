package com.itt.libraryapp.ui.loginpage

/**
 *
 * Created by Shrirang Andurkar on 6/7/2018.
 */
interface LoginPageView{
    fun displayAlertMessage(title:Int, message:String)

    fun startRegistrationActivity(clazz: Class<*>)

    fun startHomeActivity(clazz: Class<*>,token: String)

    fun toggleButtons(isEnabled: Boolean)
}