package mishas.clientofapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import mishas.clientofapp.R;
import mishas.clientofapp.logic.Administrator;
import mishas.clientofapp.logic.BankCard;
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
        card = Administrator.cards.get(0);
    }

    private void ifHasCard() {
        if (Administrator.currentUser.HasCard()) {
            numberCard.setText(String.valueOf(card.getNumber()));
            monthCard.setText(String.valueOf(card.getMonth()));
            yearCard.setText(String.valueOf(card.getYear()));
            holderCard.setText(String.valueOf(card.getName()));
            ccvCard.setText(String.valueOf(card.getCcv()));
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
                    Toast.makeText(getApplicationContext(), "Please fill in all the fields of the card!", Toast.LENGTH_SHORT).show();
                else {
                    if (Payment.isPaid()) {
                        startActivity(new Intent(PaymentActivity.this, ChoiceShopActivity.class));
                        Toast.makeText(getApplicationContext(), "Заказ успешно опалчен!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
