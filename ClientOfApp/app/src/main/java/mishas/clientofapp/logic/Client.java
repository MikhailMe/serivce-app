package mishas.clientofapp.logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private String host;
    private int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /*public static void main(String[] args) {
        Client client = new Client("192.168.1.101", 9898);
        client.sendRequest("productList={Product(productType=HAMBURGER, price=100.0)=4," +
                " Product(productType=COLD_BEER, price=200.0)=5," +
                " Product(productType=HOT_CORN, price=100.0)=5," +
                " Product(productType=WATER, price=100.0)=3}");
    }*/

    public void sendRequest(final String message) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Socket socket = new Socket(Client.this.host, Client.this.port);
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(message);

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}