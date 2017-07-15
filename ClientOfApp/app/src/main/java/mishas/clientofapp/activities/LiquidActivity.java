package mishas.clientofapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import mishas.clientofapp.R;
import mishas.clientofapp.logic.Administrator;
import mishas.clientofapp.logic.ProductType;

public class LiquidActivity extends AppCompatActivity implements OnClickListener {

    private Button plus_1, plus_2, plus_3, plus_4;
    private Button minus_1, minus_2, minus_3, minus_4;
    private TextView amount_1, amount_2, amount_3, amount_4;

    private Button viewOrder2;
    private int i1, i2, i3, i4;

    private void init() {
        plus_1 = (Button) findViewById(R.id.plus_1);
        plus_2 = (Button) findViewById(R.id.plus_2);
        plus_3 = (Button) findViewById(R.id.plus_3);
        plus_4 = (Button) findViewById(R.id.plus_4);

        minus_1 = (Button) findViewById(R.id.minus_1);
        minus_2 = (Button) findViewById(R.id.minus_2);
        minus_3 = (Button) findViewById(R.id.minus_3);
        minus_4 = (Button) findViewById(R.id.minus_4);

        amount_1 = (TextView) findViewById(R.id.amount_1);
        amount_2 = (TextView) findViewById(R.id.amount_2);
        amount_3 = (TextView) findViewById(R.id.amount_3);
        amount_4 = (TextView) findViewById(R.id.amount_4);

        viewOrder2 = (Button) findViewById(R.id.viewOrder2);
        i1 = 0;
        i2 = 0;
        i3 = 0;
        i4 = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liquid);
        init();
        plus_1.setOnClickListener(this);
        plus_1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i1 < 20)
                    i1++;
                Administrator.products.put(ProductType.TEA, i1);
                amount_1.setText(String.valueOf(i1));
            }
        });
        plus_2.setOnClickListener(this);
        plus_3.setOnClickListener(this);
        plus_4.setOnClickListener(this);

        minus_1.setOnClickListener(this);
        minus_2.setOnClickListener(this);
        minus_3.setOnClickListener(this);
        minus_4.setOnClickListener(this);

        viewOrder2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.plus_1: {
                if (i1 < 20)
                    i1++;
                Administrator.products.put(ProductType.TEA, i1);
                amount_1.setText(String.valueOf(i1));
                break;
            }
            case R.id.plus_2: {
                if (i2 < 20)
                    i2++;
                Administrator.products.put(ProductType.COFFEE, i2);
                amount_2.setText(String.valueOf(i2));
                break;
            }
            case R.id.plus_3: {
                if (i3 < 20)
                    i3++;
                Administrator.products.put(ProductType.WATER, i3);
                amount_3.setText(String.valueOf(i3));
                break;
            }
            case R.id.plus_4: {
                if (i4 < 20)
                    i4++;
                Administrator.products.put(ProductType.JUICE, i4);
                amount_4.setText(String.valueOf(i4));
                break;
            }
            case R.id.minus_1: {
                if (i1 > 0)
                    i1--;
                Administrator.products.put(ProductType.TEA, i1);
                amount_1.setText(String.valueOf(i1));
                break;
            }
            case R.id.minus_2: {
                if (i2 > 0)
                    i2--;
                Administrator.products.put(ProductType.COFFEE, i2);
                amount_2.setText(String.valueOf(i2));
                break;
            }
            case R.id.minus_3: {
                if (i3 > 0)
                    i3--;
                Administrator.products.put(ProductType.WATER, i3);
                amount_3.setText(String.valueOf(i3));
                break;
            }
            case R.id.minus_4: {
                if (i4 > 0)
                    i4--;
                Administrator.products.put(ProductType.JUICE, i4);
                amount_4.setText(String.valueOf(i4));
                break;
            }
            case R.id.viewOrder2: {
                startActivity(new Intent(LiquidActivity.this, BagActivity.class));
                break;
            }
        }
    }
}