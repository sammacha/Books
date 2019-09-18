package com.example.books.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.books.BookDetail;
import com.example.books.Models.Books;
import com.example.books.R;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {

    ArrayList<Books> books;
    public BooksAdapter(ArrayList<Books>books){
        this.books = books;

    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.book_list_items,parent,false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
            Books book = books.get(position);
            holder.bind(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        TextView tvTitle;
        TextView tvAuthors;
        TextView tvPublisher;
        TextView tvDate;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthors = itemView.findViewById(R.id.tvAuthors);
            tvPublisher = itemView.findViewById(R.id.tvPublisher);
            tvDate = itemView.findViewById(R.id.tvPublishedDate);
            itemView.setOnClickListener(this);
        }

        public void bind(Books books){
            tvTitle.setText(books.title);

            //Authors is an Array so first define a string to contain all the authors
            String authors = "";
            int i = 0;

            //for each author in the array, add a new author to the string
            for (String author:books.authors){
                authors += author;
                i++;
                if (i<books.authors.length){
                    authors += ",";
                }
            }
            tvAuthors.setText(authors);
            tvPublisher.setText(books.publisher);
            tvDate.setText(books.publishedDate);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Books selectedBook =  books.get(position);
            Intent intent = new Intent(view.getContext(), BookDetail.class);
            intent.putExtra("Book",selectedBook);
            view.getContext().startActivity(intent);

        }
    }
}
