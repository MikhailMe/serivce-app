package mishas.clientofapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import mishas.clientofapp.R;

public class MyOrderActivity extends AppCompatActivity {

    private Button[] buttons;
    private Button okey;

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
        this.setTitle("Мой заказ");
        init();
        int lastPush = getIntent().getIntExtra("numberOfClick",0);
        buttons[lastPush].setBackgroundResource(R.drawable.check);
        okey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyOrderActivity.this, MainScreenActivity.class));
            }
        });
    }
}
