package com.example.user.komputer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaSatuFragment extends Fragment {


    public BerandaSatuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        /*View myView = inflater.inflate(R.layout.fragment_beranda_satu, container, false);

        String strtext = getArguments().getString("edttext");

        TextView textView=(TextView) myView.findViewById(R.id.nama_toko);

        textView.setText(strtext);*/


        return inflater.inflate(R.layout.fragment_beranda_satu, container, false);
    }

}
