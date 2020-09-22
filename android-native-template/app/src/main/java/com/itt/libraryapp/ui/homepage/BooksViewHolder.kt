package com.itt.libraryapp.ui.homepage

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.itt.libraryapp.model.HomePageResult
import kotlinx.android.synthetic.main.item_books.view.*

/**
 *
 * Created by Shrirang Andurkar on 6/9/2018.
 */
class BooksViewHolder(view:View, private val callback: IBooksViewClicked): RecyclerView.ViewHolder(view){

    fun bindBook(book: HomePageResult){
        itemView.book_name_tv.text = book.name

        itemView.update_btn.setOnClickListener {
            callback.updateClicked(book)
        }

        itemView.delete_btn.setOnClickListener {
            callback.deleteClicked(book)
        }
    }

}

interface IBooksViewClicked {
    fun updateClicked(book:HomePageResult)
    fun deleteClicked(book: HomePageResult)
}