package mishas.clientofapp.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mishas.clientofapp.R;
import mishas.clientofapp.logic.DBHelper;

public class SignInActivity extends AppCompatActivity {

    private EditText loginTxt;
    private EditText passwordTxt;
    private Button signIn;

    private DBHelper dbHelper;

    private void init() {
        loginTxt = (EditText) findViewById(R.id.loginTxtIn);
        passwordTxt = (EditText) findViewById(R.id.passwordTxtIn);
        signIn = (Button) findViewById(R.id.letsSignIn);
        dbHelper = new DBHelper(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase database = dbHelper.getReadableDatabase();
                Cursor cursor = database.query(DBHelper.TABLE_CLIENTS, null, null, null, null, null, null);
                boolean isRegistered = false;
                String login = loginTxt.getText().toString();
                String password = passwordTxt.getText().toString();
                int loginIndex = cursor.getColumnIndex(DBHelper.KEY_LOGIN);
                int passwordIndex = cursor.getColumnIndex(DBHelper.KEY_PASSWORD);
                while (cursor.moveToNext()) {
                    if (cursor.getString(loginIndex).equals(login) &&
                            cursor.getString(passwordIndex).equals(password)) {
                        isRegistered = true;
                        break;
                    }
                }
                cursor.close();
                if (isRegistered) {
                    startActivity(new Intent(SignInActivity.this, MainScreenActivity.class));
                } else {
                    loginTxt.setText("");
                    passwordTxt.setText("");
                    Toast.makeText(getApplicationContext(),
                            "Login/Password does not correct. Please try again",
                            Toast.LENGTH_SHORT).show();
                }
                dbHelper.close();

                // достать из бд логин/пароль и проверить есть ли такой пользователь
                // если есть, то на MainScreenActivity
                // если нет, то сказать, что есть ошибка и надо попробовать ещё раз


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