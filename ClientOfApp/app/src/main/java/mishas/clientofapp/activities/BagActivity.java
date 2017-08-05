package mishas.clientofapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mishas.clientofapp.R;
import mishas.clientofapp.logic.Administrator;
import mishas.clientofapp.logic.Product;
import mishas.clientofapp.logic.ProductType;


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
        String print = Administrator.currentOrder.getOrderString();
        if ("Sorry, but your bag is empty! :(".equals(print))
            timeToPay.setEnabled(false);
        text.setText(print);
        timeToPay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BagActivity.this, PaymentActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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