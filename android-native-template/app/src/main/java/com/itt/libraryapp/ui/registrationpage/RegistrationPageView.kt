package com.itt.libraryapp.ui.registrationpage

/**
 *
 * Created by Shrirang Andurkar on 5/31/2018.
 */
interface RegistrationPageView{

    fun displayAlertMessage(title:Int, message:String)

    fun startActivity(clazz: Class<*>)

}