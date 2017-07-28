package com.books.share.smartbookshelf.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.books.share.smartbookshelf.adapter.MyBook_ListAdapter;
import com.books.share.smartbookshelf.adapter.NewBook_ListAdapter;
import com.books.share.smartbookshelf.lib.conf.Conf;
import com.books.share.smartbookshelf.lib.trans.api.ServiceGenerator;
import com.books.share.smartbookshelf.lib.trans.api.itf.BookshelfApiClient;
import com.books.share.smartbookshelf.lib.trans.api.itf.GoogleApiClient;
import com.books.share.smartbookshelf.lib.trans.api.object.AccessToken;
import com.books.share.smartbookshelf.lib.trans.api.object.BookList;
import com.books.share.smartbookshelf.lib.trans.api.object.MyBooks;
import com.books.share.smartbookshelf.ui.NewBookActivity;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;

public class Myshelf extends Fragment {

    public Myshelf() {
        // Required empty public constructor
    }

    private View mProgressView;
    private Context context;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private MyBook_ListAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_myshelf, container, false);
        context = rootView.getContext();
        final SharedPreferences prefs = context.getSharedPreferences(
                Conf.APPLICATION_ID, Context.MODE_PRIVATE);
        AccessToken accessToken = new AccessToken();
        accessToken.setAccess_token(prefs.getString("oauth.accesstoken", ""));
        accessToken.setRefresh_token(prefs.getString("oauth.refreshtoken", ""));
        accessToken.setToken_type(prefs.getString("oauth.tokentype", ""));

        BookshelfApiClient bookshelfApiClient = ServiceGenerator.createService(BookshelfApiClient.class, accessToken, context);
        Call<ArrayList<MyBooks>> call = bookshelfApiClient.getMybooks();
        new Myshelf.GetBookList().execute(call);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_myshelf, container, false);
    }

    private class GetBookList extends AsyncTask<Call, Void, ArrayList<MyBooks>> {

        @Override
        protected ArrayList<MyBooks> doInBackground(Call... calls) {
            try {
                Call<ArrayList<MyBooks>> call = calls[0];
                Response<ArrayList<MyBooks>> response = call.execute();
                return response.body();
            } catch (Exception e) {
                Log.d("test", "tets", e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<MyBooks> bookList) {
            Log.d("test", bookList.toString());
            mLinearLayoutManager = new GridLayoutManager(getActivity(), 2);
            recyclerView = (RecyclerView) getActivity().findViewById(R.id.myshelf_recycler_view);
            recyclerView.setLayoutManager(mLinearLayoutManager);
            mAdapter = new MyBook_ListAdapter(getActivity(), bookList, "10");
            recyclerView.setAdapter(mAdapter);


        }
    }

}
