package mishas.clientofapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;

import mishas.clientofapp.R;
import mishas.clientofapp.logic.Administrator;
import mishas.clientofapp.logic.ProductType;

public class FoodActivity extends AppCompatActivity {

    TextView tv;
    ListView list;
    CustomList adapter;
    String[] currentOrder = {"0", "0", "0", "0", "0", "0", "0", "0"};
    ProductType[] type =
            {ProductType.HOT_DOG, ProductType.HOT_CORN, ProductType.HAMBURGER, ProductType.CHIPS,
                    ProductType.TEA, ProductType.COFFEE, ProductType.WATER, ProductType.JUICE};
    String[] web = {
            "Хот-дог - 100 руб",
            "Кукуруза - 50 руб",
            "Гамбургер - 150 руб",
            "Чипсы - 100 руб"
    };
    Integer[] imageId = {
            R.mipmap.hotdog,
            R.mipmap.hotcorn,
            R.mipmap.burger,
            R.mipmap.chips,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        this.setTitle("Еда");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#168de2")));
        tv = (TextView) findViewById(R.id.text_rubles);
        switch (getIntent().getStringExtra("from")) {
            case "liquid":
                for(int i = 0; i < currentOrder.length; i++)
                    currentOrder[i] = getIntent().getStringArrayExtra("order")[i];
                tv.setText(getIntent().getStringExtra("money"));
                break;
            case "main":
                break;
        }

        adapter = new CustomList(this, web, imageId, tv,  Arrays.copyOf(currentOrder, 4));
        list = (ListView)findViewById(R.id.list_view);
        list.setAdapter(adapter);
        ImageView iv = (ImageView) findViewById(R.id.cart_image);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FoodActivity.this, BagActivity.class));
                System.arraycopy(adapter.getCurrentOrder(), 0, currentOrder, 0, adapter.getCurrentOrder().length);
                Administrator.products.clear();
                for (int i = 0; i < currentOrder.length; i++)
                    if (!currentOrder[i].equals("0"))
                        Administrator.products.put(type[i], Integer.parseInt(currentOrder[i]));
                startActivity(new Intent(FoodActivity.this, BagActivity.class));
            }
        });
        ImageView next = (ImageView) findViewById(R.id.next_image);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodActivity.this, LiquidActivity.class);
                intent.putExtra("money", tv.getText().toString());
                System.arraycopy(adapter.getCurrentOrder(), 0, currentOrder, 0, adapter.getCurrentOrder().length);
                intent.putExtra("order", currentOrder);
                startActivity(intent);
            }
        });
    }
}