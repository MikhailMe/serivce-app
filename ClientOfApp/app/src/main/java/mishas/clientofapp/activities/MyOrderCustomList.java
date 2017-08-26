package mishas.clientofapp.activities;

/**
 * Created by 809279 on 26.08.2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mishas.clientofapp.R;

class MyOrderCustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;
    private List<String> items = Arrays.asList("Хот-дог",
            "Гамбургер",
            "Кукуруза",
            "Чипсы",
            "Чай",
            "Кофе",
            "Вода",
            "Сок");

    MyOrderCustomList(Activity context,
                    String[] web, Integer[] imageId) {
        super(context, R.layout.my_order_custom_list, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
    }

    @NonNull
    @Override
    public View getView(final int position, final View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.my_order_custom_list, null, true);
        final TextView txtTitle = (TextView) rowView.findViewById(R.id.item_name_of_order);
        txtTitle.setTextSize(14);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon_of_order);
        txtTitle.setText(web[position]);
        for (String s : items) {
            if (web[position].contains(s)) {
                imageView.setImageResource(imageId[items.indexOf(s)]);
                break;
            }
        }
        return rowView;
    }
}
