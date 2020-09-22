package com.itt.libraryapp.ui.homepage

import com.itt.libraryapp.model.HomePageResult

/**
 *
 * Created by Shrirang Andurkar on 6/9/2018.
 */
interface HomePageView{
    fun displayAlertMessage(title:Int, message:String)

    fun displayBooksList(booksList:MutableList<HomePageResult>)

    fun logOut(clazz: Class<*>)

    fun displayPromptForAddingNewBook(title: Int,message: String)

    fun displayPromptForDeletingBook(title: Int,message: String,book: HomePageResult)

    fun displayPromptForUpdatingBook(title: Int,message: String,book: HomePageResult)

    fun removeBookFromList(book: HomePageResult)

    fun updateBookList(oldBook:HomePageResult,updatedBook:HomePageResult)
}