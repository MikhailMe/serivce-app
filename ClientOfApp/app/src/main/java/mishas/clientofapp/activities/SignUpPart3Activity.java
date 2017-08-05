package mishas.clientofapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

    private User _user;
    private long id = 5;
    private Administrator admin;

    private void init() {
        numberCard = (EditText) findViewById(R.id.numberOfCard);
        monthCard = (EditText) findViewById(R.id.monthOfCard);
        yearCard = (EditText) findViewById(R.id.yearOfCard);
        holderCard = (EditText) findViewById(R.id.holderCard);
        ccvCard = (EditText) findViewById(R.id.ccvTxt);
        addCard = (Button) findViewById(R.id.addCard);
        skip = (Button) findViewById(R.id.skip);
    }

    private void createUser() {
        _user = new User(getIntent().getLongExtra("id", id),
                getIntent().getStringExtra("login"),
                getIntent().getStringExtra("password"),
                getIntent().getStringExtra("email"),
                "default",
                "default",
                18,
                "default", false);
        id++;
    }

    @Override
    protected void onResume() {
        super.onResume();
        admin = null;
        System.gc();
        admin = new Administrator();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_part3);
        setTitle("Карта");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#168de2")));
        init();
        createUser();
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
                String number = numberCard.getText().toString();
                int month = Integer.parseInt(monthCard.getText().toString());
                int year = Integer.parseInt(yearCard.getText().toString());
                String holder = holderCard.getText().toString();
                int ccv = Integer.parseInt(ccvCard.getText().toString());
                // добавляем админу карту (бд)
                Administrator.cards.add(new BankCard(_user.getId(), number, month, year, holder, ccv));

                // добавили карту - записали что у юзверя есть карта
                _user.setHasCard(true);

                // добавляем админу юзверя (бд)
                Administrator.currentUser = _user;
                Administrator.users.add(_user);
                //startActivity(new Intent(SignUpPart3Activity.this, SignInActivity.class));
                Intent intent = new Intent(SignUpPart3Activity.this, SignInActivity.class);
                intent.putExtra("id", _user.getId());
                intent.putExtra("login", _user.getLogin());
                intent.putExtra("password", _user.getPassword());
                intent.putExtra("email", _user.getEmail());
                intent.putExtra("name", _user.getName());
                intent.putExtra("surname", _user.getSurname());
                intent.putExtra("age", _user.getAge());
                intent.putExtra("telephone", _user.getTelephone());
                intent.putExtra("number", number);
                intent.putExtra("month", month);
                intent.putExtra("year", year);
                intent.putExtra("holder", holder);
                intent.putExtra("ccv", ccv);
                startActivity(intent);
                break;
            }
            case R.id.skip:
                Administrator.currentUser = _user;
                Administrator.users.add(_user);
                Log.d("userdata", _user.getLogin() + "" + _user.getPassword());
                //startActivity(new Intent(SignUpPart3Activity.this, SignInActivity.class));
                Intent intent = new Intent(SignUpPart3Activity.this, SignInActivity.class);
                intent.putExtra("id", _user.getId());
                intent.putExtra("login", _user.getLogin());
                intent.putExtra("password", _user.getPassword());
                intent.putExtra("email", _user.getEmail());
                intent.putExtra("name", _user.getName());
                intent.putExtra("surname", _user.getSurname());
                intent.putExtra("age", _user.getAge());
                intent.putExtra("telephone", _user.getTelephone());
                intent.putExtra("number", "");
                startActivity(intent);
                break;
        }
    }
}