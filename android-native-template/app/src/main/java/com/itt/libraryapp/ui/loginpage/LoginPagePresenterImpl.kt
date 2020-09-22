package com.itt.libraryapp.ui.loginpage

import com.github.ajalt.timberkt.d
import com.itt.libraryapp.R
import com.itt.libraryapp.model.LoginPageResult
import com.itt.libraryapp.rest.LoginPage
import com.itt.libraryapp.rest.RestWrapper
import com.itt.libraryapp.ui.base.BasePresenter
import com.itt.libraryapp.ui.homepage.HomePageActivity
import com.itt.libraryapp.ui.registrationpage.RegistrationPageActivity
import com.itt.libraryapp.utils.Const
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException

/**
 *
 * Created by Shrirang Andurkar on 6/7/2018.
 */
class LoginPagePresenterImpl : BasePresenter(), LoginPagePresenter {

    private lateinit var loginPageView: LoginPageView
    private val client: OkHttpClient = OkHttpClient()
    private val api: RestWrapper = RestWrapper(client)
    private val loginPage: LoginPage = LoginPage(api)

    override fun setView(loginPageView: LoginPageView) {
        this.loginPageView = loginPageView
    }

    override fun signInBtnClicked(email: String, password: String) {
        loginPageView.toggleButtons(false)
        val (errorMessage, isUIValid) = validateUI(email, password)
        if (!isUIValid) {
            loginPageView.displayAlertMessage(R.string.invalid_details, errorMessage)
        } else {
            authenticateUser(email, password)
        }
    }

    override fun signUpBtnClicked() {
        loginPageView.startRegistrationActivity(RegistrationPageActivity::class.java)
    }

    private fun validateUI(emailId: String, password: String): Pair<String, Boolean> {
        return if (emailId.isEmpty() || password.isEmpty()) {
            Pair(first = Const.ENTER_ALL_DETAILS, second = false)
        } else if (!isEmailValid(emailId)) {
            Pair(first = Const.ENTER_VALID_EMAIL, second = false)
        } else {
            Pair(first = "", second = true)
        }
    }

    private fun authenticateUser(email: String, password: String) {
        loginPage.authenticateUser(email, password).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                if (response?.isSuccessful == true) {
                    response.let {
                        val responseString = response.body()?.string()
                        responseString.let {
                            when (JSONArray(responseString).length()) {
                                0 -> loginPageView.displayAlertMessage(R.string.error, Const.LOGIN_ERROR)
                                else -> authorizeUser(Const.AUTH_USER_NAME, Const.AUTH_PASSWORD)
                            }
                        }
                    }
                } else {
                    loginPageView.displayAlertMessage(R.string.error, Const.ERROR_OCCURED)
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                d { "Authenticate user API call failed" }
                loginPageView.displayAlertMessage(R.string.error, Const.ERROR_OCCURED)
            }
        })
    }

    private fun authorizeUser(email: String, password: String) {
        loginPage.authorizeUser(email, password).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                if (response?.isSuccessful == true) {
                    response.let {
                        LoginPageResult(it).token?.let {
                            d { "User Authorized" }
                            loginPageView.startHomeActivity(HomePageActivity::class.java, it)
                        } ?: run {
                            loginPageView.displayAlertMessage(R.string.error, response.message())
                        }
                    }
                } else {
                    loginPageView.displayAlertMessage(R.string.error, Const.ERROR_OCCURED)
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                d { "Authorize user API call failed" }
                loginPageView.displayAlertMessage(R.string.error, Const.ERROR_OCCURED)
            }
        })
    }

}