package com.example.user.komputer;


import android.content.DialogInterface;
import android.content.Intent;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_home, container, false);
        goToDaftarService = (CardView) myView.findViewById(R.id.list_service);
        goToDaftarService.setOnClickListener(this);
        return myView;


    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), ListService.class);
        getActivity().startActivity(intent);


    }
/*
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       goToDaftarService = (CardView) view.findViewById(R.id.list_service);

        goToDaftarService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListService.class);
                getActivity().startActivity(intent);
            }
        });


    }
*/
}
