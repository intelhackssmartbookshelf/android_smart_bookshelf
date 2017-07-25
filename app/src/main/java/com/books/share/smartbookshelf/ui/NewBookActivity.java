package com.books.share.smartbookshelf.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.books.share.smartbookshelf.R;
import com.books.share.smartbookshelf.lib.trans.api.itf.GoogleApiClient;
import com.books.share.smartbookshelf.lib.trans.api.object.Book;
import com.books.share.smartbookshelf.lib.trans.api.object.BookList;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class NewBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);
        Intent intent = getIntent();

        String keyword = intent.getStringExtra("keyword");
        GoogleApiClient googleApiClient = GoogleApiClient.retrofit.create(GoogleApiClient.class);
        Call<BookList> call = googleApiClient.getVolumeBookList(keyword);
        new GetBookList().execute(call);

    }

    private class GetBookList extends AsyncTask<Call, Void, BookList>{

        @Override
        protected BookList doInBackground(Call... calls) {

            try {
                Call<BookList> call = calls[0];
                Response<BookList> response = call.execute();
                return response.body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(BookList bookList){
            Log.d("Books", "booksList"+bookList.toString()+" "+bookList.getItems().get(0).getVolumeInfo().getDescription());
        }
    }
}
