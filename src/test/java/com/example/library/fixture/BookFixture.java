package com.example.library.fixture;

import com.example.library.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookFixture {

    public static List<Book> getListBooks(){
        List<Book> list = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            list.add(new Book("Author_"+i,"Book_"+i,i+10l,i+150l));
        }
        return list;
    }
}
