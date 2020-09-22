package com.itt.libraryapp.model

/**
 *
 * Created by Shrirang Andurkar on 6/9/2018.
 */
data class HomePageResult(private val bookId: String,private val bookName:String){
    var id: String? = null
    var name: String? = null

    init {
        id = bookId
        name = bookName
    }
}