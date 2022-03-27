package edu.temple.audiobookplayer

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val BookListKey = "param1"

class BookListFragment : Fragment() {
    private var bookList: ArrayList<BookObject>? = null
    private lateinit var bookViewModel: BookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bookViewModel = ViewModelProvider(requireActivity()).get(BookViewModel::class.java)
        arguments?.let {
            bookList = it.getParcelableArrayList<BookObject>(BookListKey);
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_list, container, false) as RecyclerView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(view as RecyclerView){
            bookList?.run {
                val clickEvent = {
                        book : BookObject -> bookViewModel.setSelectedBook(book)
                    (requireActivity() as BookListFragmentInterface).bookSelected()
                }
                layoutManager = LinearLayoutManager(requireContext())
                adapter = BookAdapter(this,clickEvent)
            }
        }
    }

    class BookAdapter(_books : ArrayList<BookObject>, _clickEvent : (BookObject) -> Unit) : RecyclerView.Adapter<BookAdapter.BookViewHolder>(){

        val books = _books
        val clickEvent = _clickEvent

        class BookViewHolder(_view : View) : RecyclerView.ViewHolder(_view){
            val view = _view
            val titleTxt = _view.findViewById<TextView>(R.id.titleText)
            val authorTxt = _view.findViewById<TextView>(R.id.authorText)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
            return BookViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.book_list_layout, parent, false)
            )
        }

        override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
            holder.titleTxt.text = books[position].title
            holder.authorTxt.text = books[position].author
            holder.view.setOnClickListener{clickEvent(books[position])}
        }

        override fun getItemCount(): Int {
            return books.size
        }
    }

    companion object {
        fun newInstance(bookList: BookList) =
            BookListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(BookListKey, bookList.list)
                }
            }
    }

    interface BookListFragmentInterface {
        fun bookSelected()
    }

}