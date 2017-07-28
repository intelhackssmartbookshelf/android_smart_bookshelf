package com.books.share.smartbookshelf.lib.trans.api.itf;


import com.books.share.smartbookshelf.lib.trans.api.object.BookList;
import com.books.share.smartbookshelf.lib.trans.api.object.FcmToken;
import com.books.share.smartbookshelf.lib.trans.api.object.MyBooks;
import com.books.share.smartbookshelf.lib.trans.api.object.MyShelf;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

import java.util.ArrayList;

public interface BookshelfApiClient {
    @GET("/location/")
    Call<Object> setLocation(
            @Query("lat") String lat,
            @Query("lng") String lng);

    @GET("/fcmtoken/")
    Call<FcmToken> saveFcmToken(
            @Query("type") int tokenType,
            @Query("token") String token);

    @GET("/set_totallen/")
    Call<Object> setTotalLen(
            @Query("len") String len);

    @GET("/api/shelf/books/")
    Call<ArrayList<MyBooks>> getBooksList();

    @FormUrlEncoded
    @POST("/api/shelf/books/")
    Call<ArrayList<MyBooks>> saveMybooks(
            @Field("shelf") int shelf,
            @Field("bookTitle") String bookTitle,
            @Field("bookImgUri") String bookImgUri,
            @Field("bookPublisher") String bookPublisher,
            @Field("bookDesc") String bookDesc,
            @Field("bookInfo") String bookInfo,
            @Field("bookPosLen") double bookPosLen,
            @Field("readPos") int readPos,
            @Field("remark") String remark
    );

    @GET("/api/shelf/books/")
    Call<ArrayList<MyBooks>> getMybooks();

    @GET("/bookshelf/")
    Call<MyShelf> getShelf();

}
