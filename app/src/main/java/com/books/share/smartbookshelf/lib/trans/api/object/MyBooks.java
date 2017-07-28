package com.books.share.smartbookshelf.lib.trans.api.object;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyBooks implements Serializable {
    private String bookTitle;
    private String bookImgUri;
    private String bookPublisher;
    private String bookDesc;
    private String bookInfo;
    private double booksPosLen;
    private int readPos;
    private String remark;
}