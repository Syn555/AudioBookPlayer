package edu.temple.audiobookplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import android.util.Log

class SearchActivity : AppCompatActivity() {
    val searchButton: ImageButton by lazy{
        findViewById(R.id.searchButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        this.setFinishOnTouchOutside(false)
        searchButton.setOnClickListener {
            val url = "https://kamorris.com/lab/cis3515/search.php?term="+
                    findViewById<EditText>(R.id.booksearch).text.toString()
            Log.d("URL:", url)
            Volley.newRequestQueue(this).add(
                JsonArrayRequest(Request.Method.GET, url, null, {
                    setResult(
                        RESULT_OK,
                        Intent().putExtra(
                            BookList.BOOKLIST_KEY,
                            BookList().apply { populateBooks(it) })
                    )
                    finish()
                }, {})
            )
        }
    }
}