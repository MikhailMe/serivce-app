package servingclient.servingclient.GUI

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.EditText
import android.widget.Toast
import servingclient.servingclient.Core.*
import servingclient.servingclient.R


class StartingActivity: Activity() {
    private val LONG_DELAY = 3500
    private var shop = Shop()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.starting_activity)
        createEnvironment()
        buttonInit()
    }

    fun createEnvironment() {
        val shopId = 1L
        val sectorId = 1L
        val servingUsers = arrayListOf<ServingUser>(ServingUser(1, "Billy", "1"))
        val productList = hashMapOf<Product, Int>(Product(ProductType.HOT_DOG, 100.0) to 2)
        val orders = arrayListOf<Order>(Order(1, productList))
        shop = Shop(shopId, sectorId, orders, servingUsers)
    }

    fun buttonInit() {
        val button = findViewById(R.id.button4)
        button.setOnClickListener {
            val login = (findViewById(R.id.editText) as EditText).text.toString()
            val password = (findViewById(R.id.editText2) as EditText).text.toString()
            Log.d("DebugLogOn", login + " " + password)
            if (shop.checkServingUser(ServingUser(1, login, password))) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                val textToast = Toast.makeText(applicationContext,
                        "Wrong combination of login and password!",
                        LONG_DELAY)
                textToast.setGravity(Gravity.CENTER, 0, 0)
                textToast.show()
            }
        }
    }

}