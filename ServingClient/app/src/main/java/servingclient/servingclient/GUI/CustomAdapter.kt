package servingclient.servingclient.GUI

import android.widget.ArrayAdapter
import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.app.Activity
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import servingclient.servingclient.R


class CustomAdapter(private val context: Activity, private val itemname: ArrayList<String>?, private val imgid: Array<Int>)
    : ArrayAdapter<String>(context, R.layout.mylist, itemname) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater as LayoutInflater

        val rowView = inflater.inflate(R.layout.mylist, null) as View

        val txtTitle = rowView.findViewById(R.id.item) as TextView

        val imageView = rowView.findViewById(R.id.icon) as ImageView
        //imageView.setBackgroundColor(parent.solidColor)

        txtTitle.text = itemname?.get(position)
        txtTitle.setTextColor(Color.BLACK)
        txtTitle.setTextSize(20.0f)

        if (itemname != null)
            imageView.setImageResource(when (true) {
                itemname.get(position).contains("HOT_DOG") -> imgid[0]
                itemname.get(position).contains("CHEESEBURGER") -> imgid[1]
                itemname.get(position).contains("HAMBURGER") -> imgid[2]
                itemname.get(position).contains("HOT_CORN") -> imgid[3]
                itemname.get(position).contains("CHIPS") -> imgid[4]
                itemname.get(position).contains("COLD_BEER") -> imgid[5]
                itemname.get(position).contains("COCA_COLA") -> imgid[6]
                itemname.get(position).contains("WATER") -> imgid[7]
                itemname.get(position).contains("STEEL_WATER") -> imgid[8]
                itemname.get(position).contains("TEA") -> imgid[9]
                itemname.get(position).contains("COFFEE") -> imgid[10]
                itemname.get(position).contains("JUICE") -> imgid[11]
                itemname.get(position).contains("BALL") -> imgid[12]
                else -> imgid[13]
            })

        return rowView
    }
}