package com.itt.libraryapp.ui.loginpage

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import com.github.ajalt.timberkt.d
import com.itt.libraryapp.R
import com.itt.libraryapp.application.LibraryApplication
import com.itt.libraryapp.ui.base.BaseAcitivity
import com.itt.libraryapp.utils.Const
import kotlinx.android.synthetic.main.activity_login_page.*
import javax.inject.Inject

class LoginPageActivity : BaseAcitivity(),LoginPageView {

    @Inject lateinit var presenter : LoginPagePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        (application as LibraryApplication).libraryComponent.inject(this)
        d { "Launching Login Screen" }
        presenter.setView(this)

        signup_btn.setOnClickListener {
            presenter.signUpBtnClicked()
        }

        signin_btn.setOnClickListener {
            presenter.signInBtnClicked(email = email_et.text.toString(),password = password_et.text.toString())
        }
    }

    override fun displayAlertMessage(title: Int, message: String) {
        showAlertDialog(title,message,R.string.ok) { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
            clearEditTexts()
            toggleButtons(true)
        }
    }

    override fun startRegistrationActivity(clazz: Class<*>) {
        val intent = Intent(this,clazz)
        startActivity(intent)
    }

    override fun startHomeActivity(clazz: Class<*>, token: String) {
        val intent = Intent(this,clazz)
        intent.putExtra(Const.TOKEN,token)
        startActivity(intent)
    }

    private fun clearEditTexts() {
        email_et.setText("")
        password_et.setText("")
    }

    override fun toggleButtons(isEnabled: Boolean) {
        signin_btn.isEnabled = isEnabled
        signup_btn.isEnabled = isEnabled
    }
}
