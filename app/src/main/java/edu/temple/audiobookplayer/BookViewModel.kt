package edu.temple.audiobookplayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookViewModel : ViewModel(){

    val selectedBookObject : MutableLiveData<BookObject> by lazy{
        MutableLiveData<BookObject>()
    }

    fun setSelectedBook(book : BookObject){
        selectedBookObject.value = book
    }

    fun getSeletedBook() : LiveData<BookObject>{
        return selectedBookObject
    }

}