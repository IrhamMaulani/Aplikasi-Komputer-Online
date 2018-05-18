package com.example.shiina.komputer;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shiina.komputer.Model.ProfilModel;
import com.example.shiina.komputer.Network.ApiInterface;
import com.example.shiina.komputer.SharedPreference.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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
    LinearLayout goToLogout;
    TextView userName;
    TextView userTelepon;
    TextView userAlamat;
    String nama;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_profil, container, false);



        SharedPrefManager sharedPrefManager = new SharedPrefManager(getContext());
        nama = sharedPrefManager.getSPNama();

        userName = (TextView) myView.findViewById(R.id.user_name_profil);

        userAlamat = (TextView) myView.findViewById(R.id.alamat_user);

        userTelepon = (TextView) myView.findViewById(R.id.user_telepon_profil) ;
        //userName.setText(nama);
        getData(nama);

        // Inflate the layout for this fragment
        goToEdit = (ImageView) myView.findViewById(R.id.edit_profil);
        goToEdit.setOnClickListener(this);

        goToHelp = (LinearLayout) myView.findViewById(R.id.goToHelp);
        goToHelp.setOnClickListener(this);

        goToLogout = (LinearLayout) myView.findViewById(R.id.btn_for_logout);
        goToLogout.setOnClickListener(this);



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



            case  R.id.btn_for_logout:

                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setCancelable(true);
                alert.setMessage("Apakah Anda ingin Keluar");
                alert.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPrefManager sharedPrefManager = new SharedPrefManager(getContext());
                                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                                startActivity(new Intent(getActivity(), login.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                getActivity().finish();

                            }

                        });
                alert.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // Toast.makeText(getActivity(), "SA :(", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();

                break;


        }

    }

    public void getData(String nama){
        //Mulai getData

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://pemrograman-web.ti.ulm.ac.id/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        ApiInterface client = retrofit.create(ApiInterface.class);
        Call<ProfilModel> call = client.getProfile(nama);
        call.enqueue(new Callback<ProfilModel>() {
            @Override
            public void onResponse(Call<ProfilModel> call, Response<ProfilModel> response) {
                //progressDialog.dismiss();
                ProfilModel p = response.body();

                userName.setText(p.getNamaUser());
                userTelepon.setText(p.getNamaTelpon());
                userAlamat.setText(p.getAlamatUser());

            }

            @Override
            public void onFailure(Call<ProfilModel> call, Throwable t) {
                // progressDialog.dismiss();
                Toast.makeText(getActivity(), "Failed to load", Toast.LENGTH_LONG).show();
            }
        });

        //EndGetData

    }

    @Override
    public void onResume() {
        super.onResume();
        getData(nama);
    }
}
