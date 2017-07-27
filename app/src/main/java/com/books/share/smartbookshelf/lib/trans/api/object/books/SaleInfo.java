package com.books.share.smartbookshelf.lib.trans.api.object.books;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class SaleInfo implements Serializable {
    private String country;
    private String saleability;
    private boolean isEbook;
    private Map<String, Object> listPrice;
    private Map<String, Object> retailPrice;
    private String buyLink;
}
