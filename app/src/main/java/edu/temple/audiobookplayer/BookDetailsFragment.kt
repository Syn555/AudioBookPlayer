package edu.temple.audiobookplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso

class BookDetailsFragment : Fragment() {
    private lateinit var coverOfBook:ImageView
    private lateinit var titleOfBook:TextView
    private lateinit var authorOfBook:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_details, container, false)
        coverOfBook = view.findViewById<ImageView>(R.id.bookCover)
        authorOfBook= view.findViewById<TextView>(R.id.authDet)
        titleOfBook = view.findViewById<TextView>(R.id.titleDet)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewModelProvider(requireActivity()).get(bookViewModel::class.java).getBook().observe(requireActivity(), {
            changeBook(it)
        })
    }
    private fun changeBook(book: Book?){
        book?.run {
            titleOfBook.text=title
            authorOfBook.text = author
            Picasso.get().load(coverURL).into(coverOfBook)
        }
    }

}