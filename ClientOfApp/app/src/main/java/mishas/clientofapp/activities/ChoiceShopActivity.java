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

public class ChoiceShopActivity extends AppCompatActivity {

    private Button[] buttons;
    private LinearLayout send, back;
    private Boolean[] button_click = {false, false, false, false, false};
    private ArrayList<Boolean> button_clicked = new ArrayList<>(Arrays.asList(button_click));
    private int lastPush;

    private void init() {
        send = (LinearLayout) findViewById(R.id.sendToServingApp);
        back = (LinearLayout) findViewById(R.id.back_to_card);

        buttons = new Button[5];
        buttons[0] = (Button) findViewById(R.id.button1);
        buttons[1] = (Button) findViewById(R.id.button2);
        buttons[2] = (Button) findViewById(R.id.button3);
        buttons[3] = (Button) findViewById(R.id.button4);
        buttons[4] = (Button) findViewById(R.id.button5);

        for (int k = 0; k < 5; k++)
            buttons[k].setOnClickListener(myClicker(k));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_place);
        setTitle("Выберите магазин");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#168de2")));
        init();
        send.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int counter = 0;
                for (boolean b : button_clicked)
                    if (b) {
                        counter++;
                        Intent intent = new Intent(ChoiceShopActivity.this, PaymentActivity.class);
                        intent.putExtra("numberOfClick", lastPush);
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
                startActivity(new Intent(ChoiceShopActivity.this, BagActivity.class));
            }
        });
    }

    private OnClickListener myClicker(final int i) {
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                buttons[lastPush].setBackgroundResource(R.mipmap.circle);
                button_clicked.set(lastPush, false);
                int counter = 0;
                for (boolean b : button_clicked) {
                    if (!b) counter++;
                }
                if (counter == 5) {
                    button_clicked.set(i, true);
                    v.setBackgroundResource(R.drawable.check);
                    lastPush = i;
                }
            }
        };
    }
}