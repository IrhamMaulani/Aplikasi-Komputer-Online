package com.example.user.komputer;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment implements  View.OnClickListener {


    public ProfilFragment() {
        // Required empty public constructor
    }


    ImageView goToEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_profil, container, false);

        // Inflate the layout for this fragment
        goToEdit = (ImageView) myView.findViewById(R.id.edit_profil);
        goToEdit.setOnClickListener(this);

        return myView;
    }

    public void onClick(View v) {
        Intent intent = new Intent(getActivity(),Profil.class);
        getActivity().startActivity(intent);
    }

}
