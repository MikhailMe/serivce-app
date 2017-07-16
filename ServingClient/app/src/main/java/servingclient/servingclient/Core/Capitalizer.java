package servingclient.servingclient.Core;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;


public class Capitalizer implements Runnable {
    private Socket socket;
    private int clientNumber;
    private final ArrayList<Order> orders;
    //private ArrayList<String> orderList;
    private ProductType[] productTypes = {ProductType.HOT_DOG,
            ProductType.CHEESEBURGER,
            ProductType.HAMBURGER,
            ProductType.HOT_CORN,
            ProductType.CHIPS,
            ProductType.COLD_BEER,
            ProductType.COCA_COLA,
            ProductType.WATER,
            ProductType.STEEL_WATER,
            ProductType.TEA,
            ProductType.COFFEE,
            ProductType.JUICE,
            ProductType.SCARF,
            ProductType.BALL,
            ProductType.T_SHIRT};
    private List<ProductType> productTypeArrayList = Arrays.asList(productTypes);

    public Capitalizer(Socket socket, int clientNumber, ArrayList<Order> orders) {
        this.socket = socket;
        this.clientNumber = clientNumber;
        this.orders = orders;
        //this.orderList = orderList;
        log("New connection with client# " + clientNumber + " at " + socket);
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            //PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                String input = "";
                try {
                    input = in.readLine();
                }
                catch (IOException e) {
                    log("got null string from the port: " + e);
                }
                if (input == null || input.equals("")) break;
                log("MESSAGE: " + input);

                HashMap<Product, Integer> productHash = new HashMap<>();
                String _string = input.substring(input.indexOf("{") + 1, input.indexOf("}")).replace("Product", "").replace("(", "").replace(")", "");
                StringTokenizer st = new StringTokenizer(_string, ", ");
                while (st.hasMoreTokens()) {
                    String _productType = st.nextToken();
                    String productType = _productType.substring(_productType.indexOf("=") + 1, _productType.length());
                    int counter = 0;
                    for (ProductType p : productTypeArrayList) {
                        if (p.toString().equals(productType)) break;
                        counter++;
                    }
                    Log.d("Counter", counter + "");
                    ProductType pt = productTypeArrayList.get(counter);
                    String priceAndNumber = st.nextToken();
                    double price = Double.parseDouble(priceAndNumber.split("=")[1]);
                    int number = Integer.parseInt(priceAndNumber.split("=")[2]);
                    Product product = new Product(pt, price);
                    productHash.put(product, number);
                }
                Order order = new Order(clientNumber, productHash, false, false);
                Log.d("Order", order.toString());
                synchronized (orders) {
                    orders.add(order);
                    //orderList.add("Order №" + order.getId());
                }
            }
        } catch (IOException e) {
            log("Error handling client# " + clientNumber + ": " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                log("Couldn't close a socket, what's going on?");
            }
            log("Connection with client# " + clientNumber + " closed");
        }
    }

    private void log(String message) {
        System.out.println(message);
    }
}
