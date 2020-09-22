package com.itt.libraryapp.ui.registrationpage

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import com.itt.libraryapp.R
import com.itt.libraryapp.application.LibraryApplication
import com.itt.libraryapp.ui.base.BaseAcitivity
import kotlinx.android.synthetic.main.activity_registration_page.*
import javax.inject.Inject

class RegistrationPageActivity : BaseAcitivity(),RegistrationPageView {


    @Inject lateinit var presenter: RegistrationPagePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_page)
        (application as LibraryApplication).libraryComponent.inject(this)
        presenter.setView(this)

        signup_btn.setOnClickListener {
            presenter.registerBtnClicked(email = email_et.text.toString(),password = password_et.text.toString(),
                    confirmPassword = confirm_password_et.text.toString())
        }

        signin_btn.setOnClickListener {
            presenter.signInBtnClicked()
        }
    }

    override fun displayAlertMessage(title:Int, message: String) {
        showAlertDialog(title,message,R.string.ok) { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
            clearEditTexts()
        }
    }

    override fun startActivity(clazz: Class<*>) {
        val intent = Intent(this,clazz)
        startActivity(intent)
    }

    private fun clearEditTexts() {
       email_et.text.clear()
        password_et.text.clear()
        confirm_password_et.text.clear()
    }
}
