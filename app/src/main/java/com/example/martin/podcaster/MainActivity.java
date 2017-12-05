package com.example.martin.podcaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public BottomSheetBehavior bottomSheetBehavior ;
    public ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            SlidingTabsBasicFragment fragment = new SlidingTabsBasicFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }
        */


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout;
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LinearLayout bottomSheetViewGroup = findViewById(R.id.bottom_sheet_player);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetViewGroup);

        final ImageButton sheetPlay = findViewById(R.id.imageButton);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED){
                    sheetPlay.setImageResource(R.drawable.ic_info_outline_black_24dp);
                }
                if (newState == BottomSheetBehavior.STATE_COLLAPSED){
                    sheetPlay.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // React to dragging events
            }
        });

        SeekBar seekBar = findViewById(R.id.seekBar2);
        seekBar.setProgress(35);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }else if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; american adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_update) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_podcasts) {
            Intent intent = new Intent(this, PodcastsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_queue) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else if (id == R.id.nav_add) {
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_all) {
            viewPager.setCurrentItem(0);
        } else if (id == R.id.nav_fav) {
            viewPager.setCurrentItem(1);
        } else if (id == R.id.nav_down) {
            viewPager.setCurrentItem(2);
        } else if (id == R.id.nav_not_list) {
            viewPager.setCurrentItem(3);
        } else if (id == R.id.nav_user_list) {
            viewPager.setCurrentItem(4);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "All");
        adapter.addFragment(new TwoFragment(), "Favourites");
        adapter.addFragment(new ThreeFragment(), "Downloaded");
        adapter.addFragment(new FourFragment(), "Not listened");
        adapter.addFragment(new FiveFragment(), "User list");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
