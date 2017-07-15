package mishas.clientofapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import mishas.clientofapp.R;
import mishas.clientofapp.logic.Administrator;

public class BagActivity extends AppCompatActivity {

    private Button timeToPay;
    private TextView text;

    private void init() {
        text = (TextView) findViewById(R.id.showText);
        timeToPay = (Button) findViewById(R.id.timeToPay);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag_of_orders);
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
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


}