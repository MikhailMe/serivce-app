package mishas.clientofapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import mishas.clientofapp.R;



public class ShopNavigationActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        this.setContentView(R.layout.activity_shop_navigation);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#168de2")));
        this.setTitle("Маршрут");
        ListView lv = (ListView) findViewById(R.id.listOfNavigation);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1. Спуститесь к выходу справа от вас");
        strings.add("2. Выйдите и поверните налево");
        strings.add("3. Вы у цели");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        LinearLayout toHome = (LinearLayout) findViewById(R.id.toHome);
        LinearLayout toOrder = (LinearLayout) findViewById(R.id.back_to_my_order);
        toHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopNavigationActivity.this, MainScreenActivity.class);
                intent.putExtra("id", getIntent().getStringExtra("id"));
                intent.putExtra("place", getIntent().getStringExtra("place"));
                intent.putExtra("orderString", getIntent().getStringExtra("orderString"));
            }
        });
        toOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopNavigationActivity.this, MyOrderActivity.class);
                intent.putExtra("id", getIntent().getStringExtra("id"));
                intent.putExtra("place", getIntent().getStringExtra("place"));
                intent.putExtra("orderString", getIntent().getStringExtra("orderString"));
            }
        });
    }
}
