package com.books.share.smartbookshelf.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.books.share.smartbookshelf.R;
import com.books.share.smartbookshelf.adapter.NewBook_ListAdapter;
import com.books.share.smartbookshelf.lib.trans.api.itf.GoogleApiClient;
import com.books.share.smartbookshelf.lib.trans.api.object.Book;
import com.books.share.smartbookshelf.lib.trans.api.object.BookList;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;

public class NewBookActivity extends AppCompatActivity {

    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView recyclerView;
    private NewBook_ListAdapter mAdapter;
    private View mProgressView;
    private String pos;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);
        intent = getIntent();

        String keyword = intent.getStringExtra("keyword");
        pos = intent.getStringExtra("pos");
        GoogleApiClient googleApiClient = GoogleApiClient.retrofit.create(GoogleApiClient.class);
        Call<BookList> call = googleApiClient.getVolumeBookList(keyword, "40");
        new GetBookList().execute(call);

        mProgressView = findViewById(R.id.login_progress_booklist);
    }

    private class GetBookList extends AsyncTask<Call, Void, BookList>{

        @Override
        protected BookList doInBackground(Call... calls) {
            showProgress(true);

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

            mLinearLayoutManager = new GridLayoutManager(NewBookActivity.this, 2);
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(mLinearLayoutManager);
            mAdapter = new NewBook_ListAdapter(getApplicationContext(), bookList.getItems(), pos);
            recyclerView.setAdapter(mAdapter);
            showProgress(false);


        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);


            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }
}
