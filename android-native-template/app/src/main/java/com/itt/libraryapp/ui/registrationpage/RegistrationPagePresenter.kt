package com.itt.libraryapp.ui.registrationpage

/**
 *
 * Created by Shrirang Andurkar on 5/31/2018.
 */
interface RegistrationPagePresenter{

    fun setView(registrationPageView: RegistrationPageView)

    fun registerBtnClicked(email:String,password:String,confirmPassword:String)

    fun signInBtnClicked()
}