package mishas.clientofapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import mishas.clientofapp.R;
import mishas.clientofapp.logic.Administrator;


public class BagActivity extends AppCompatActivity {

    private LinearLayout timeToPay;
    private TextView text;


    private void init() {
        text = (TextView) findViewById(R.id.showText);
        timeToPay = (LinearLayout) findViewById(R.id.timeToPay);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag_of_orders);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#168de2")));
        this.setTitle("Корзина");
        init();
        Administrator.currentOrder.setProducts(Administrator.productsToOrder());
        final String print = Administrator.currentOrder.getOrderString();
        if ("Ваша корзина пуста! :(".equals(print))
            timeToPay.setEnabled(false);
        text.setText(print);
        try {
            File root = new File(Environment.getExternalStorageDirectory(), "Order");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, "currentOrder.txt");
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(print);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        timeToPay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BagActivity.this, ChoiceShopActivity.class);
                intent.putExtra("orderString", print);
                startActivity(intent);
            }
        });
    }
}