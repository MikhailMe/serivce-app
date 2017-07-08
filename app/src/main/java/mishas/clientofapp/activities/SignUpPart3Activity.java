package mishas.clientofapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import mishas.clientofapp.R;
import mishas.clientofapp.logic.Administrator;
import mishas.clientofapp.logic.BankCard;
import mishas.clientofapp.logic.User;

public class SignUpPart3Activity extends AppCompatActivity implements OnClickListener {

    private EditText numberCard;
    private EditText monthCard;
    private EditText yearCard;
    private EditText holderCard;
    private EditText ccvCard;

    private Button addCard;
    private Button skip;

    private long id;

    private void init() {
        numberCard = (EditText) findViewById(R.id.numberOfCard);
        monthCard = (EditText) findViewById(R.id.monthOfCard);
        yearCard = (EditText) findViewById(R.id.yearOfCard);
        holderCard = (EditText) findViewById(R.id.holderCard);
        ccvCard = (EditText) findViewById(R.id.ccvTxt);
        addCard = (Button) findViewById(R.id.addCard);
        skip = (Button) findViewById(R.id.skip);
        id = 5;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_part3);
        init();
        addCard.setOnClickListener(this);
        skip.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addCard: {

                User _user = new User(getIntent().getLongExtra("id", id),
                        getIntent().getStringExtra("login"),
                        getIntent().getStringExtra("password"),
                        getIntent().getStringExtra("email"),
                        getIntent().getStringExtra("name"),
                        getIntent().getStringExtra("surname"),
                        getIntent().getIntExtra("age", 0),
                        getIntent().getStringExtra("telephone"), true);
                id++;
                // добавляем админу юзверя (бд)
                Administrator.users.add(_user);

                String number = numberCard.getText().toString();
                int month = Integer.parseInt(monthCard.getText().toString());
                int year = Integer.parseInt(yearCard.getText().toString());
                String holder = holderCard.getText().toString();
                int ccv = Integer.parseInt(ccvCard.getText().toString());
                // добавляем админу карту (бд)
                Administrator.cards.add(new BankCard(_user.getId(), number, month, year, holder, ccv));
                Administrator.users.get(Integer.parseInt(_user.getId().toString())).setHasCard(true);

                startActivity(new Intent(SignUpPart3Activity.this, SignInActivity.class));
                break;
            }
            case R.id.skip:
                startActivity(new Intent(SignUpPart3Activity.this, SignInActivity.class));
                break;
        }
    }
}