package mishas.clientofapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mishas.clientofapp.R;
import mishas.clientofapp.logic.Administrator;
import mishas.clientofapp.logic.BankCard;
import mishas.clientofapp.logic.User;

public class SignInActivity extends AppCompatActivity {

    private EditText loginTxt;
    private EditText passwordTxt;
    private Button signIn;
    private Button signUp;
    private TextView forgotPassword;
    //private User _user;
    private Administrator admin;

    //private DBHelper dbHelper;

    private void init() {
        loginTxt = (EditText) findViewById(R.id.loginTxtIn);
        passwordTxt = (EditText) findViewById(R.id.passwordTxtIn);
        signIn = (Button) findViewById(R.id.letsSignIn);
        signUp = (Button) findViewById(R.id.signUp);
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        //dbHelper = new DBHelper(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#168de2")));
        setTitle("App");
        init();
        //createUser();
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*SQLiteDatabase database = dbHelper.getReadableDatabase();
                String[] projection = {DBHelper.KEY_ID, DBHelper.KEY_LOGIN, DBHelper.KEY_PASSWORD,
                                       DBHelper.KEY_EMAIL, DBHelper.KEY_NAME, DBHelper.KEY_SURNAME,
                                       DBHelper.KEY_AGE};
                Cursor cursor = database.query(DBHelper.TABLE_CLIENTS, projection, null, null, null, null, null);*/
                boolean isRegistered = false;
                String login = loginTxt.getText().toString();
                String password = passwordTxt.getText().toString();
                Log.d("login", login);
                Log.d("pass", password);
                for (User user : Administrator.users) {
                    Log.d("usersdata", user.getLogin() + " " + user.getPassword());
                }
                for (User user : Administrator.users) {
                    if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                        Administrator.currentUser = user;
                        isRegistered = true;
                        break;
                    }
                }

                /*int loginIndex = cursor.getColumnIndex(DBHelper.KEY_LOGIN);
                int passwordIndex = cursor.getColumnIndex(DBHelper.KEY_PASSWORD);
                cursor.moveToFirst();
                while (cursor.moveToNext()) {
                    if (cursor.getString(loginIndex).equals(login) &&
                            cursor.getString(passwordIndex).equals(password)) {
                        isRegistered = true;
                        break;
                    }
                }
                cursor.close();*/
                if (isRegistered) {
                    startActivity(new Intent(SignInActivity.this, MainScreenActivity.class));
                } else {
                    loginTxt.setText("");
                    passwordTxt.setText("");
                    Toast.makeText(getApplicationContext(),
                            "Login/Password does not correct. Please try again",
                            Toast.LENGTH_SHORT).show();
                }
                //dbHelper.close();

                // достать из бд логин/пароль и проверить есть ли такой пользователь
                // если есть, то на MainScreenActivity
                // если нет, то сказать, что есть ошибка и надо попробовать ещё раз
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, RecoveryPasswordActivity.class));
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignUpPart1Activity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        admin = null;
        System.gc();
        admin = new Administrator();
    }
//    private void createUser() {
//        _user = new User(getIntent().getLongExtra("id", 0L),
//                getIntent().getStringExtra("login"),
//                getIntent().getStringExtra("password"),
//                getIntent().getStringExtra("email"),
//                getIntent().getStringExtra("name"),
//                getIntent().getStringExtra("surname"),
//                getIntent().getIntExtra("age", 0),
//                getIntent().getStringExtra("telephone"), true);
//        Administrator.users.add(_user);
//        if (!getIntent().getStringExtra("number").equals("")) {
//            Administrator.cards.add(new BankCard(_user.getId(),
//                    getIntent().getStringExtra("number"),
//                    getIntent().getIntExtra("month", 0),
//                    getIntent().getIntExtra("year", 0),
//                    getIntent().getStringExtra("holder"),
//                    getIntent().getIntExtra("ccv", 0)));
//            _user.setHasCard(true);
//        }
//
//    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}