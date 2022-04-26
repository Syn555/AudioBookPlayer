package edu.temple.audiobookplayer

import android.util.SparseArray

class API {
    companion object URL {
        val DownloadedList: ArrayList<String> by lazy{
            ArrayList()
        }
        fun getBookDataUrl(bookId: Int): String {
            return "https://kamorris.com/lab/audlib/download.php?id=${bookId}"
        }
    }
}