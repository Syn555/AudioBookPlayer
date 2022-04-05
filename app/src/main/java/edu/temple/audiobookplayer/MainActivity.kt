package edu.temple.audiobookplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button


class MainActivity : AppCompatActivity(), BookListFragment.BookListFragmentInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val title = resources.getStringArray(R.array.title)
        val author = resources.getStringArray(R.array.author)
        val bookList = BookList()
        val search = findViewById<Button>(R.id.search)
        search.setOnClickListener {
            onSearchRequested()
        }
        val fragment = supportFragmentManager.findFragmentById(R.id.container1)
        if(fragment != null)
            supportFragmentManager.beginTransaction().remove(fragment).commit()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container1, BookListFragment.newInstance(bookList))
            .commit()

    }

    override fun bookSelected() {
        if(findViewById<View>(R.id.container2) == null)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container1,BookDetailsFragment())
                .addToBackStack(null)
                .commit()
    }


}