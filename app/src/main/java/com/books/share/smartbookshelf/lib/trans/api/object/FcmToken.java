package com.books.share.smartbookshelf.lib.trans.api.object;

import lombok.Data;

@Data
public class FcmToken {
    private String shelf;
    private int tokenType;
    private String token;
    private String remark;
}
