package com.itt.libraryapp.ui.homepage

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ajalt.timberkt.d
import com.itt.libraryapp.R
import com.itt.libraryapp.application.LibraryApplication
import com.itt.libraryapp.model.HomePageResult
import com.itt.libraryapp.ui.base.BaseAcitivity
import com.itt.libraryapp.utils.Const
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class HomePageActivity : BaseAcitivity(), HomePageView, IBooksListRecyclerClicked {

    private var token = String()
    @Inject
    lateinit var presenter: HomePagePresenter
    @Inject
    lateinit var booksListRecyclerAdapter: BooksListRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        setSupportActionBar(findViewById(R.id.toolbar))
        (application as LibraryApplication).libraryComponent.inject(this)
        token = intent?.getStringExtra(Const.TOKEN) ?: token
        presenter.setView(this)
        fabAddBook.setOnClickListener {
            presenter.addBookClicked()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_log_out -> {
                presenter.logOutOptionClicked()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        presenter.getBooks(token)
        booksListRecyclerAdapter.callback = this
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        books_rv.layoutManager = linearLayoutManager
        books_rv.setHasFixedSize(true)
        books_rv.adapter = booksListRecyclerAdapter
        val dividerItemDecoration = DividerItemDecoration(applicationContext, linearLayoutManager.orientation)
        books_rv.addItemDecoration(dividerItemDecoration)
    }

    override fun displayAlertMessage(title: Int, message: String) {
        showAlertDialog(title, message, R.string.ok) { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
        }
    }

    override fun displayBooksList(booksList: MutableList<HomePageResult>) {
        booksListRecyclerAdapter.addAllBooks(booksList)
    }

    override fun updateOptionSelected(book: HomePageResult) {
        d { "Update book option selected" }
        presenter.updateBook(book)
    }

    override fun deleteOptionSelected(book: HomePageResult) {
        d { "Delete book option selected" }
        presenter.deleteBook(book)
    }

    override fun logOut(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }

    override fun removeBookFromList(book: HomePageResult) {
        booksListRecyclerAdapter.removeBook(book)
    }

    override fun updateBookList(oldBook: HomePageResult, updatedBook: HomePageResult) {
        runOnUiThread {
            booksListRecyclerAdapter.updateBookList(oldBook, updatedBook)
        }

    }


    override fun displayPromptForAddingNewBook(title: Int, message: String) {
        val (alertDialog, promptView) = createPromptDialog(title, message)
        val bookNameEditText: EditText = promptView.findViewById(R.id.book_name_et)

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, Const.OK_BUTTON) { _, _ ->
            val bookName = bookNameEditText.text.toString()
            when (bookName.length) {
                0 -> {
                    alertDialog.dismiss()
                }
                else -> {
                    presenter.addNewBook(token, bookName)
                    alertDialog.dismiss()
                }
            }
        }

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, Const.CANCEL_BUTTON) { _, _ -> alertDialog.dismiss() }

        alertDialog.setView(promptView)
        alertDialog.show()
    }

    override fun displayPromptForDeletingBook(title: Int, message: String, book: HomePageResult) {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, Const.OK_BUTTON) { _, _ ->
            presenter.deleteSelectedBook(token, book)
            alertDialog.dismiss()
        }

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, Const.CANCEL_BUTTON) { _, _ -> alertDialog.dismiss() }

        alertDialog.show()
    }

    override fun displayPromptForUpdatingBook(title: Int, message: String, book: HomePageResult) {
        val (alertDialog, promptView) = createPromptDialog(title, message)
        val bookNameEditText: EditText = promptView.findViewById(R.id.book_name_et)
        bookNameEditText.setText(book.name)
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, Const.OK_BUTTON) { _, _ ->
            val updatedBookName = bookNameEditText.text.toString()
            when (updatedBookName.length) {
                0 -> {
                    alertDialog.dismiss()
                }
                else -> {
                    presenter.updateSelectedBook(token, book, updatedBookName)
                    alertDialog.dismiss()
                }
            }
        }

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, Const.CANCEL_BUTTON) { _, _ -> alertDialog.dismiss() }

        alertDialog.setView(promptView)
        alertDialog.show()
    }
}
