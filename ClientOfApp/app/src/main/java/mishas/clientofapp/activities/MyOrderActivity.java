package mishas.clientofapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import mishas.clientofapp.R;

public class MyOrderActivity extends AppCompatActivity {

    private Button[] buttons;
    private Button okey;
    private LinearLayout toShopNav;
    private String currentId = "";
    Integer[] imageId = {
            R.mipmap.hotdog,
            R.mipmap.hotcorn,
            R.mipmap.burger,
            R.mipmap.chips,
            R.mipmap.tea,
            R.mipmap.coffee,
            R.mipmap.water,
            R.mipmap.juice,
    };

    private void init() {
        toShopNav = (LinearLayout) findViewById(R.id.toShopLL);
        okey = (Button) findViewById(R.id.okey);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_my_order);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#168de2")));
        this.setTitle("Мой заказ");
        init();
        final String lastPush = getIntent().getStringExtra("place");
        TextView tv = (TextView) findViewById(R.id.summary);
        TextView shop = (TextView) findViewById(R.id.myShopString);
        currentId = getIntent().getStringExtra("id");
        if (currentId == null) currentId = "";
        this.setTitle("Мой заказ №" + currentId);

        // tv.setText("Ваш заказ №" + currentId);
        ListView currentOrder = (ListView) findViewById(R.id.currentOrder);

        if (lastPush != null) {
            String str = getIntent().getStringExtra("orderString");
            String[] splString = Arrays.copyOf(str.split("\n"), str.split("\n").length - 2);
            Log.d("yoi", Arrays.toString(splString));
            MyOrderCustomList adapter = new MyOrderCustomList(this, splString, imageId);
            currentOrder.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            String[] splitted = str.split("\n");
            tv.setText(splitted[splitted.length - 1]);
            shop.setText(getIntent().getStringExtra("place").split("_")[3]);
        }
        toShopNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyOrderActivity.this, ShopNavigationActivity.class);
                intent.putExtra("id", getIntent().getStringExtra("id"));
                intent.putExtra("place", lastPush);
                intent.putExtra("orderString", getIntent().getStringExtra("orderString"));
                startActivity(intent);
            }
        });

        okey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyOrderActivity.this, MainScreenActivity.class);
                intent.putExtra("id", getIntent().getStringExtra("id"));
                intent.putExtra("place", lastPush);
                intent.putExtra("orderString", getIntent().getStringExtra("orderString"));
                startActivity(intent);
            }
        });
    }
}
