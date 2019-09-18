package com.example.books;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.books.Models.Books;

public class BookDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        Books book = getIntent().getParcelableExtra("Book");

    }
}
