package com.example.liyongjian.aidlclient;

import com.example.liyongjian.aidlclient.Book;

import java.util.List;

interface IBookManager {
    List<Book> getBookList();

    void addBook(in Book book);
}
