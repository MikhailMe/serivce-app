package mishas.clientofapp.activities;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import mishas.clientofapp.R;

public class SignUpPart1Activity extends AppCompatActivity {

    private EditText loginTxt;
    private EditText emailTxt;
    private EditText passwordTxt;
    private EditText confirmPasswordTxt;

    private Button signUp1;

    private void init() {
        loginTxt = (EditText) findViewById(R.id.loginTxtUp);
        emailTxt = (EditText) findViewById(R.id.emailTxtUp);
        passwordTxt = (EditText) findViewById(R.id.passwordTxtUp);
        confirmPasswordTxt = (EditText) findViewById(R.id.confirmPasswordTxtUp);
        signUp1 = (Button) findViewById(R.id.letsSignUp1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_part1);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#168de2")));
        setTitle("Регистрация");
        init();
        signUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordTxt.getText().toString().equals(confirmPasswordTxt.getText().toString())) {
                    Intent intent = new Intent(SignUpPart1Activity.this, SignUpPart3Activity.class);
                    intent.putExtra("id", new Random().nextLong());
                    intent.putExtra("login", loginTxt.getText().toString());
                    intent.putExtra("password", passwordTxt.getText().toString());
                    intent.putExtra("email", emailTxt.getText().toString());
                    startActivity(intent);
                } else {
                    passwordTxt.setText("");
                    confirmPasswordTxt.setText("");
                    Toast.makeText(getApplicationContext(), "The entered passwords do not match!", Toast.LENGTH_SHORT).show();
                }
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