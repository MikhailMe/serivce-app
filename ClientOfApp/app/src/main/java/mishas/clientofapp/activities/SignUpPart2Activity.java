package mishas.clientofapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import mishas.clientofapp.R;

public class SignUpPart2Activity extends AppCompatActivity{

    private EditText nameTxt;
    private EditText surnameTxt;
    private EditText ageTxt;
    private EditText telephoneTxt;
    private Button signUp2;

    private void init(){
        nameTxt = (EditText) findViewById(R.id.nameTxtUp);
        surnameTxt = (EditText) findViewById(R.id.surnameTxtUp);
        ageTxt = (EditText) findViewById(R.id.ageTxtUp);
        telephoneTxt = (EditText) findViewById(R.id.telephoneTxtUp);
        signUp2 = (Button) findViewById(R.id.letsSignUp2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_part2);
        init();
        signUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpPart2Activity.this, SignUpPart3Activity.class);
                intent.putExtra("id", getIntent().getLongExtra("id", 0L));
                intent.putExtra("login", getIntent().getStringExtra("login"));
                intent.putExtra("password", getIntent().getStringExtra("password"));
                intent.putExtra("email", getIntent().getStringExtra("email"));
                intent.putExtra("name", nameTxt.getText().toString());
                intent.putExtra("surname", surnameTxt.getText().toString());
                intent.putExtra("age", ageTxt.getText().toString());
                intent.putExtra("telephone", telephoneTxt.getText().toString());
                startActivity(intent);
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