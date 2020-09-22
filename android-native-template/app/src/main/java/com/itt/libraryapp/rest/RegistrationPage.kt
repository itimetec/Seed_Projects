package com.itt.libraryapp.rest

/**
 *
 * Created by Shrirang Andurkar on 6/7/2018.
 */
class RegistrationPage(private val api: RestWrapper){
    fun get(email: String) = api.getUserByEmail(email)
    fun createUser(email: String,password:String) = api.createUser(email,password)
}