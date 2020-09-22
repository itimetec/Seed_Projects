package com.itt.libraryapp.model

import okhttp3.Response
import org.json.JSONObject

/**
 *
 * Created by Shrirang Andurkar on 6/8/2018.
 */

data class LoginPageResult(private val response: Response) {

    var token: String? = null

    init {
        token = response.body()?.string().let {
            JSONObject(it)
                    .getString("token")?:null
        }
    }
}