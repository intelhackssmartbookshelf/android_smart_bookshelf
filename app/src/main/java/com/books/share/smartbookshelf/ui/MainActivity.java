package com.books.share.smartbookshelf.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import com.books.share.smartbookshelf.R;
import com.books.share.smartbookshelf.adapter.MainActivity_ListAdapter;
import com.books.share.smartbookshelf.item.MainActivity_ListItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private boolean mKillFlag = false;
    private Handler mKillHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sb_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ArrayList<MainActivity_ListItem> arrayList = new ArrayList<>();
        arrayList.add(new MainActivity_ListItem(0, "가나다", "당신의 지식을 가나다"));
        arrayList.add(new MainActivity_ListItem(1, "마바사", "당신의 지식을 마바사"));
        arrayList.add(new MainActivity_ListItem(2, "아자차", "당신의 지식을 아자차"));
        arrayList.add(new MainActivity_ListItem(0, "가나다", "당신의 지식을 가나다"));
        arrayList.add(new MainActivity_ListItem(1, "마바사", "당신의 지식을 마바사"));
        arrayList.add(new MainActivity_ListItem(2, "아자차", "당신의 지식을 아자차"));
        arrayList.add(new MainActivity_ListItem(0, "가나다", "당신의 지식을 가나다"));
        arrayList.add(new MainActivity_ListItem(1, "마바사", "당신의 지식을 마바사"));
        arrayList.add(new MainActivity_ListItem(2, "아자차", "당신의 지식을 아자차"));
        arrayList.add(new MainActivity_ListItem(0, "가나다", "당신의 지식을 가나다"));
        arrayList.add(new MainActivity_ListItem(1, "마바사", "당신의 지식을 마바사"));
        arrayList.add(new MainActivity_ListItem(2, "아자차", "당신의 지식을 아자차"));

        MainActivity_ListAdapter adapter = new MainActivity_ListAdapter(this, R.layout.suggest_books_list_item, arrayList);

        ListView listView = (ListView) findViewById(R.id.suggest_books_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        mKillHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    mKillFlag = false;
                }
            }
        };
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    protected void finishApp() {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        SearchView searchView;
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint(getString(R.string.main_book_search_hint));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    public class GetMainBooksList extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        if (!mKillFlag) {
            Toast.makeText(this, "Are you sure you want to quit the app?",
                    Toast.LENGTH_SHORT).show();
            mKillFlag = true;
            mKillHandler.sendEmptyMessageDelayed(0, 2000);

        } else {
            finishApp();
        }
    }


}
