package edu.temple.audiobookplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() , BookListFragment.BookSelectedInterface{

    private lateinit var bookListFragment: BookListFragment

    private val searchRequest = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        supportFragmentManager.popBackStack()
        it.data?.run {
            bookListViewModel.copyBooks(getSerializableExtra(BookList.BOOKLIST_KEY)as BookList)
            bookListFragment.bookListUpdated()
        }
    }
    private val isSingleContainer : Boolean by lazy{
        findViewById<View>(R.id.container2) == null
    }

    private val selectedBookViewModel : bookViewModel by lazy {
        ViewModelProvider(this).get(bookViewModel::class.java)
    }

    private val bookListViewModel : BookList by lazy {
        ViewModelProvider(this).get(BookList::class.java)
    }
    companion object {
        const val BOOKLISTFRAGMENT_KEY = "BookListFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // If we're switching from one container to two containers
        // clear BookDetailsFragment from container1
        if(supportFragmentManager.findFragmentById(R.id.container1) is BookDetailsFragment
            && selectedBookViewModel.getBook().value!=null){
            supportFragmentManager.popBackStack()
        }

        if(savedInstanceState ==null){
            bookListFragment= BookListFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.container1,bookListFragment, BOOKLISTFRAGMENT_KEY)
                .commit()
        }else{
            bookListFragment=supportFragmentManager.findFragmentByTag(BOOKLISTFRAGMENT_KEY) as BookListFragment
            if(isSingleContainer&&selectedBookViewModel.getBook().value!=null){
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container1, BookDetailsFragment())
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit()
            }
        }

        if(!isSingleContainer&& supportFragmentManager.findFragmentById(R.id.container2) !is BookDetailsFragment)
            supportFragmentManager.beginTransaction()
                .add(R.id.container2,BookDetailsFragment())
                .commit()
        findViewById<ImageButton>(R.id.searchButton).setOnClickListener{
            searchRequest.launch(Intent(this, SearchActivity::class.java))
        }
    }//end of onCreate

    //callbacks in reverse
    override fun onBackPressed() {
        selectedBookViewModel.setSelectedBook(null)
        super.onBackPressed()
    }//end of onBackPressed

    override fun bookSelected(){
        if(isSingleContainer){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container1,BookDetailsFragment())
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit()
        }
    }//end of selectedBook

}//end of class