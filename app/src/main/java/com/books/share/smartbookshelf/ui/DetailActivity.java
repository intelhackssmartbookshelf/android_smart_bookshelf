package com.books.share.smartbookshelf.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.books.share.smartbookshelf.R;
import com.books.share.smartbookshelf.lib.trans.api.object.Book;
import com.books.share.smartbookshelf.lib.trans.api.object.MyBooks;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    private TextView tTitle;
    private TextView tDesc;
    private ImageView tImg;
    private TextView tPublisher;
    private TextView tPublisherDate;
    private TextView tAutors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        Gson gson = new Gson();

        Intent intent = getIntent();
        MyBooks book = (MyBooks) intent.getSerializableExtra("book");
        Book org_book = gson.fromJson(book.getBookInfo(), Book.class);
        tImg = (ImageView) findViewById(R.id.detail_book_img);
        tTitle = (TextView) findViewById(R.id.detail_book_title);
        tAutors = (TextView) findViewById(R.id.detail_book_authors);
        tPublisher = (TextView) findViewById(R.id.detail_book_publisher);
        tPublisherDate = (TextView) findViewById(R.id.detail_book_publishdate);
        tDesc = (TextView) findViewById(R.id.detail_book_desc);
        try {
            Picasso.with(getApplicationContext()).load(book.getBookImgUri()).into(tImg);
        } catch (Exception e) {
            tImg.setImageResource(R.mipmap.no_cover_thumb);
        }
        tTitle.setText(book.getBookTitle());
        try {
            tAutors.setText(book.getBookPublisher());
        } catch (Exception e) {
        }

        tPublisher.setText(book.getBookPublisher());
        tPublisherDate.setText(org_book.getVolumeInfo().getPublishedDate());
        tDesc.setText(org_book.getVolumeInfo().getDescription());

        double ab = (book.getReadPos()/30.0)*10.0;
        Log.d("test", String.valueOf(ab));
        int a =  (int) Math.round(ab);
        Log.d("test", String.valueOf(a) + " "+book.getReadPos());
        onPos(a);
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
}
