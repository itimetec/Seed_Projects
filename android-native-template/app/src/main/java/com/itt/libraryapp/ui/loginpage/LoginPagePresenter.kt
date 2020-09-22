package com.itt.libraryapp.ui.loginpage

/**
 *
 * Created by Shrirang Andurkar on 6/7/2018.
 */

interface LoginPagePresenter{
    fun setView(loginPageView: LoginPageView)

    fun signInBtnClicked(email:String,password:String)

    fun signUpBtnClicked()
}