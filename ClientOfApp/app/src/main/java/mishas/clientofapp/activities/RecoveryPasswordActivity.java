package mishas.clientofapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mishas.clientofapp.R;
import mishas.clientofapp.logic.Administrator;

public class RecoveryPasswordActivity extends AppCompatActivity {

    private EditText email;
    private Button send;

    private void init() {
        email = (EditText) findViewById(R.id.recoveryTxt);
        send = (Button) findViewById(R.id.recoveryPassword);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_password);
        init();
        send.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // if true we generate new password right away
                if (Administrator.isExist(email.getText().toString())) {
                    startActivity(new Intent(RecoveryPasswordActivity.this, SignInActivity.class));
                    Toast.makeText(getApplicationContext(), "We sent to your email new password", Toast.LENGTH_LONG).show();
                } else {
                    email.setText("");
                    Toast.makeText(getApplicationContext(), "Sorry, but entered email does not exist", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
