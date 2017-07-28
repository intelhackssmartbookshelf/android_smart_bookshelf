package com.books.share.smartbookshelf.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.books.share.smartbookshelf.R;
import com.books.share.smartbookshelf.lib.conf.Conf;
import com.books.share.smartbookshelf.lib.trans.api.ServiceGenerator;
import com.books.share.smartbookshelf.lib.trans.api.itf.BookshelfApiClient;
import com.books.share.smartbookshelf.lib.trans.api.object.AccessToken;
import com.books.share.smartbookshelf.lib.trans.api.object.Book;
import com.books.share.smartbookshelf.lib.trans.api.object.MyBooks;
import com.books.share.smartbookshelf.lib.trans.api.object.books.VolumeInfo;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;

public class AddBookActivity extends AppCompatActivity {

    private TextView tTitle;
    private TextView tDesc;
    private ImageView tImg;
    private TextView tPublisher;
    private TextView tPublisherDate;
    private TextView tAutors;
    private MenuItem tAdd;
    private Gson gson;
    private Book book;
    private String pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        gson = new Gson();
        tImg = (ImageView) findViewById(R.id.add_book_img);
        tTitle = (TextView) findViewById(R.id.add_book_title);
        tAutors = (TextView) findViewById(R.id.add_book_authors);
        tPublisher = (TextView) findViewById(R.id.add_book_publisher);
        tPublisherDate = (TextView) findViewById(R.id.add_book_publishdate);
        tDesc = (TextView) findViewById(R.id.add_book_desc);

        Intent intent = getIntent();

        book = (Book) intent.getSerializableExtra("book");
        pos = intent.getStringExtra("pos");
        Log.d("please", book.getVolumeInfo().toString());

        try {
            Picasso.with(getApplicationContext()).load(book.getVolumeInfo().getImageLinks().get("thumbnail")).into(tImg);
        } catch (Exception e) {
            Log.e("test", "t", e);
        }

        tTitle.setText(book.getVolumeInfo().getTitle());
        try {
            tAutors.setText(android.text.TextUtils.join(",", book.getVolumeInfo().getAuthors()));
        } catch (Exception e) {
        }
        tPublisher.setText(book.getVolumeInfo().getPublisher());
        tPublisherDate.setText(book.getVolumeInfo().getPublishedDate());
        tDesc.setText(book.getVolumeInfo().getDescription());

        double ab = (Integer.parseInt(pos)/30.0)*10.0;
        Log.d("test", String.valueOf(ab));
        int a =  (int) Math.round(ab);
        Log.d("test", String.valueOf(a) + " "+pos);

        onPos(a);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_book_menu, menu);

        tAdd = menu.findItem(R.id.add_book_save);
        tAdd.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                saveBooks saveBook = new saveBooks();
                saveBook.execute(book);
                Toast.makeText(getApplicationContext(), "Successfuly saved", 1000).show();
                Intent intent = new Intent(getApplicationContext(), SmartBookshelfMainActivity.class);
                startActivity(intent);
                return false;
            }
        });

        return true;
    }
    public void onPos(int number){
        TextView textView;
        Log.d("test", String.valueOf(number));
        switch(number){
            case 1:
                textView = (TextView) findViewById(R.id.add_book_location_1);
                textView.setBackgroundColor(R.color.cardview_dark_background);
                break;
            case 2:
                textView = (TextView) findViewById(R.id.add_book_location_2);
                textView.setBackgroundColor(R.color.cardview_dark_background);
                break;
            case 3:
                textView = (TextView) findViewById(R.id.add_book_location_3);
                textView.setBackgroundColor(R.color.cardview_dark_background);
                break;
            case 4:
                textView = (TextView) findViewById(R.id.add_book_location_4);
                textView.setBackgroundColor(R.color.cardview_dark_background);
                break;
            case 5:
                textView = (TextView) findViewById(R.id.add_book_location_5);
                textView.setBackgroundColor(R.color.cardview_dark_background);
                break;
            case 6:
                textView = (TextView) findViewById(R.id.add_book_location_6);
                textView.setBackgroundColor(R.color.cardview_dark_background);
                break;
            case 7:
                textView = (TextView) findViewById(R.id.add_book_location_7);
                textView.setBackgroundColor(R.color.cardview_dark_background);
                break;
            case 8:
                textView = (TextView) findViewById(R.id.add_book_location_8);
                textView.setBackgroundColor(R.color.cardview_dark_background);
                break;
            case 9:
                textView = (TextView) findViewById(R.id.add_book_location_9);
                textView.setBackgroundColor(R.color.cardview_dark_background);
                break;
            case 10:
                textView = (TextView) findViewById(R.id.add_book_location_10);
                textView.setBackgroundColor(R.color.cardview_dark_background);
                break;

        }
    }
    private class saveBooks extends AsyncTask<Book, Void, Void> {

        @Override
        protected Void doInBackground(Book... books) {
            final SharedPreferences prefs = getApplicationContext().getSharedPreferences(
                    Conf.APPLICATION_ID, Context.MODE_PRIVATE);
            AccessToken accessToken = new AccessToken();
            accessToken.setAccess_token(prefs.getString("oauth.accesstoken", ""));
            accessToken.setRefresh_token(prefs.getString("oauth.refreshtoken", ""));
            accessToken.setToken_type(prefs.getString("oauth.tokentype", ""));
            int shelf = prefs.getInt("shelf", 3);
            Book book = books[0];
            VolumeInfo volumeInfo = book.getVolumeInfo();
            BookshelfApiClient client = ServiceGenerator.createService(BookshelfApiClient.class, accessToken, getApplicationContext());
            Call<ArrayList<MyBooks>> call = client.saveMybooks(shelf
                    , volumeInfo.getTitle()
                    , volumeInfo.getImageLinks().get("thumbnail")
                    , volumeInfo.getPublisher()
                    , volumeInfo.getDescription()
                    , gson.toJson(book)
                    , 25.0
                    , Integer.parseInt(pos)
                    , "remark");

            try {
                Response<ArrayList<MyBooks>> response = call.execute();
                Log.d("test", response.errorBody().string());
            } catch (Exception e) {
                Log.e("saveBooks", "error", e);
            }

            return null;
        }
    }
}
