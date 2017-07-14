package servingclient.servingclient.GUI

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import android.widget.*
import servingclient.servingclient.Core.*

import servingclient.servingclient.R
import java.net.ServerSocket
import servingclient.servingclient.Core.Capitalizer
import android.net.wifi.WifiManager
import android.text.format.Formatter


class MainActivity : AppCompatActivity() {
    private var shop = Shop()
    private var currentOrderId: Long = -1
    private var currentlyOpenView: View? = null
    private var previouslyOpenedView: View? = null

    private val BACKGROUND_COLOR = "#87CEEB"
    private val SELECTED_ITEM_COLOR = "#FFFAFA"

    private val massive = arrayOf(
            R.drawable.hot_dog,
            R.drawable.hamburger,
            R.drawable.hamburger,
            R.drawable.hot_corn,
            R.drawable.chips,
            R.drawable.cold_beer,
            R.drawable.coca_cola,
            R.drawable.water,
            R.drawable.water,
            R.drawable.tea,
            R.drawable.coffee,
            R.drawable.juice,
            R.drawable.scarf,
            R.drawable.ball,
            R.drawable.t_shirt)

    private val list = arrayListOf<String>()
    private val ordersList = arrayListOf<String>()
    private var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        // setting bar view
        this.title = "Serving Orders"
        this.supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor(BACKGROUND_COLOR)))

        setContentView(R.layout.activity_main)

        // setting orders and shop for testing
        createEnvironment()

        //creating two listViews
        val listView = findViewById(R.id.listOrder) as ListView
        val listView1 = findViewById(R.id.listView) as ListView

        // setting list view with list view adapter
        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ordersList)
        shop.getOrders()?.forEach { ordersList.add("Order №" + it.id) }
        listView.adapter = adapter
        adapter?.notifyDataSetChanged()

        // setting list view with list view adapter
        val adapter1 = CustomAdapter(this, list, massive)
        listView1.adapter = adapter1

        // setting onclicklistener of the left listview
        listView.setOnItemClickListener { parent, view, position, id ->
            currentlyOpenView?.setBackgroundColor(Color.parseColor(BACKGROUND_COLOR))
            previouslyOpenedView = currentlyOpenView
            currentlyOpenView = view
            list.clear()
            val pressedOrder = ordersList[position]
            Log.d("DebugPressedItem", pressedOrder)
            Log.d("SIZE OF VIEW:", view.width.toString() + " : " + view.height.toString())
            val string = pressedOrder.split("№")
            Log.d("DebugPressedItem", "elem to find: " + string[1].toLong().toString())
            val order: Order? = shop.getOrders()?.first { it.id == string[1].toLong() }
            currentOrderId = string[1].toLong()
            order!!.productList.forEach { list.add(it.key.productType.toString() + " x " + it.value.toString()) }
            currentlyOpenView?.setBackgroundColor(Color.parseColor(SELECTED_ITEM_COLOR))

            adapter1.notifyDataSetChanged()
        }

        // setting button to handle current order
        val button = findViewById(R.id.orderButton) as Button
        button.text = "Order is Done"
        button.setTextColor(Color.WHITE)
        button.setOnClickListener {
            if (currentlyOpenView != null) {
                ordersList.remove(ordersList.first { it.split("№")[1].toLong() == currentOrderId })
                shop.getOrders()?.remove(shop.getOrders()?.first { it.id == currentOrderId })
                list.clear()
                currentlyOpenView?.setBackgroundColor(Color.parseColor(BACKGROUND_COLOR))
                adapter?.notifyDataSetChanged()
                adapter1.notifyDataSetChanged()
            }
        }
        // just some simple methods to check IP
        val wm = getSystemService(Context.WIFI_SERVICE) as WifiManager
        val ip = Formatter.formatIpAddress(wm.connectionInfo.ipAddress)
        Log.d("IPPPP__________:", ip)

        // new Thread not to touch UI but still work as a server
        Thread(Runnable {
            run()
        }).start()
    }

    // creating and environment
    fun createEnvironment() {
        val shopId = 1L
        val sectorId = 1L
        val servingUsers = arrayListOf<ServingUser>(ServingUser(1, "Billy", "1"))
        val productList =
                hashMapOf<Product, Int>(Product(ProductType.HOT_DOG, 100.0) to 2,
                        Product(ProductType.HOT_CORN, 100.0) to 1,
                        Product(ProductType.HAMBURGER, 100.0) to 4,
                        Product(ProductType.CHEESEBURGER, 100.0) to 5,
                        Product(ProductType.COCA_COLA, 100.0) to 3)
        val productList1 =
                hashMapOf<Product, Int>(Product(ProductType.HOT_CORN, 200.0) to 2,
                        Product(ProductType.HAMBURGER, 100.0) to 4,
                        Product(ProductType.CHEESEBURGER, 100.0) to 5,
                        Product(ProductType.COLD_BEER, 100.0) to 3)
        val productList2 =
                hashMapOf<Product, Int>(Product(ProductType.HAMBURGER, 300.0) to 2,
                        Product(ProductType.STEEL_WATER, 100.0) to 4,
                        Product(ProductType.CHEESEBURGER, 100.0) to 5,
                        Product(ProductType.COCA_COLA, 100.0) to 3)
        val fullProductList =
                hashMapOf<Product, Int>(Product(ProductType.HAMBURGER, 300.0) to 2,
                        Product(ProductType.STEEL_WATER, 100.0) to 4,
                        Product(ProductType.CHEESEBURGER, 100.0) to 5,
                        Product(ProductType.COCA_COLA, 100.0) to 3,
                        Product(ProductType.HOT_DOG, 100.0) to 1,
                        Product(ProductType.HOT_CORN, 100.0) to 7,
                        Product(ProductType.CHIPS, 100.0) to 14,
                        Product(ProductType.WATER, 100.0) to 9,
                        Product(ProductType.TEA, 100.0) to 12,
                        Product(ProductType.COFFEE, 100.0) to 8,
                        Product(ProductType.JUICE, 100.0) to 10,
                        Product(ProductType.BALL, 100.0) to 11,
                        Product(ProductType.T_SHIRT, 100.0) to 6)
        val orders =
                arrayListOf<Order>(Order(1, productList),
                        Order(2, productList1),
                        Order(345, productList2),
                        Order(3, productList2),
                        Order(333, productList1),
                        Order(200, productList))
        Log.d("Ordersdislpay", fullProductList.toString())
        shop = Shop(shopId, sectorId, arrayListOf<Order>(), servingUsers)
    }

    // listening for orders..
    fun run() {
        println("The capitalization server is running.")
        var clientNumber = 0
        val listener = ServerSocket(9898)
        try {
            while (true) {
                println("HI IM THERE")
                Thread(Capitalizer(listener.accept(), clientNumber++, shop.getOrders(), ordersList)).start()
                runOnUiThread { adapter?.notifyDataSetChanged() }
            }
        } finally {
            println("FINALLY I M THERE")
            listener.close()
        }
    }
}

