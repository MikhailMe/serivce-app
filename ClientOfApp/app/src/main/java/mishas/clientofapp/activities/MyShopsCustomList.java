package mishas.clientofapp.activities;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mishas.clientofapp.R;

/**
 * Created by 809279 on 28.09.2017.
 */

public class MyShopsCustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> web;
    private final List<Integer> imageId;


    MyShopsCustomList(Activity context,
                      ArrayList<String> web, ArrayList<Integer> imageId) {
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
        if (web.size() != 0 && imageId.size() != 0) {
            txtTitle.setText(web.get(position));
            imageView.setImageResource(imageId.get(position));
        }
        return rowView;
    }
}