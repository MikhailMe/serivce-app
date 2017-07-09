package mishas.clientofapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mishas.clientofapp.R;
import mishas.clientofapp.logic.Administrator;
import mishas.clientofapp.logic.BankCard;
import mishas.clientofapp.logic.User;

public class PaymentActivity extends AppCompatActivity {

    private EditText numberCard;
    private EditText monthCard;
    private EditText yearCard;
    private EditText holderCard;
    private EditText ccvCard;

    private Button pay;
    private User _user;
    private BankCard card;

    private void init() {
        numberCard = (EditText) findViewById(R.id._numberOfCard);
        monthCard = (EditText) findViewById(R.id._monthOfCard);
        yearCard = (EditText) findViewById(R.id._yearOfCard);
        holderCard = (EditText) findViewById(R.id._holderCard);
        ccvCard = (EditText) findViewById(R.id._ccvTxt);

        pay = (Button) findViewById(R.id.pay);
        _user = (User) getIntent().getSerializableExtra("user");
        card = Administrator.cards.get(0);
    }

    /*private void ifHasCard() {
        if (_user.HasCard()){
            numberCard.setText(String.valueOf(card.getNumber()));
            monthCard.setText(String.valueOf(card.getMonth()));
            yearCard.setText(String.valueOf(card.getYear()));
            holderCard.setText(String.valueOf(card.getName()));
            ccvCard.setText(String.valueOf(card.getCcv()));
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        init();
        //ifHasCard();

        pay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Your order is successfully paid!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
