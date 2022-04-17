package edu.temple.audiobookplayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlayingBookViewModel: ViewModel() {

    private val book: MutableLiveData<Book> by lazy{
        MutableLiveData()
    }

    fun getBookPlaying(): LiveData<Book> {
        return book
    }

    fun setBookPlaying(selectedBook: Book?){
        this.book.value = selectedBook
    }
}