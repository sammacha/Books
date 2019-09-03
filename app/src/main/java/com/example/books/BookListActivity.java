package com.example.books;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.books.Adapters.BooksAdapter;
import com.example.books.Models.Books;

import java.net.URL;
import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private ProgressBar mLoadingProgress;
    private RecyclerView rvBooks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        mLoadingProgress = findViewById(R.id.pb_loading);

        rvBooks = findViewById(R.id.rv_books);
        LinearLayoutManager booksLinearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rvBooks.setLayoutManager(booksLinearLayoutManager);
        try {
            URL bookUrl = ApiUtil.buildUrl("cooking");
            new BooksQueryTask().execute(bookUrl);

        }
        catch (Exception e){
            Log.d("error",e.getMessage());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.book_list_menu,menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        try{
            URL bookUrl = ApiUtil.buildUrl(s);
            new BooksQueryTask().execute(bookUrl);

        }catch (Exception e){
            Log.d("error",e.getMessage());
        }

        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    public class BooksQueryTask extends AsyncTask<URL,Void,String>{

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls [0];
            String result = null;
            try{
                result = ApiUtil.getJson(searchUrl);
            }catch (Exception e){
                Log.d("Error",e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView tvError = findViewById(R.id.error);
            mLoadingProgress.setVisibility(View.INVISIBLE);
            if (result == null){
                rvBooks.setVisibility(View.INVISIBLE);
                tvError.setVisibility(View.VISIBLE);
            }else {
                rvBooks.setVisibility(View.VISIBLE);
                tvError.setVisibility(View.INVISIBLE);
            }
            ArrayList<Books> books = ApiUtil.getBooksFromJson(result);
            String resultString = "";

            BooksAdapter adapter = new BooksAdapter(books);
            rvBooks.setAdapter(adapter);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingProgress.setVisibility(View.VISIBLE);
        }
    }
}
