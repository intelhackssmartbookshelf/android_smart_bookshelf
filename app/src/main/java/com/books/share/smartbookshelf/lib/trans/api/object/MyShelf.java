package com.books.share.smartbookshelf.lib.trans.api.object;

import lombok.Data;

@Data
public class MyShelf {
    private int id;
    private int user;
    private double totalShelfLen;
    private double lat;
    private double lng;
}