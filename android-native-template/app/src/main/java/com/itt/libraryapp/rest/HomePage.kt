package com.itt.libraryapp.rest

/**
 *
 * Created by Shrirang Andurkar on 6/9/2018.
 */
class HomePage(private val api: RestWrapper){
    fun getBooks(token:String) = api.getBooks(token)

    fun addNewBook(token:String,bookName:String) = api.createNewBook(token,bookName)

    fun deleteBook(token: String,bookId: String) = api.deleteBook(token,bookId)

    fun updateBook(token: String,bookId: String,bookName: String) = api.updateBook(token,bookId,bookName)
}