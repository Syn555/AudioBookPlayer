package edu.temple.audiobookplayer

import androidx.lifecycle.ViewModel
import org.json.JSONArray
import java.io.Serializable

class BookList : ViewModel(),Serializable{
    companion object{
        val BOOKLIST_KEY = "Booklist"
    }
    private val bookList : ArrayList<Book> by lazy {
        ArrayList()
    }

    fun add(book: Book) {
        bookList.add(book)
    }

    fun clearBooks(){
        bookList.clear()
    }

    fun populateBooks (books: JSONArray) {
        for (j in 0 until books.length()) {
            bookList.add(Book(books.getJSONObject(j)))
        }
    }
    fun copyBooks(newBooks: BookList){
        bookList.clear()
        bookList.addAll(newBooks.bookList)
    }
    operator fun get(index: Int) = bookList.get(index)
    fun size() = bookList.size
}