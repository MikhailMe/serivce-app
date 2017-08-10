package mishas.clientofapp.activities;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import mishas.clientofapp.R;

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;
    private TextView rubles;
    private String[] currentOrder = {"0", "0", "0", "0"};
    public CustomList(Activity context,
                      String[] web, Integer[] imageId, TextView rubles, String[] currentOrder) {
        super(context, R.layout.my_custom_list, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
        this.rubles = rubles;
        this.currentOrder = currentOrder;
    }
    @NonNull
    @Override
    public View getView(final int position, final View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.my_custom_list, null, true);
        final TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        final TextView textView = (TextView) rowView.findViewById(R.id.number);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        Button btn_minus = (Button) rowView.findViewById(R.id.minus_button);
        textView.setText(currentOrder[position]);
        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(textView.getText().toString());
                if (number != 0) {
                    textView.setText(number - 1 + "");
                    currentOrder[position] = textView.getText().toString();
                    rubles.setText(Integer.parseInt(rubles.getText().toString().split("\\ ")[0]) -
                            Integer.parseInt(web[position].substring(web[position].indexOf(" ") + 3, web[position].length() - 4)) + " руб");
                }
            }
        });
        Button btn_plus = (Button) rowView.findViewById(R.id.plus_button);
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(textView.getText().toString());
                textView.setText(number + 1 + "");
                currentOrder[position] = textView.getText().toString();
                rubles.setText(Integer.parseInt(rubles.getText().toString().split("\\ ")[0]) +
                        Integer.parseInt(web[position].substring(web[position].indexOf(" ") + 3, web[position].length() - 4)) + " руб");
            }
        });
        txtTitle.setText(web[position]);
        imageView.setImageResource(imageId[position]);
        return rowView;
    }

    public String[] getCurrentOrder() {
        return currentOrder;
    }
}
