package mishas.clientofapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import mishas.clientofapp.R;
import mishas.clientofapp.logic.Administrator;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvForLogin;

    private void init() {
        tvForLogin = (TextView) findViewById(R.id.tvForLogin);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_profile);
        this.setTitle("Профиль");
        init();
        tvForLogin.setText(Administrator.currentUser.getLogin());
    }
}
