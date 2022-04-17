package edu.temple.audiobookplayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.temple.audlibplayer.PlayerService

class BookViewModel: ViewModel() {

    private val book: MutableLiveData<Book> by lazy {
        MutableLiveData()
    }

    fun getBook(): LiveData<Book> {
        return book
    }

    fun setSelectedBook(item: Book?) {
        this.book.value = item
    }
}