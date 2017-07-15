package mishas.clientofapp.activities;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mishas.clientofapp.R;
import mishas.clientofapp.logic.DBHelper;

public class SignUpActivity extends AppCompatActivity {

    private EditText loginTxt;
    private EditText passwordTxt;
    private EditText confirmPasswordTxt;
    private EditText nameTxt;
    private EditText surnameTxt;
    private EditText emailTxt;
    private Button signUp;

    private DBHelper dbHelper;

    private void init() {
        loginTxt = (EditText) findViewById(R.id.loginTxtUp);
        passwordTxt = (EditText) findViewById(R.id.passwordTxtUp);
        confirmPasswordTxt = (EditText) findViewById(R.id.confirmPasswordTxtUp);
        nameTxt = (EditText) findViewById(R.id.nameTxtUp);
        surnameTxt = (EditText) findViewById(R.id.surnameTxtUp);
        emailTxt = (EditText) findViewById(R.id.emailTxtUp);
        signUp = (Button) findViewById(R.id.letsSignUp);
        dbHelper = new DBHelper(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                if (passwordTxt.getText().toString().equals(confirmPasswordTxt.getText().toString())) {
                    contentValues.put(DBHelper.KEY_LOGIN, loginTxt.getText().toString());
                    contentValues.put(DBHelper.KEY_PASSWORD, passwordTxt.getText().toString());
                    contentValues.put(DBHelper.KEY_EMAIL, emailTxt.getText().toString());
                    contentValues.put(DBHelper.KEY_NAME, nameTxt.getText().toString());
                    contentValues.put(DBHelper.KEY_SURNAME, surnameTxt.getText().toString());
                    database.insert(DBHelper.TABLE_CLIENTS, null, contentValues);
                    startActivity(new Intent(SignUpActivity.this, MainScreenActivity.class));
                } else {
                    passwordTxt.setText("");
                    confirmPasswordTxt.setText("");
                    Toast.makeText(getApplicationContext(), "The entered passwords do not match!", Toast.LENGTH_SHORT).show();
                }
                dbHelper.close();
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