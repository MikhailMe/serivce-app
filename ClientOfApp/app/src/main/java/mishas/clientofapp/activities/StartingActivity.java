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
import mishas.clientofapp.logic.User;

public class StartingActivity extends AppCompatActivity {

    private EditText loginTxt;
    private EditText passwordTxt;
    private Button signIn;
    private Button signUp;
    private TextView forgotPassword;
    private Administrator admin;

    private void init() {
        admin = Administrator.getAdmin();

        loginTxt = (EditText) findViewById(R.id.loginTxtIn);
        passwordTxt = (EditText) findViewById(R.id.passwordTxtIn);
        signIn = (Button) findViewById(R.id.letsSignIn);
        signUp = (Button) findViewById(R.id.signUp);
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#168de2")));
        setTitle("");

        init();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isRegistered = false;
                String login = loginTxt.getText().toString();
                String password = passwordTxt.getText().toString();
                Log.d("login", login);
                Log.d("pass", password);
                for (User user : Administrator.users) {
                    if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                        Administrator.currentUser = user;
                        isRegistered = true;
                        break;
                    }
                }
                if (isRegistered) {
                    startActivity(new Intent(StartingActivity.this, MainScreenActivity.class));
                } else {
                    loginTxt.setText("");
                    passwordTxt.setText("");
                    Toast.makeText(getApplicationContext(),
                            "Логин или пароль введены неверно! Пожалуйста, попробуйте снова!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartingActivity.this, RecoveryPasswordActivity.class));
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartingActivity.this, SignUpPart1Activity.class));
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}