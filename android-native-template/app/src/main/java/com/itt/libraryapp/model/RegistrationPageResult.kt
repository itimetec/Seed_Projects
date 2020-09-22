package com.itt.libraryapp.model

import org.json.JSONObject

/**
 *
 * Created by Shrirang Andurkar on 6/7/2018.
 */
data class RegistrationPageResult(private val responseString: String){
    var id: String? = null
    var email: String? = null
    var password: String? = null


    init {
        id = JSONObject(responseString).getString("id")
        email = JSONObject(responseString).getString("email")
        password = JSONObject(responseString).getString("password")
    }
}

