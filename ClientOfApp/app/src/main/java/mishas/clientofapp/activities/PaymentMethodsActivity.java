package mishas.clientofapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mishas.clientofapp.R;

public class PaymentMethodsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_payment_methods);
        this.setTitle("Способы оплаты");
    }
}
