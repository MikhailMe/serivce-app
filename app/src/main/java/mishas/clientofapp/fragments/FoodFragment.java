package mishas.clientofapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import mishas.clientofapp.R;
import mishas.clientofapp.activities.BagActivity;
import mishas.clientofapp.activities.LiquidActivity;
import mishas.clientofapp.logic.Administrator;
import mishas.clientofapp.logic.ProductType;

public class FoodFragment extends Fragment {

    private Button plus1, plus2, plus3, plus4;
    private Button minus1, minus2, minus3, minus4;
    private TextView amount1, amount2, amount3, amount4;
    private Button next, viewOrder;
    private int i1, i2, i3, i4;

    public FoodFragment() {
        i1 = 0;
        i2 = 0;
        i3 = 0;
        i4 = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_food, container, false);
        //View v = inflater.inflate(R.layout.fragment_food, null);

        plus1 = (Button) v.findViewById(R.id.plus1);
        plus1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i1 < 20)
                    i1++;
                Administrator.products.put(ProductType.HOTDOG, i1);
                amount1.setText(String.valueOf(i1));
            }
        });
        plus2 = (Button) v.findViewById(R.id.plus2);
        plus2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i2 < 20)
                    i2++;
                Administrator.products.put(ProductType.BURGER, i2);
                amount2.setText(String.valueOf(i2));
            }
        });
        plus3 = (Button) v.findViewById(R.id.plus3);
        plus3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i3 < 20)
                    i3++;
                Administrator.products.put(ProductType.HOTCORN, i3);
                amount3.setText(String.valueOf(i3));
            }
        });
        plus4 = (Button) v.findViewById(R.id.plus4);
        plus4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i4 < 20)
                    i4++;
                Administrator.products.put(ProductType.CHIPS, i4);
                amount4.setText(String.valueOf(i4));
            }
        });

        minus1 = (Button) v.findViewById(R.id.minus1);
        minus1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i1 > 0)
                    i1--;
                Administrator.products.put(ProductType.HOTDOG, i1);
                amount1.setText(String.valueOf(i1));
            }
        });
        minus2 = (Button) v.findViewById(R.id.minus2);
        minus2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i2 > 0)
                    i2--;
                Administrator.products.put(ProductType.BURGER, i2);
                amount2.setText(String.valueOf(i2));
            }
        });
        minus3 = (Button) v.findViewById(R.id.minus3);
        minus3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i3 > 0)
                    i3--;
                Administrator.products.put(ProductType.HOTCORN, i3);
                amount3.setText(String.valueOf(i3));
            }
        });
        minus4 = (Button) v.findViewById(R.id.minus4);
        minus4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i4 > 0)
                    i4--;
                Administrator.products.put(ProductType.CHIPS, i4);
                amount4.setText(String.valueOf(i4));
            }
        });

        amount1 = (TextView) v.findViewById(R.id.amount1);
        amount2 = (TextView) v.findViewById(R.id.amount2);
        amount3 = (TextView) v.findViewById(R.id.amount3);
        amount4 = (TextView) v.findViewById(R.id.amount4);

        next = (Button) v.findViewById(R.id.nextToDrink);
        next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LiquidActivity.class);
                intent.putExtra("user", getActivity().getIntent().getSerializableExtra("user"));
                startActivity(intent);
            }
        });
        viewOrder = (Button) v.findViewById(R.id.viewOrder);
        viewOrder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BagActivity.class);
                intent.putExtra("user", getActivity().getIntent().getSerializableExtra("user"));
                startActivity(intent);
            }
        });

        return v; //inflater.inflate(R.layout.fragment_food, container, false);
    }
}