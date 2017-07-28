package com.books.share.smartbookshelf.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.books.share.smartbookshelf.R;
import com.books.share.smartbookshelf.lib.trans.api.object.Book;
import com.squareup.picasso.Picasso;

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

        Intent intent = getIntent();
        Book book = (Book) intent.getSerializableExtra("book");

        tImg = (ImageView) findViewById(R.id.detail_book_img);
        tTitle = (TextView) findViewById(R.id.detail_book_title);
        tAutors = (TextView) findViewById(R.id.detail_book_authors);
        tPublisher = (TextView) findViewById(R.id.detail_book_publisher);
        tPublisherDate = (TextView) findViewById(R.id.detail_book_publishdate);
        tDesc = (TextView) findViewById(R.id.detail_book_desc);
        try {
            Picasso.with(getApplicationContext()).load(book.getVolumeInfo().getImageLinks().get("thumbnail")).into(tImg);
        } catch (Exception e) {
            tImg.setImageResource(R.mipmap.no_cover_thumb);
        }

        tTitle.setText(book.getVolumeInfo().getTitle());
        try {
            tAutors.setText(android.text.TextUtils.join(",", book.getVolumeInfo().getAuthors()));
        } catch (Exception e) {
        }
        ;
        tPublisher.setText(book.getVolumeInfo().getPublisher());
        tPublisherDate.setText(book.getVolumeInfo().getPublishedDate());
        tDesc.setText(book.getVolumeInfo().getDescription());
    }
}
