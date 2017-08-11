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
import android.widget.Toast;

import mishas.clientofapp.R;
import mishas.clientofapp.logic.Administrator;
import mishas.clientofapp.logic.BankCard;
import mishas.clientofapp.logic.User;

public class SignUpPart2Activity extends AppCompatActivity implements OnClickListener {

    private EditText numberCard;
    private EditText monthCard;
    private EditText yearCard;
    private EditText holderCard;
    private EditText ccvCard;

    private Button addCard;
    private Button skip;

    private User _user;

    private void init() {
        numberCard = (EditText) findViewById(R.id.numberOfCard);
        monthCard = (EditText) findViewById(R.id.monthOfCard);
        yearCard = (EditText) findViewById(R.id.yearOfCard);
        holderCard = (EditText) findViewById(R.id.holderCard);
        ccvCard = (EditText) findViewById(R.id.ccvTxt);
        addCard = (Button) findViewById(R.id.addCard);
        skip = (Button) findViewById(R.id.skip);
    }

    private void createUser(boolean hasCard) {
        _user = new User(Administrator.currentUserId,
                getIntent().getStringExtra("login"),
                getIntent().getStringExtra("password"),
                getIntent().getStringExtra("email"),
                hasCard);
        Log.d("INFO_ABOUT_USER", "id: " + _user.getId() + " | login: " + _user.getLogin() + " | password: " + _user.getPassword());
        Administrator.currentUserId++;
    }

    private void setUser() {
        // добавляем админу юзверя (бд)
        Administrator.users.add(_user);

        // установили текущего юзверя
        Administrator.currentUser = _user;

        // выводим данные о юзвере в лог
        Log.d("USER_DATA:   ",
                "id: " + _user.getId() + " |   login: " + _user.getLogin() + " |   password: " + _user.getPassword());

        // переходим на стартовую активити
        startActivity(new Intent(SignUpPart2Activity.this, StartingActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_part2);
        setTitle("Карта");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#168de2")));
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
                if (numberCard.getText().length() == 0 ||
                        monthCard.getText().length() == 0 ||
                        yearCard.getText().length() == 0 ||
                        holderCard.getText().length() == 0 ||
                        ccvCard.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Пожалуйста, заполните все поля!", Toast.LENGTH_SHORT).show();
                } else {
                    // создаем юзверя, true - карта есть
                    createUser(true);
                    // добавляем карту в epic database
                    Administrator.cards.add(new BankCard(_user.getId(),
                            numberCard.getText().toString(),
                            Integer.parseInt(monthCard.getText().toString()),
                            Integer.parseInt(yearCard.getText().toString()),
                            holderCard.getText().toString(),
                            Integer.parseInt(ccvCard.getText().toString())));
                    // кидаем юзверя админу
                    setUser();
                }
                break;
            }
            case R.id.skip:

                // макет карты, чтобы не сбивалась индексация
                Administrator.cards.add(new BankCard(-1, "", -1, -1, "", -1));

                // создаем юзверя, false - карта нет
                createUser(false);
                // кидаем юзверя админу
                setUser();
                break;
        }
    }
}