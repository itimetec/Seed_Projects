package com.itt.libraryapp.model

import okhttp3.Response
import org.json.JSONArray

/**
 *
 * Created by Shrirang Andurkar on 6/7/2018.
 */
data class GetUserByEmail(private val response: Response){
    var email: String? = null

    init {
        email = response.body()?.string().let {
            val responseJsonArray = JSONArray(it)
            if (responseJsonArray.length() == 1){
                responseJsonArray
                        .getJSONObject(0)
                        .get("email").toString()
            } else {
                null
            }
        }
    }
}