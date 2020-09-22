package com.itt.libraryapp.ui.base

import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.itt.libraryapp.R

/**
 *
 * Created by Shrirang Andurkar on 6/7/2018.
 */
abstract class BaseAcitivity:AppCompatActivity(){

    protected fun showAlertDialog(
            titleStringId: Int,
            message: String,
            positiveStringId: Int,
            onPositiveBehavior: (DialogInterface, Int) -> Unit
    ) {
        runOnUiThread {
            val alertDialog = AlertDialog
                    .Builder(this)
                    .setTitle(titleStringId)
                    .setMessage(message)
                    .setPositiveButton(positiveStringId, onPositiveBehavior)
                    .create()
            alertDialog.setOnShowListener { alertDialog.getButton(AlertDialog.BUTTON_POSITIVE) }
            alertDialog

                    .show()
        }

    }

    protected fun createPromptDialog(titleStringId: Int,message: String): Pair<AlertDialog,View>{
        val promptView: View = layoutInflater.inflate(R.layout.input_prompt,null)
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle(titleStringId)
        alertDialog.setMessage(message)

        return Pair(alertDialog,promptView)
    }
}