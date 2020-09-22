package com.itt.libraryapp.ui.homepage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itt.libraryapp.R
import com.itt.libraryapp.model.HomePageResult
import javax.inject.Inject

/**
 *
 * Created by Shrirang Andurkar on 6/9/2018.
 */
class BooksListRecyclerAdapter @Inject constructor(var contextRef: Context): RecyclerView.Adapter<BooksViewHolder>(), IBooksViewClicked {

    private var data: MutableList<HomePageResult> = mutableListOf()
    var callback: IBooksListRecyclerClicked? = null

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        return holder.bindBook(data[position])
    }

    override fun getItemCount(): Int {
        synchronized(data) {
            return data.size
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        return BooksViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_books,parent,false),this)
    }

    override fun updateClicked(book: HomePageResult) {
        callback?.updateOptionSelected(book)
    }

    override fun deleteClicked(book: HomePageResult) {
        callback?.deleteOptionSelected(book)
    }

    fun addAllBooks(books: List<HomePageResult>) {
        synchronized(data) {
            for (book: HomePageResult in books){
                val indexOfFirst = data.indexOfFirst { book.name == it.name }
                if (indexOfFirst >= 0) {
                    data.removeAt(indexOfFirst)
                    data.add(indexOfFirst,book)
                } else {
                    data.add(book)
                    notifyItemInserted(data.size-1)
                }
            }
        }
    }

    fun removeBook(book: HomePageResult) {
        synchronized(data) {
            val indexOfFirst = data.indexOfFirst { book.name == it.name }
            if (indexOfFirst >= 0) {
                data.removeAt(indexOfFirst)
                notifyItemRemoved(indexOfFirst)
            }
        }
    }

    fun updateBookList(oldBook:HomePageResult,updatedBook:HomePageResult) {
        synchronized(data) {
            val indexOfOldBook = data.indexOf(oldBook)
            data[indexOfOldBook] = updatedBook
            notifyItemChanged(indexOfOldBook)
        }
    }

}

interface IBooksListRecyclerClicked{
    fun updateOptionSelected(book: HomePageResult)
    fun deleteOptionSelected(book: HomePageResult)
}