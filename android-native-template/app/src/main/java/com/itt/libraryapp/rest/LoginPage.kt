package com.itt.libraryapp.rest

/**
 *
 * Created by Shrirang Andurkar on 6/8/2018.
 */
class LoginPage(private val api: RestWrapper){
    fun authenticateUser(email:String,password:String) = api.authenticateUser(email,password)

    fun authorizeUser(email: String,password: String) = api.authorizeUser(email,password)
}