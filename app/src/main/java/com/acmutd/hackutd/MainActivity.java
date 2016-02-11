package com.acmutd.hackutd;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private String[] navigationTitles = {"Events", "Announcements", "Social", "Contact", "Map", "FAQ"};
    private int[] navigationIcons = {
            R.drawable.ic_event,
            R.drawable.ic_announcement,
            R.drawable.ic_group,
            R.drawable.ic_message,
            R.drawable.ic_map,
            R.drawable.ic_help
    };

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    DrawerLayout drawerLayout;

    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);
        adapter = new NavigationAdapter(navigationTitles, navigationIcons);

        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
