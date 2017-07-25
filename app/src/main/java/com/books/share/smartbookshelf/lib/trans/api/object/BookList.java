package com.books.share.smartbookshelf.lib.trans.api.object;

import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Data
public class BookList {
    private String kind;
    private int totalItems;
    private ArrayList<Book> items;

}
