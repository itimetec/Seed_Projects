package com.itt.libraryapp.ui.registrationpage

import com.github.ajalt.timberkt.d
import com.itt.libraryapp.R
import com.itt.libraryapp.model.GetUserByEmail
import com.itt.libraryapp.model.RegistrationPageResult
import com.itt.libraryapp.rest.RegistrationPage
import com.itt.libraryapp.rest.RestWrapper
import com.itt.libraryapp.ui.base.BasePresenter
import com.itt.libraryapp.ui.loginpage.LoginPageActivity
import com.itt.libraryapp.utils.Const
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

/**
 *
 * Created by Shrirang Andurkar on 5/31/2018.
 */
class RegistrationPagePresenterImpl:BasePresenter(),RegistrationPagePresenter{

    private lateinit var registrationPageView: RegistrationPageView
    private val client: OkHttpClient = OkHttpClient()
    private val api: RestWrapper =  RestWrapper(client)
    private val registrationPage: RegistrationPage = RegistrationPage(api)

    override fun setView(registrationPageView: RegistrationPageView) {
        this.registrationPageView = registrationPageView
    }

    override fun registerBtnClicked(email: String, password: String, confirmPassword: String) {
        val (errorMessage,isUIValid) = validateUI(email,password,confirmPassword)
        if (!isUIValid){
            registrationPageView.displayAlertMessage(R.string.invalid_details,errorMessage)
        } else{
            getUserByEmail(email,password)
        }
    }

    override fun signInBtnClicked() {
        registrationPageView.startActivity(LoginPageActivity::class.java)
    }

    private fun validateUI(emailId: String, password: String, confirmPassword: String): Pair<String,Boolean>{
        return if(emailId.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Pair(first = Const.ENTER_ALL_DETAILS,second = false)
        } else if (!isEmailValid(emailId)){
            Pair(first = Const.ENTER_VALID_EMAIL,second = false)
        } else if (password.length != 8){
            Pair(first = Const.PASSWORD_LENGTH,second = false)
        } else if(password != confirmPassword){
            Pair(first = Const.PASSWORD_AND_CONFIRM_PASSWORD_MUST_MATCH,second = false)
        } else {
            Pair(first = "",second = true)
        }
    }

    private fun getUserByEmail(email: String,password: String){
        registrationPage.get(email).enqueue(object : Callback{
            override fun onResponse(call: Call?, response: Response?) {
                if (response?.isSuccessful == true){
                    response.let {
                        GetUserByEmail(it).email?.let {
                            registrationPageView.displayAlertMessage(R.string.invalid_details,Const.EMAIL_ADDRESS_ALREADY_EXISTS)
                        } ?: run {
                            createUser(email,password)
                        }
                    }
                } else {
                    registrationPageView.displayAlertMessage(R.string.invalid_details,Const.ERROR_OCCURED)
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                d { "Get user by email API call failed" }
                registrationPageView.displayAlertMessage(R.string.invalid_details,Const.ERROR_OCCURED)
            }
        })
    }

    private fun createUser(email: String,password: String) {
        registrationPage.createUser(email,password).enqueue(object : Callback{
            override fun onResponse(call: Call?, response: Response?) {
                if (response?.isSuccessful == true) {
                    response.let {
                        val responseString = response.body()?.string()
                        responseString?.let {
                            if (RegistrationPageResult(it).id != null && RegistrationPageResult(it).email != null
                                    && RegistrationPageResult(it).password != null) {
                                registrationPageView.displayAlertMessage(R.string.success,Const.USER_CREATED)
                            } else {
                                registrationPageView.displayAlertMessage(R.string.error,Const.ERROR_OCCURED)
                            }
                        }
                    }
                } else {
                    registrationPageView.displayAlertMessage(R.string.invalid_details,Const.ERROR_OCCURED)
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                d { "Create user API call failed" }
                registrationPageView.displayAlertMessage(R.string.invalid_details,Const.ERROR_OCCURED)
            }
        })
    }

}