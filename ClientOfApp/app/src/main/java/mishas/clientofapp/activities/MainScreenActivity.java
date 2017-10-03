package mishas.clientofapp.activities;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import mishas.clientofapp.R;
import mishas.clientofapp.logic.Administrator;
import mishas.clientofapp.logic.Client;
import mishas.clientofapp.logic.Order;

public class MainScreenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Menu myMenu;
    ListView list;
    EventCustomList adapter;
    String currentOrderId = "";
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
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Socket socket = new Socket("192.168.137.134", 11100);
                            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                            out.println("type3#" + getIntent().getStringExtra("id"));
                            BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            String message =  bf.readLine();
                            boolean isTrue = message.equals("true") && message != null;
                            if (isTrue) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        MenuItem menuItem = navigationView.getMenu().findItem(R.id.my_order);
                                        SpannableString s = new SpannableString(menuItem.getTitle());
                                        s.setSpan(new ForegroundColorSpan(Color.GREEN), 0, s.length(), 0);
                                        menuItem.setTitle(s);
                                    }
                                });
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();




        Administrator.currentOrder = new Order();
        setTitle("События");
        adapter = new EventCustomList(this, web, web_def, imageId);
        list = (ListView)findViewById(R.id.main_screen_list);
        list.setAdapter(adapter);

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
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
            case R.id.my_order:
                Intent intent = new Intent(this, MyOrderActivity.class);
                intent.putExtra("place", getIntent().getStringExtra("place"));
                intent.putExtra("orderString", getIntent().getStringExtra("orderString"));
                Log.d("AAAAAAAAAAAAa", getIntent().getStringExtra("orderString"));
                currentOrderId = getIntent().getStringExtra("id");
                intent.putExtra("id", currentOrderId);
                if (getIntent().getStringExtra("id") != null)
                    Log.d("idid", getIntent().getStringExtra("id"));
                startActivity(intent);
                break;
            case R.id.payment_methods:
                startActivity(new Intent(this, PaymentMethodsActivity.class));
                break;
            case R.id.exit:
                startActivity(new Intent(this, StartingActivity.class));
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}