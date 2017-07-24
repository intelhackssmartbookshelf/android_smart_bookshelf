package com.books.share.smartbookshelf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.books.share.smartbookshelf.R;
import com.books.share.smartbookshelf.adapter.MainActivity_ListAdapter;
import com.books.share.smartbookshelf.item.MainActivity_ListItem;

import java.util.ArrayList;

public class searchActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
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

        ListView listView = (ListView) findViewById(R.id.full_books_listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(searchActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchactivity, menu);
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
}
