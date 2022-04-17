package edu.temple.audiobookplayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(listofBooks: BookList, clicked: (Book)->Unit): RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    val list = listofBooks
    val clicked = clicked

    class ViewHolder(itemView: View, clicked: (Book)->Unit): RecyclerView.ViewHolder(itemView){
        lateinit var Book:Book
        var title: TextView = itemView.findViewById(R.id.Booktitle)
        var author: TextView = itemView.findViewById(R.id.BookAuthor)
        init{
            title = itemView.findViewById(R.id.Booktitle)
            author= itemView.findViewById(R.id.BookAuthor)
            title.setOnClickListener {
                clicked(Book)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.book, parent, false), clicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.author.text=list[position]?.title
        holder.title.text= list[position]?.author
        holder.Book= list.get(position)!!
    }
    override fun getItemCount(): Int {
        return list.size()
    }
}