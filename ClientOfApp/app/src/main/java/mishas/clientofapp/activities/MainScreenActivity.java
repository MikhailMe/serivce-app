package mishas.clientofapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import java.util.Arrays;

import mishas.clientofapp.R;
import mishas.clientofapp.fragments.FoodFragment;
import mishas.clientofapp.fragments.HelpFragment;
import mishas.clientofapp.fragments.SouvenirsFragment;
import mishas.clientofapp.fragments.SportsReplayFragment;
import mishas.clientofapp.logic.Administrator;
import mishas.clientofapp.logic.Order;

public class MainScreenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnClickListener {

    ListView list;
    EventCustomList adapter;
    String[] web = {
            "Матч чемпионата мира по футболу",
            "Белый лебедь в Мариинском театре",
            "Выступление рок-группы RCHP",
    };
    String[] web_def = {
            "19.09.2017",
            "1.09.2017 - 31.09.2017",
            "6.09.2017",
    };
    Integer[] imageId = {
            R.drawable.football,
            R.drawable.theatre,
            R.drawable.concert,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //?attr/colorPrimary

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Administrator.currentOrder = new Order();
        setTitle("События");
        adapter = new EventCustomList(this, web, web_def, imageId);
        list = (ListView)findViewById(R.id.main_screen_list);
        list.setAdapter(adapter);
//        foodFr = new FoodFragment();
//        souvenirsFr = new SouvenirsFragment();
//        sportsReplayFr = new SportsReplayFragment();
//        helpFr = new HelpFragment();
//
//        Button food = (Button) findViewById(R.id.food_btn);
//        food.setOnClickListener(this);
//        Button souvenirs = (Button) findViewById(R.id.souvenirs_btn);
//        souvenirs.setOnClickListener(this);
//        Button replay = (Button) findViewById(R.id.replay_btn);
//        replay.setOnClickListener(this);
//        Button bag = (Button) findViewById(R.id.bag_btn);
//        bag.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_food:
                //frTransaction.replace(R.id.container, foodFr);
                Intent intent = new Intent(this, FoodActivity.class);
                intent.putExtra("from", "main");
                startActivity(intent);
            case R.id.nav_logout:
                startActivity(new Intent(this, StartActivity.class));
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.food_btn: {
//                //frTransaction.replace(R.id.container, foodFr);
//                Intent intent = new Intent(this, FoodActivity.class);
//                intent.putExtra("from", "main");
//                startActivity(intent);
//
//                break;
//            }
//            case R.id.bag_btn: {
//                startActivity(new Intent(this, BagActivity.class));
//                break;
//            }
//        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}