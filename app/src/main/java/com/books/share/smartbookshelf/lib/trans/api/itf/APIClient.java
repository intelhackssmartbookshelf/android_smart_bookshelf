package com.books.share.smartbookshelf.lib.trans.api.itf;

import com.books.share.smartbookshelf.lib.trans.api.object.AccessToken;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by limjuhyun on 24/07/2017.
 */
public interface APIClient {

    @FormUrlEncoded
    @POST("/o/token/")
    Call<AccessToken> getNewAccessToken(
            @Field("username") String userName,
            @Field("password") String password,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("grant_type") String grantType);

    @FormUrlEncoded
    @POST("/o/token/")
    Call<AccessToken> getRefreshAccessToken(
            @Field("refresh_token") String refreshToken,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("grant_type") String grantType);

    @FormUrlEncoded
    @POST("/o/revoke_token/")
    Call<Object> revokeToken(
            @Field("token") String token,
            @Field("client_id") String client_id,
            @Field("client_secret") String client_secret
    );

}