package com.example.user.komputer;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment implements  View.OnClickListener {
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    public ProfilFragment() {
        // Required empty public constructor
    }


    ImageView goToEdit;
    LinearLayout goToHelp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_profil, container, false);

        // Inflate the layout for this fragment
        goToEdit = (ImageView) myView.findViewById(R.id.edit_profil);
        goToEdit.setOnClickListener(this);

        goToHelp = (LinearLayout) myView.findViewById(R.id.goToHelp);
        goToHelp.setOnClickListener(this);

        return myView;
    }

    public void onClick(View v) {
        v.startAnimation(buttonClick);

        switch(v.getId())
        {
            case R.id.edit_profil:
                Intent intent = new Intent(getActivity(),Profil.class);
                getActivity().startActivity(intent);

                break;

            case  R.id.goToHelp:
                Intent intent2 = new Intent(getActivity(),KeWebView.class);
                getActivity().startActivity(intent2);
                break;


        }

    }

}
