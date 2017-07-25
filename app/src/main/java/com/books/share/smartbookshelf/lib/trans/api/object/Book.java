package com.books.share.smartbookshelf.lib.trans.api.object;

import com.books.share.smartbookshelf.lib.trans.api.object.books.SaleInfo;
import com.books.share.smartbookshelf.lib.trans.api.object.books.VolumeInfo;
import lombok.Data;

@Data
public class Book {
    private String kind;
    private String id;
    private String etag;
    private String selfLink;
    private VolumeInfo volumeInfo;
    private SaleInfo saleInfo;
}
