package com.example.shiina.komputer;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements  View.OnClickListener {

   // private CardView goToDaftarService;
   // public HomeFragment() {
        // Required empty public constructor
    //}

    CardView goToDaftarService;
    CardView goToWebsite;
    CardView goToRiwayat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_home, container, false);


        goToDaftarService = (CardView) myView.findViewById(R.id.list_service);
        goToDaftarService.setOnClickListener(this);

        goToWebsite = (CardView) myView.findViewById(R.id.website);
        goToWebsite.setOnClickListener(this);

        goToRiwayat = (CardView) myView.findViewById(R.id.goToRiwayat);
        goToRiwayat.setOnClickListener(this);

        return myView;


    }
    @Override
    public void onClick(View v) {
        /*Intent intent = new Intent(getActivity(), ListService.class);
        getActivity().startActivity(intent);

        String url = "http://www.example.com";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);*/

        switch(v.getId())
        {
            case R.id.list_service:
                Intent intent = new Intent(getActivity(), ListService.class);
                getActivity().startActivity(intent);

                break;

            case R.id.website:
                Intent intent2 = new Intent(getActivity(), KeWebView.class);
                getActivity().startActivity(intent2);
                break;

            case R.id.goToRiwayat:
                Intent intent3 = new Intent(getActivity(), RiwayatService.class);
                getActivity().startActivity(intent3);
                break;

        }

    }

}
