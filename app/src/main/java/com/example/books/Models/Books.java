package com.example.books.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Books implements Parcelable {
    public String id;
    public String title;
    public String subTitle;
    public String [] authors;
    public String publisher;
    public String publishedDate;

    public Books(String id, String title, String subTitle, String[] authors, String publisher, String publishedDate) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
    }

    protected Books(Parcel in) {
        id = in.readString();
        title = in.readString();
        subTitle = in.readString();
        authors = in.createStringArray();
        publisher = in.readString();
        publishedDate = in.readString();
    }

    public static final Creator<Books> CREATOR = new Creator<Books>() {
        @Override
        public Books createFromParcel(Parcel in) {
            return new Books(in);
        }

        @Override
        public Books[] newArray(int size) {
            return new Books[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(subTitle);
        parcel.writeStringArray(authors);
        parcel.writeString(publisher);
        parcel.writeString(publishedDate);
    }
}
