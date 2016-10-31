package com.example.gabriela.aplicacao;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        mDrawerToggle = new ActionBarDrawerToggle(this,
                                                  mDrawerLayout,
                                                  R.string.drawer_open,
                                                  R.string.drawer_close);
        mDrawerToggle.syncState();

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        selectItem(0);
    }

    private void selectItem(int position) {
        Fragment fragment = null;
        String title = null;

        switch (position) {
            case 0:
                fragment = new FilmesFragment();
                title = "Em cartaz";
                break;
            case 1:
                fragment = new FavoritosFragment();
                title = "Favoritos";
                break;
            case 3:
                fragment = new AtoresFragment();
                title = "Atores";
                break;
        }

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                       .replace(R.id.content_frame, fragment)
                       .commit();

        setTitle(title);
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_movies:
                selectItem(0);
                break;
            case R.id.action_favorites:
                selectItem(1);
                break;
            case R.id.action_actors:
                selectItem(3);
                break;
        }

        return true;
    }
}
