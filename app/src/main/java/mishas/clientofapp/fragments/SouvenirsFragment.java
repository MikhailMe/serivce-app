package mishas.clientofapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import mishas.clientofapp.R;
import mishas.clientofapp.activities.BagActivity;
import mishas.clientofapp.logic.Administrator;
import mishas.clientofapp.logic.ProductType;

public class SouvenirsFragment extends Fragment {

    private Button _plus1, _plus2, _plus3, _plus4;
    private Button _minus1, _minus2, _minus3, _minus4;
    private TextView _amount1, _amount2, _amount3, _amount4;
    private Button viewOrder3;
    private int i1, i2, i3, i4;

    public SouvenirsFragment() {
        i1 = 0;
        i2 = 0;
        i3 = 0;
        i4 = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_souvenirs, container, false);

        _plus1 = (Button) v.findViewById(R.id._plus1);
        _plus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i1 < 20) {
                    i1++;
                    Administrator.products.put(ProductType.BALL, i1);
                    _amount1.setText(String.valueOf(i1));
                }
            }
        });
        _plus2 = (Button) v.findViewById(R.id._plus2);
        _plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i2 < 20) {
                    i2++;
                    Administrator.products.put(ProductType.TSHITRT, i2);
                    _amount2.setText(String.valueOf(i2));
                }
            }
        });
        _plus3 = (Button) v.findViewById(R.id._plus3);
        _plus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i3 < 20) {
                    i3++;
                    Administrator.products.put(ProductType.MAGNET, i3);
                    _amount3.setText(String.valueOf(i3));
                }
            }
        });
        _plus4 = (Button) v.findViewById(R.id._plus4);
        _plus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i4 < 20) {
                    i4++;
                    Administrator.products.put(ProductType.FLAG, i4);
                    _amount4.setText(String.valueOf(i4));
                }
            }
        });

        _minus1 = (Button) v.findViewById(R.id._minus1);
        _minus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i1 > 0) {
                    i1--;
                    Administrator.products.put(ProductType.BALL, i1);
                    _amount1.setText(String.valueOf(i1));
                }
            }
        });
        _minus2 = (Button) v.findViewById(R.id._minus2);
        _minus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i2 > 0) {
                    i2--;
                    Administrator.products.put(ProductType.TSHITRT, i2);
                    _amount2.setText(String.valueOf(i2));
                }
            }
        });
        _minus3 = (Button) v.findViewById(R.id._minus3);
        _minus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i3 > 0) {
                    i3--;
                    Administrator.products.put(ProductType.MAGNET, i3);
                    _amount3.setText(String.valueOf(i3));
                }
            }
        });
        _minus4 = (Button) v.findViewById(R.id._minus4);
        _minus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i4 > 0) {
                    i4--;
                    Administrator.products.put(ProductType.FLAG, i4);
                    _amount4.setText(String.valueOf(i4));
                }
            }
        });

        _amount1 = (TextView) v.findViewById(R.id._amount1);
        _amount2 = (TextView) v.findViewById(R.id._amount2);
        _amount3 = (TextView) v.findViewById(R.id._amount3);
        _amount4 = (TextView) v.findViewById(R.id._amount4);

        viewOrder3 = (Button) v.findViewById(R.id.viewOrder3);
        viewOrder3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BagActivity.class));
            }
        });

        return v;
    }

}
