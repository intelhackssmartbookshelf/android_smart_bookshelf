package com.books.share.smartbookshelf.lib.trans.api.object;

import com.books.share.smartbookshelf.lib.trans.api.object.books.SaleInfo;
import com.books.share.smartbookshelf.lib.trans.api.object.books.VolumeInfo;
import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {
    private String kind;
    private String id;
    private String etag;
    private String selfLink;
    private VolumeInfo volumeInfo;
    private SaleInfo saleInfo;
}
