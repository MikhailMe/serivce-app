package mishas.clientofapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Enumeration;

import mishas.clientofapp.R;
import mishas.clientofapp.logic.Administrator;
import mishas.clientofapp.logic.Product;
import mishas.clientofapp.logic.ProductType;


public class LiquidActivity extends AppCompatActivity {

    TextView tv;
    ListView list;
    CustomList adapter;
    String[] currentOrder = {"0", "0", "0", "0", "0", "0", "0", "0"};
    String[] web = {
            "Чай - 100 руб",
            "Кофе - 150 руб",
            "Вода - 100 руб",
            "Сок - 120 руб"
    };
    ProductType[] type =
            {ProductType.HOT_DOG, ProductType.HOT_CORN, ProductType.HAMBURGER, ProductType.CHIPS,
            ProductType.TEA, ProductType.COFFEE, ProductType.WATER, ProductType.JUICE};
    Integer[] imageId = {
            R.mipmap.tea,
            R.mipmap.coffee,
            R.mipmap.water,
            R.mipmap.juice,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liquid);
        this.setTitle("Напитки");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#168de2")));
        tv = (TextView) findViewById(R.id.text_rubles_liquid);
        tv.setText(getIntent().getStringExtra("money"));
        for(int i = 0; i < getIntent().getStringArrayExtra("order").length; i++)
            currentOrder[i] = getIntent().getStringArrayExtra("order")[i];
        adapter = new CustomList(this, web, imageId, tv, Arrays.copyOfRange(currentOrder, 4, currentOrder.length));
        list = (ListView) findViewById(R.id.list_view);
        list.setAdapter(adapter);
        ImageView iv = (ImageView) findViewById(R.id.cart_image_liquid);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LiquidActivity.this, BagActivity.class));
                for (int i = 0; i < adapter.getCurrentOrder().length; i++) {
                    currentOrder[i + 4] = adapter.getCurrentOrder()[i];
                }
                Administrator.products.clear();
                for (int i = 0; i < currentOrder.length; i++)
                    if (!currentOrder[i].equals("0"))
                        Administrator.products.put(type[i], Integer.parseInt(currentOrder[i]));
                startActivity(new Intent(LiquidActivity.this, BagActivity.class));
            }
        });
        ImageView next = (ImageView) findViewById(R.id.next_image_liquid);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < adapter.getCurrentOrder().length; i++) {
                    currentOrder[i + 4] = adapter.getCurrentOrder()[i];
                }
                Administrator.products.clear();
                for (int i = 0; i < currentOrder.length; i++)
                    if (!currentOrder[i].equals("0"))
                        Administrator.products.put(type[i], Integer.parseInt(currentOrder[i]));
                startActivity(new Intent(LiquidActivity.this, BagActivity.class));
            }
        });
        ImageView back = (ImageView) findViewById(R.id.icon_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LiquidActivity.this, FoodActivity.class);
                intent.putExtra("from", "liquid");
                intent.putExtra("money", tv.getText().toString());
                for (int i = 0; i < adapter.getCurrentOrder().length; i++) {
                    currentOrder[i + 4] = adapter.getCurrentOrder()[i];
                }
                intent.putExtra("order", currentOrder);
                startActivity(intent);
            }
        });

    }
}
