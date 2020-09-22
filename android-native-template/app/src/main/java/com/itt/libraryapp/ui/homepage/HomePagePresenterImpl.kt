package com.itt.libraryapp.ui.homepage

import com.github.ajalt.timberkt.d
import com.itt.libraryapp.R
import com.itt.libraryapp.model.HomePageResult
import com.itt.libraryapp.rest.HomePage
import com.itt.libraryapp.rest.RestWrapper
import com.itt.libraryapp.ui.base.BasePresenter
import com.itt.libraryapp.ui.loginpage.LoginPageActivity
import com.itt.libraryapp.utils.Const
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

/**
 *
 * Created by Shrirang Andurkar on 6/9/2018.
 */
class HomePagePresenterImpl:BasePresenter(),HomePagePresenter{

    private lateinit var homePageView: HomePageView
    private val client: OkHttpClient = OkHttpClient()
    private val api: RestWrapper =  RestWrapper(client)
    private val homePage: HomePage = HomePage(api)
    private var booksList : MutableList<HomePageResult> = mutableListOf()

    override fun setView(homePageView: HomePageView) {
        this.homePageView = homePageView
    }

    override fun logOutOptionClicked() {
        homePageView.logOut(LoginPageActivity::class.java)
    }

    override fun getBooks(token: String) {
        homePage.getBooks(token).enqueue(object : Callback{
            override fun onResponse(call: Call?, response: Response?) {
               if (response?.isSuccessful == true) {
                   response.let {
                       val responseString = response.body()?.string()
                       responseString.let {
                           val responseJsonArray = JSONArray(it)
                           when(responseJsonArray.length()){
                               0 -> {
                                   homePageView.displayAlertMessage(R.string.no_books,Const.NO_BOOKS_AVAILABLE)
                                   homePageView.displayBooksList(booksList)
                               }
                               else -> {
                                   for (i in 0 until responseJsonArray.length()){
                                       val bookId = responseJsonArray.getJSONObject(i).getString("id")
                                       val bookName = responseJsonArray.getJSONObject(i).getString("name")
                                       val homePageResult = HomePageResult(bookId,bookName)
                                       booksList.add(homePageResult)
                                   }
                                   homePageView.displayBooksList(booksList)
                               }
                           }
                       }
                   }
               } else {
                   homePageView.displayAlertMessage(R.string.error,Const.ERROR_OCCURED)
               }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                d { "Get Books API call failed" }
                homePageView.displayAlertMessage(R.string.error,Const.ERROR_OCCURED)
            }
        })
    }

    override fun addBookClicked() {
        homePageView.displayPromptForAddingNewBook(R.string.add_book,Const.NEW_BOOK)
    }

    override fun addNewBook(token: String,bookName: String) {
        homePage.addNewBook(token,bookName).enqueue(object : Callback{
            override fun onResponse(call: Call?, response: Response?) {
                if (response?.isSuccessful == true) {
                    response.let {
                        val responseString = response.body()?.string()
                        responseString.let {
                            val responseJsonObj = JSONObject(it)
                            val bookId = responseJsonObj.getString("id")
                            val bookName = responseJsonObj.getString("name")
                            val homePageResult = HomePageResult(bookId,bookName)
                            booksList.add(homePageResult)
                            homePageView.displayBooksList(booksList)
                        }
                    }
                } else {
                    homePageView.displayAlertMessage(R.string.error,Const.ERROR_OCCURED)
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                d { "Add Book API call failed" }
                homePageView.displayAlertMessage(R.string.error,Const.ERROR_OCCURED)
            }
        })
    }

    override fun deleteBook(book: HomePageResult) {
        homePageView.displayPromptForDeletingBook(R.string.delete_book,Const.DELETE_BOOK,book)
    }

    override fun deleteSelectedBook(token: String, book: HomePageResult) {
        homePage.deleteBook(token,book.id.toString()).enqueue(object : Callback{
            override fun onResponse(call: Call?, response: Response?) {
                if (response?.isSuccessful == true) {
                    booksList.remove(book)
                    homePageView.removeBookFromList(book)
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                d { "Delete Book API call failed" }
                homePageView.displayAlertMessage(R.string.error,Const.DELETE_BOOK_ERROR)
            }
        })
    }

    override fun updateBook(book: HomePageResult) {
        homePageView.displayPromptForUpdatingBook(R.string.update_book,Const.UPDATE_BOOK,book)
    }

    override fun updateSelectedBook(token: String, book: HomePageResult,updatedBookName:String) {
        homePage.updateBook(token,book.id.toString(),updatedBookName).enqueue(object : Callback{
            override fun onResponse(call: Call?, response: Response?) {
                if (response?.isSuccessful == true) {
                    response.let {
                        val responseString = response.body()?.string()
                        responseString.let {
                            val responseJsonObj = JSONObject(it)
                            val bookId = responseJsonObj.getString("id")
                            val updatedBookName = responseJsonObj.getString("name")
                            val updatedBook = HomePageResult(bookId,updatedBookName)

                            booksList.remove(book)
                            booksList.add(updatedBook)
                            homePageView.updateBookList(book,updatedBook)
                        }
                    }
                } else {
                    homePageView.displayAlertMessage(R.string.error,Const.ERROR_OCCURED)
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                d { "Update Book API call failed" }
                homePageView.displayAlertMessage(R.string.error,Const.UPDATE_BOOK_ERROR)
            }
        })
    }

}