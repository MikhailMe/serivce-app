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
    private String currentId = "";

    private void init() {
        buttons = new Button[5];
        buttons[0] = (Button) findViewById(R.id.button_1);
        buttons[1] = (Button) findViewById(R.id.button_2);
        buttons[2] = (Button) findViewById(R.id.button_3);
        buttons[3] = (Button) findViewById(R.id.button_4);
        buttons[4] = (Button) findViewById(R.id.button_5);

        okey = (Button) findViewById(R.id.okey);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_my_order);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#168de2")));
        this.setTitle("Мой заказ");
        init();
        final int lastPush = getIntent().getIntExtra("numberOfClick", 10);
        TextView tv = (TextView) findViewById(R.id.summary);
        currentId = getIntent().getStringExtra("id");
        if (currentId == null) currentId = "";
        this.setTitle("Мой заказ №" + currentId);

       // tv.setText("Ваш заказ №" + currentId);
        if (lastPush != 10) buttons[lastPush].setBackgroundResource(R.drawable.check);
        ListView currentOrder = (ListView) findViewById(R.id.currentOrder);
        if (android.os.Build.VERSION.SDK_INT < 23) {
            File sdcard = Environment.getExternalStorageDirectory();
            Log.d("PlACE", sdcard.toString());
            File file = new File(sdcard, "Order/currentOrder.txt");
            ArrayList<String> text = new ArrayList<>();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {
                    if (!line.contains("Сумма") & !line.contains("-"))
                        text.add(line);
                    else if (line.contains("Сумма") & lastPush != 10) tv.setText(line);
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (lastPush != 10) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, text);
                currentOrder.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        } else {
            if (lastPush != 10) {
                String str = getIntent().getStringExtra("orderString");
                String[] splString = Arrays.copyOf(str.split("\n"), str.split("\n").length - 2);
                Log.d("yoi", Arrays.toString(splString));
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, splString);
                currentOrder.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                String[] splitted = str.split("\n");
                tv.setText(splitted[splitted.length - 1]);
            }
        }
        okey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyOrderActivity.this, MainScreenActivity.class);
                intent.putExtra("id", getIntent().getStringExtra("id"));
                intent.putExtra("numberOfClick", lastPush);
                intent.putExtra("orderString", getIntent().getStringExtra("orderString"));
                startActivity(intent);
            }
        });
    }
}
