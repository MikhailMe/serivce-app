package servingclient.servingclient.Core

/**
 * Created by 809279 on 24.08.2017.
 */
import android.util.Log

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class Client(private val host: String, private val port: Int) {

    fun sendRequest(message: String) {
        object : Thread() {
            override fun run() {
                try {
                    val socket = Socket(host, port)
                    val out = PrintWriter(socket.getOutputStream(), true)
                    out.println(message)
                    socket.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }.start()
    }

}