package mishas.clientofapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import mishas.clientofapp.R;
import mishas.clientofapp.logic.Administrator;
import mishas.clientofapp.logic.Order;

public class MainScreenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
                intent.putExtra("numberOfClick", getIntent().getIntExtra("numberOfClick", 0));
                intent.putExtra("id", getIntent().getStringExtra("id"));
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