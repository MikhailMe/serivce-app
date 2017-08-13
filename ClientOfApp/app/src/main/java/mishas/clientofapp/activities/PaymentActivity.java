package mishas.clientofapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import mishas.clientofapp.R;
import mishas.clientofapp.logic.Administrator;
import mishas.clientofapp.logic.BankCard;
import mishas.clientofapp.logic.Client;
import mishas.clientofapp.logic.Payment;

public class PaymentActivity extends AppCompatActivity {

    private EditText numberCard;
    private EditText monthCard;
    private EditText yearCard;
    private EditText holderCard;
    private EditText ccvCard;

    private LinearLayout pay;
    private BankCard card;

    private void init() {
        numberCard = (EditText) findViewById(R.id._numberOfCard);
        monthCard = (EditText) findViewById(R.id._monthOfCard);
        yearCard = (EditText) findViewById(R.id._yearOfCard);
        holderCard = (EditText) findViewById(R.id._holderCard);
        ccvCard = (EditText) findViewById(R.id._ccvTxt);
        pay = (LinearLayout) findViewById(R.id.pay);
    }

    private void ifHasCard() {
        if (Administrator.currentUser.hasCard()) {
            card = Administrator.cards.get(Integer.parseInt(Administrator.currentUser.getId().toString()));
            numberCard.setText(String.valueOf(card.getNumber()));
            monthCard.setText(String.valueOf(card.getMonth()));
            yearCard.setText(String.valueOf(card.getYear()));
            holderCard.setText(String.valueOf(card.getName()));
            ccvCard.setText(String.valueOf(card.getCcv()));
        } else {
            Toast.makeText(getApplicationContext(), "Заполните информацию о карте для оплаты!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#168de2")));
        setTitle("Оплата");
        init();
        ifHasCard();
        pay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberCard.getText().toString().equals("") ||
                        monthCard.getText().toString().equals("") ||
                        yearCard.getText().toString().equals("") ||
                        holderCard.getText().toString().equals("") ||
                        ccvCard.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                else {
                    if (Payment.isPaid()) {
                        Client client = new Client("192.168.0.98", 11100);
                        client.sendRequest(Administrator.currentOrder.makeSendString());
//                        try {
//                            wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        Toast textToast = Toast.makeText(PaymentActivity.this,
                                "Заказ отправлен, информацию о заказе Вы сможете найти в пункте меню \"Мой заказ\"",
                                Toast.LENGTH_LONG);
                        textToast.setGravity(Gravity.CENTER, 0, 0);
                        textToast.show();
                        Intent intent = new Intent(PaymentActivity.this, MainScreenActivity.class);
                        intent.putExtra("from", "pay");
                        intent.putExtra("numberOfClick", getIntent().getIntExtra("numberOfClick", 0));

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.d("idsh", client.getId());
                        intent.putExtra("id", client.getId());

                        startActivity(intent);
                    }
                }
            }
        });
    }
}
