package com.books.share.smartbookshelf.lib.trans.api.itf;


import com.books.share.smartbookshelf.lib.trans.api.object.BookList;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookshelfApiClient {
    @GET("/location/")
    Call<Object> setLocation(
            @Query("lat") String lat,
            @Query("lng") String lng);

}
