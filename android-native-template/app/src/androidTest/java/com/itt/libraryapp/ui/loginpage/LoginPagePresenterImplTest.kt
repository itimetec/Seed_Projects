package com.itt.libraryapp.ui.loginpage

import com.itt.libraryapp.R
import com.itt.libraryapp.utils.Const
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.runners.MockitoJUnitRunner

/**
 *
 * Created by Shrirang Andurkar on 6/12/2018.
 */
@RunWith(MockitoJUnitRunner::class)
class LoginPagePresenterImplTest {


    private val loginPresenter: LoginPagePresenter = LoginPagePresenterImpl()

    @Mock
    private val loginPageView: LoginPageView? = null

    @Before
    fun setup() {
        loginPresenter.setView(loginPageView!!)
    }

    @Test
    fun signInBtnClickedWithOutEmailAndPassword() {
        loginPresenter.signInBtnClicked("","")
        verify(loginPageView)?.displayAlertMessage(R.string.invalid_details, Const.ENTER_ALL_DETAILS)
    }

    @Test
    fun signInBtnClickedWithInvalidEmailPattern() {
        loginPresenter.signInBtnClicked("testuseritt","12334345")
        verify(loginPageView)?.displayAlertMessage(R.string.invalid_details,Const.ENTER_VALID_EMAIL)
    }

}