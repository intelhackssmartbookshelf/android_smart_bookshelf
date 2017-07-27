package com.books.share.smartbookshelf.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import com.books.share.smartbookshelf.R;
import com.books.share.smartbookshelf.ui.fragment.MainListFragment;
import com.books.share.smartbookshelf.ui.fragment.Myshelf;

public class SmartBookshelfMainActivity extends AppCompatActivity {
    private SearchView mSearchView;
    private TabPagerAdapter tabPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_bookshelf_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.main);

        mTabLayout = (TabLayout) findViewById(R.id.detail_tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(tabPagerAdapter);
        mTabLayout.setTabsFromPagerAdapter(tabPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mSearchView = (SearchView) toolbar.getMenu().findItem(R.id.action_search).getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        setSupportActionBar(toolbar);


    }

    private class TabPagerAdapter extends FragmentPagerAdapter {
        private String fragments[] = {"My bookshelf", "Finding books", "Set my shelf"};

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public TabPagerAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Myshelf();
                case 1:
                    return new MainListFragment();
                case 2:
                    return new Myshelf();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments[position];
        }
    }
}
