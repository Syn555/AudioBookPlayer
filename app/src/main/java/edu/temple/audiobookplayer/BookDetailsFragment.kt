package edu.temple.audiobookplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import android.widget.ImageView
import com.squareup.picasso.Picasso

class BookDetailsFragment : Fragment() {
    lateinit var bookViewModel: BookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bookViewModel = ViewModelProvider(requireActivity()).get(BookViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookViewModel.getSelectedBook().observe(requireActivity()){
            val titleTxt = view?.findViewById<TextView>(R.id.bigTitle)
            val authorTxt = view?.findViewById<TextView>(R.id.bigAuthor)
            val bookcover = view?.findViewById<ImageView>(R.id.bookcover)
            Picasso.get().load(it.url).into(bookcover)
            titleTxt.setText(it.title)
            authorTxt.setText(it.author)
        }
    }
}