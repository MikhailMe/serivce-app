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
import mishas.clientofapp.logic.Administrator;
import mishas.clientofapp.logic.ProductType;

public class LiquidFragment extends Fragment {

    private Button plus_1, plus_2, plus_3, plus_4;
    private Button minus_1, minus_2, minus_3, minus_4;
    private TextView amount_1, amount_2, amount_3, amount_4;

    private Button viewOrder2;
    private int i1, i2, i3, i4;


    public LiquidFragment() {
        i1 = 0;
        i2 = 0;
        i3 = 0;
        i4 = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_liquid, container, false);

        plus_1 = (Button) v.findViewById(R.id.plus_1);
        plus_1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i1 < 20) {
                    i1++;
                    Administrator.products.put(ProductType.TEA, i1);
                    amount_1.setText(String.valueOf(i1));
                }
            }
        });
        plus_2 = (Button) v.findViewById(R.id.plus_2);
        plus_2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i2 < 20) {
                    i2++;
                    Administrator.products.put(ProductType.COFFEE, i2);
                    amount_2.setText(String.valueOf(i2));
                }
            }
        });
        plus_3 = (Button) v.findViewById(R.id.plus_3);
        plus_3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i3 < 20) {
                    i3++;
                    Administrator.products.put(ProductType.WATER, i3);
                    amount_3.setText(String.valueOf(i3));
                }
            }
        });
        plus_4 = (Button) v.findViewById(R.id.plus_4);
        plus_4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i4 < 20) {
                    i4++;
                    Administrator.products.put(ProductType.JUICE, i4);
                    amount_4.setText(String.valueOf(i4));
                }
            }
        });

        minus_1 = (Button) v.findViewById(R.id.minus_1);
        minus_1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i1 > 0) {
                    i1--;
                    Administrator.products.put(ProductType.TEA, i1);
                    amount_1.setText(String.valueOf(i1));
                }
            }
        });
        minus_2 = (Button) v.findViewById(R.id.minus_2);
        minus_2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i2 > 0) {
                    i2--;
                    Administrator.products.put(ProductType.COFFEE, i2);
                    amount_2.setText(String.valueOf(i2));
                }
            }
        });
        minus_3 = (Button) v.findViewById(R.id.minus_3);
        minus_3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i3 > 0) {
                    i3--;
                    Administrator.products.put(ProductType.WATER, i3);
                    amount_3.setText(String.valueOf(i3));
                }
            }
        });
        minus_4 = (Button) v.findViewById(R.id.minus_4);
        minus_4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i4 > 0) {
                    i4--;
                    Administrator.products.put(ProductType.JUICE, i4);
                    amount_4.setText(String.valueOf(i4));
                }
            }
        });

        amount_1 = (TextView) v.findViewById(R.id.amount_1);
        amount_2 = (TextView) v.findViewById(R.id.amount_2);
        amount_3 = (TextView) v.findViewById(R.id.amount_3);
        amount_4 = (TextView) v.findViewById(R.id.amount_4);

        viewOrder2 = (Button) v.findViewById(R.id.viewOrder2);
        viewOrder2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BagActivity.class));
            }
        });
        return v;
    }
}