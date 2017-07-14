package mishas.clientofapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import mishas.clientofapp.R;
import mishas.clientofapp.logic.Administrator;
import mishas.clientofapp.logic.BagOfOrders;

public class StartActivity extends AppCompatActivity implements OnClickListener {

    private Button signIn;
    private Button signUp;

    private void init() {
        Administrator.getInstance();
        BagOfOrders.getInstance();
        signIn = (Button) findViewById(R.id.signIn);
        signUp = (Button) findViewById(R.id.signUp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        init();
        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
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
            case R.id.signIn:
                startActivity(new Intent(this, SignInActivity.class));
                break;
            case R.id.signUp:
                startActivity(new Intent(this, SignUpPart1Activity.class));
                break;
            default:
                break;
        }
    }
}