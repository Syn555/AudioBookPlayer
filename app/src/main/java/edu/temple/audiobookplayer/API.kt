package edu.temple.audiobookplayer

class API {
    companion object URL {
        fun getBookDataUrl(id: Int): String {
            return "https://kamorris.com/lab/cis3515/book.php?id=${id}"
        }
    }
}