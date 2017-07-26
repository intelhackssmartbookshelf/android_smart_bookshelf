package com.books.share.smartbookshelf.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.books.share.smartbookshelf.R;
import com.books.share.smartbookshelf.lib.trans.api.object.Book;
import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent intent = getIntent();
        Book book = (Book) intent.getSerializableExtra("book");
        Gson gson = new Gson();

        TextView textView = (TextView) findViewById(R.id.testtest);
        textView.setText(gson.toJson(book));

    }
}
