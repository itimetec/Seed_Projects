package com.itt.libraryapp.ui.homepage

import com.itt.libraryapp.model.HomePageResult

/**
 *
 * Created by Shrirang Andurkar on 6/9/2018.
 */
interface HomePagePresenter{
    fun setView(homePageView: HomePageView)

    fun getBooks(token: String)

    fun logOutOptionClicked()

    fun addBookClicked()

    fun addNewBook(token: String,bookName: String)

    fun deleteBook(book: HomePageResult)

    fun deleteSelectedBook(token: String,book: HomePageResult)

    fun updateBook(book: HomePageResult)

    fun updateSelectedBook(token: String,book: HomePageResult,updatedBookName:String)
}