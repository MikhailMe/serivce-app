package mishas.clientofapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import mishas.clientofapp.R;
import mishas.clientofapp.logic.Administrator;
import mishas.clientofapp.logic.Client;

public class ChoiceShopActivity extends AppCompatActivity {

    private Button button, button2, button3, button4, button5;
    private LinearLayout send, back;
    private Boolean[] button_click = {false, false, false, false, false};
    private ArrayList<Boolean> button_clicked = new ArrayList<>(Arrays.asList(button_click));

    private void init() {
        send = (LinearLayout) findViewById(R.id.sendToServingApp);
        back = (LinearLayout) findViewById(R.id.back_to_card);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_place);
        setTitle("Выберите магазин");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#168de2")));
        init();


        button.setOnClickListener(new OnClickListener() { // Then you should add add click listener for your button.
            @Override
            public void onClick(View v) {
                int counter = 0;
                for (boolean b : button_clicked) {
                    if (!b) counter++;
                }
                if (counter == 5) {
                    button_clicked.set(0, true);
                    v.setBackgroundResource(R.drawable.check);
                }
            }
        });
        button2.setOnClickListener(new OnClickListener() { // Then you should add add click listener for your button.
            @Override
            public void onClick(View v) {
                int counter = 0;
                for (boolean b : button_clicked) {
                    if (!b) counter++;
                }
                if (counter == 5) {
                    button_clicked.set(1, true);
                    v.setBackgroundResource(R.drawable.check);
                }
            }
        });
        button3.setOnClickListener(new OnClickListener() { // Then you should add add click listener for your button.
            @Override
            public void onClick(View v) {
                int counter = 0;
                for (boolean b : button_clicked) {
                    if (!b) counter++;
                }
                if (counter == 5) {
                    button_clicked.set(2, true);
                    v.setBackgroundResource(R.drawable.check);
                }
            }
        });
        button4.setOnClickListener(new OnClickListener() { // Then you should add add click listener for your button.
            @Override
            public void onClick(View v) {
                int counter = 0;
                for (boolean b : button_clicked) {
                    if (!b) counter++;
                }
                if (counter == 5) {
                    button_clicked.set(3, true);
                    v.setBackgroundResource(R.drawable.check);
                }
            }
        });
        button5.setOnClickListener(new OnClickListener() { // Then you should add add click listener for your button.
            @Override
            public void onClick(View v) {
                int counter = 0;
                for (boolean b : button_clicked) {
                    if (!b) counter++;
                }
                if (counter == 5) {
                    button_clicked.set(4, true);
                    v.setBackgroundResource(R.drawable.check);
                }
            }
        });
        send.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int counter = 0;
                for (boolean b : button_clicked)
                    if (b) {
                        Client client = new Client("192.168.137.131", 11100);
                        client.sendRequest(Administrator.currentOrder.makeSendString());
                        Toast textToast = Toast.makeText(ChoiceShopActivity.this,
                                "Заказ отправлен, информацию о заказе Вы сможете найти в пункте меню \"Мой заказ\"",
                                Toast.LENGTH_LONG);
                        textToast.setGravity(Gravity.CENTER, 0, 0);
                        textToast.show();
                        counter++;
                        Intent intent = new Intent(ChoiceShopActivity.this, MainScreenActivity.class);
                        intent.putExtra("from", "shop");
                        startActivity(intent);
                        break;
                    }
                if (counter == 0) {
                    Toast textToast = Toast.makeText(ChoiceShopActivity.this,
                            "Пожалуйста, выберите магазин",
                            Toast.LENGTH_LONG);
                    textToast.setGravity(Gravity.CENTER, 0, 0);
                    textToast.show();
                }
            }
        });

        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoiceShopActivity.this, PaymentActivity.class));
            }
        });
    }


    // FIXME
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainScreenActivity.class));
    }
}
