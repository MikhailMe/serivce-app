package mishas.clientofapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import mishas.clientofapp.R;

class EventCustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] web;
    private final String[] web_def;
    private final Integer[] imageId;

    EventCustomList(Activity context,
                           String[] web, String[] web_def, Integer[] imageId) {
        super(context, R.layout.my_custom_list, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
        this.web_def = web_def;
    }

    @NonNull
    @Override
    public View getView(final int position, final View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.my_event_custom_list, null, true);
        final TextView txtTitle = (TextView) rowView.findViewById(R.id.item_name);
        txtTitle.setTextSize(14);
        final TextView textView = (TextView) rowView.findViewById(R.id.item_def);
        textView.setTextSize(12);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        ImageView next = (ImageView) rowView.findViewById(R.id.continue_button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodActivity.class);
                intent.putExtra("from", "main");
                context.startActivity(intent);
            }
        });
        txtTitle.setText(web[position]);
        textView.setText(web_def[position]);
        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
