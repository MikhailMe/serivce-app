package mishas.clientofapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mishas.clientofapp.R;

public class ChooseShopActivity extends AppCompatActivity {


    private LinearLayout send, back;
    String selectedShop;
    String mySector = "201";
    String myRow = "1";
    String myPlace = "1";
    ListView listView;
    ArrayList<String> sectorsList = new ArrayList<>();
    ArrayList<String> placeList = new ArrayList<>();
    ArrayList<String> rowList = new ArrayList<>();
    MyShopsCustomList myShopsCustomList;
    ArrayList<String> metersToShop = new ArrayList<>();
    ArrayList<Integer> imageTime = new ArrayList<>();
    private void init() {
        send = (LinearLayout) findViewById(R.id.sendToServingApp);
        back = (LinearLayout) findViewById(R.id.back_to_card);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        setTitle("Выберите магазин");
        init();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#168de2")));
        Spinner sectorSpinner = (Spinner) findViewById(R.id.sectorSpinner);

        for(int i = 201; i <= 216; i++) {
            sectorsList.add(i + "");
        }
        for(int i = 401; i <= 416; i++) {
            sectorsList.add(i + "");
        }
        ArrayAdapter<String> sectorListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sectorsList);
        sectorSpinner.setAdapter(sectorListAdapter);
        Spinner placeSpinner = (Spinner) findViewById(R.id.placeSpinner);

        for(int i = 1; i <= 14; i++) {
            placeList.add(i + "");
        }
        for(int i = 101; i <= 114; i++) {
            placeList.add(i + "");
        }
        ArrayAdapter<String> placeListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, placeList);
        placeSpinner.setAdapter(placeListAdapter);
        Spinner rowSpinner = (Spinner) findViewById(R.id.rowSpinner);

        for(int i = 1; i <= 20; i++) {
            rowList.add(i + "");
        }
        ArrayAdapter<String> rowListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, rowList);
        rowSpinner.setAdapter(rowListAdapter);
        listView = (ListView) findViewById(R.id.nearestShopList);
        myShopsCustomList = new MyShopsCustomList(this, metersToShop, imageTime);
        listFunc();
        listView.setAdapter(myShopsCustomList);
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedShop= metersToShop.get(position);
                Log.d("AAAAAAA", "AAAAAAAAAAAAAAA");
                for(int i = 0; i < metersToShop.size(); i++) {
                    listView.getChildAt(i).setBackgroundColor(Color.WHITE);
                    TextView tv = (TextView)listView.getChildAt(i).findViewById(R.id.item_name_of_order);
                    tv.setTextColor(Color.GRAY);
                }
                TextView tv = (TextView)view.findViewById(R.id.item_name_of_order);
                tv.setTextColor(Color.WHITE);
                view.setBackgroundColor(Color.parseColor("#168de2"));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sectorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mySector = sectorsList.get(position);
                listFunc();
                myShopsCustomList.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        rowSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                myRow = rowList.get(position);
                listFunc();
                myShopsCustomList.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        placeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                myPlace = placeList.get(position);
                listFunc();
                myShopsCustomList.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseShopActivity.this, BagActivity.class));
            }
        });

    }

    void listFunc() {
        int sec = Integer.parseInt(mySector);
        int place = Integer.parseInt(myPlace);
        metersToShop.clear();
        imageTime.clear();

        if ((place > 3 && place < 11) || (place > 103 && place < 111)) {
            metersToShop.add("Магазин сектора: " + sec + " - 20метров");
            metersToShop.add("Магазин сектора: " + sec + " - 20метров");
            metersToShop.add("Магазин сектора: " + ((sec == 201) ? 216 : sec - 1) + " - 30метров");
            metersToShop.add("Магазин сектора: " + ((sec == 216) ? 201 : sec + 1) + " - 30метров");
            imageTime.add(R.drawable.yellow_icon);
            imageTime.add(R.drawable.yellow_icon);
            imageTime.add(R.drawable.red_icon);
            imageTime.add(R.drawable.red_icon);
        } else {
            if ((place >= 11 && place <= 14) || (place >= 111 && place <=114)) {
                metersToShop.add("Магазин сектора: " + sec + " - 10метров");
                metersToShop.add("Магазин сектора: " + ((sec == 216) ? 201 : sec + 1) + " - 20метров");
                metersToShop.add("Магазин сектора: " + sec + " - 30метров");
                imageTime.add(R.drawable.green_icon);
                imageTime.add(R.drawable.yellow_icon);
                imageTime.add(R.drawable.red_icon);
            } else {
                if (place <= 3 || (place >= 101 && place <= 103)) {
                    metersToShop.add("Магазин сектора: " + sec + " - 10метров");
                    metersToShop.add("Магазин сектора: " + ((sec == 201) ? 216 : sec - 1) + " - 20метров");
                    metersToShop.add("Магазин сектора: " + sec + " - 30метров");
                    imageTime.add(R.drawable.green_icon);
                    imageTime.add(R.drawable.yellow_icon);
                    imageTime.add(R.drawable.red_icon);
                }
            }

        }
    }
}