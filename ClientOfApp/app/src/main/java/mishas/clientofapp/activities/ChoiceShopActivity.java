package mishas.clientofapp.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import mishas.clientofapp.R;
import mishas.clientofapp.logic.Administrator;
import mishas.clientofapp.logic.Client;

import static android.R.id.message;

public class ChoiceShopActivity extends AppCompatActivity {

    private Button send;

    private void init(){
        send = (Button) findViewById(R.id.sendToServingApp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_place);
        init();
        send.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Client client = new Client("192.168.1.101", 9898);
                Client client = new Client("172.16.0.3",9898);
                client.sendRequest(Administrator.currentOrder.makeSendString());
            }
        });
    }

    private class MyAsyncTask extends AsyncTask<Void, String, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            Socket socket = new Socket("192.168.1.101", 9898);
                            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                            out.println(message);

                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... params) {
            super.onProgressUpdate(params);
        }
    }
}
