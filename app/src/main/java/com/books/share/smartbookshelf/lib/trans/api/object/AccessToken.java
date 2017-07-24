package com.books.share.smartbookshelf.lib.trans.api.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by limjuhyun on 24/07/2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessToken {
    private String access_token;
    private String token_type;
    private Integer expires_in;
    private String refresh_token;
    private String scope;
    private String client_id;
    private String client_secret;
}
