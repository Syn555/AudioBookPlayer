package edu.temple.audiobookplayer

class BookList {
    val list = arrayListOf<BookObject>()
    fun add(book : BookObject){
        list.add(book)
    }

    fun remove(book : BookObject){
        list.remove(book)
    }

    operator fun get(index : Int) : BookObject{
        return list.get(index)
    }

    fun size() : Int{
        return list.size
    }
}