package com.books.share.smartbookshelf.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.books.share.smartbookshelf.R;
import com.books.share.smartbookshelf.lib.conf.Conf;
import com.books.share.smartbookshelf.lib.trans.api.ServiceGenerator;
import com.books.share.smartbookshelf.lib.trans.api.itf.APIClient;
import com.books.share.smartbookshelf.lib.trans.api.object.AccessToken;
import com.books.share.smartbookshelf.ui.fragment.MainListFragment;
import com.books.share.smartbookshelf.ui.fragment.Myshelf;
import retrofit2.Call;

public class SmartBookshelfMainActivity extends AppCompatActivity {
    private TabPagerAdapter tabPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private boolean mKillFlag = false;
    private Handler mKillHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_bookshelf_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        mTabLayout = (TabLayout) findViewById(R.id.detail_tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(tabPagerAdapter);
        mTabLayout.setTabsFromPagerAdapter(tabPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        setSupportActionBar(toolbar);

        mKillHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    mKillFlag = false;
                }
            }
        };

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                SharedPreferences prefs = getApplicationContext().getSharedPreferences(Conf.APPLICATION_ID, Context.MODE_PRIVATE);

                RevokeToken revokeToken = new RevokeToken(prefs.getString("oauth.accesstoken", ""));
                revokeToken.execute();

                break;
            case R.id.setting:
                Toast.makeText(this, "2222", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    protected void finishApp() {
        finish();
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

    private class RevokeToken extends AsyncTask<Void, Void, Void>{
        private String token;

        RevokeToken(String token) {
            this.token = token;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                APIClient apiClient = ServiceGenerator.createService(APIClient.class);
                Call<Object> revoke = apiClient.revokeToken(token, Conf.API_OAUTH_CLIENTID, Conf.API_OAUTH_CLIENTSECRET);
                try{
                    revoke.execute();
                } catch (Exception e) {};
            } catch (Exception e){
                Log.e("login", "revoke", e);
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void ds) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);

        }
    }
}
