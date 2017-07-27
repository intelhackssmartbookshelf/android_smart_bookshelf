package com.books.share.smartbookshelf.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.books.share.smartbookshelf.R;
import com.books.share.smartbookshelf.adapter.NewBook_ListAdapter;
import com.books.share.smartbookshelf.lib.trans.api.itf.GoogleApiClient;
import com.books.share.smartbookshelf.lib.trans.api.object.BookList;
import com.books.share.smartbookshelf.ui.NewBookActivity;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class Myshelf extends Fragment {

    public Myshelf() {
        // Required empty public constructor
    }

    private View mProgressView;
    private Context context;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private NewBook_ListAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_myshelf, container, false);
        context = rootView.getContext();

        GoogleApiClient googleApiClient = GoogleApiClient.retrofit.create(GoogleApiClient.class);
        Call<BookList> call = googleApiClient.getVolumeBookList("test", "40");
        new Myshelf.GetBookList().execute(call);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_myshelf, container, false);
    }

    private class GetBookList extends AsyncTask<Call, Void, BookList> {

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
        protected void onPostExecute(BookList bookList) {
            Log.d("Books", "booksList" + bookList.toString() + " " + bookList.getItems().get(0).getVolumeInfo().getDescription());

            mLinearLayoutManager = new GridLayoutManager(getActivity(), 2);
            recyclerView = (RecyclerView) getActivity().findViewById(R.id.myshelf_recycler_view);
            recyclerView.setLayoutManager(mLinearLayoutManager);
            mAdapter = new NewBook_ListAdapter(getActivity(), bookList.getItems(), "10");
            recyclerView.setAdapter(mAdapter);


        }
    }

}
