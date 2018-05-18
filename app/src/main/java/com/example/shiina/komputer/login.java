package com.example.shiina.komputer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shiina.komputer.Model.ProfilModel;
import com.example.shiina.komputer.Network.ApiInterface;
import com.example.shiina.komputer.SharedPreference.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.os.Handler;

public class login extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
    ProgressDialog csprogress;
     EditText inputNama,inputPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         inputNama= (EditText) findViewById(R.id.input_user_name);
        inputPassword=(EditText) findViewById(R.id.input_password) ;





        sharedPrefManager = new SharedPrefManager(this);

        if (sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(login.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

         csprogress=new ProgressDialog(login.this);

        Button inputData = (Button) findViewById(R.id.btn_login);
        inputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                csprogress.setMessage("Loading...");
                csprogress.show();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        csprogress.dismiss();
//whatever you want just you have to launch overhere.

                        logIn();


                    }
                }, 1000);


            }
        });



       /* Button mainActivity = (Button) findViewById(R.id.btn_login);

        mainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent utamaIntent = new Intent(login.this, MainActivity.class);

                // Start the new activity
                startActivity(utamaIntent);
            }
        });
//s
*/
    }

    public void keSignUp(View view ){
        Intent registerIntent = new Intent(login.this, Register.class);

        // Start the new activity
        startActivity(registerIntent);
    }

    public void logIn(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://pemrograman-web.ti.ulm.ac.id/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        ApiInterface client = retrofit.create(ApiInterface.class);
        Call<ProfilModel> call = client.postLogin(inputNama.getText().toString(),inputPassword.getText().toString());

        call.enqueue(new Callback<ProfilModel>() {
            @Override
            public void onResponse(Call<ProfilModel> call, Response<ProfilModel> response) {
                //Toast.makeText(login.this, "Data telah di update " + response.body().getResponseServer(), Toast.LENGTH_SHORT).show();
                //Log.v("cek sasja","isi dari konsumen" +response.body().getResponseServer() );
                Log.v("cek ","isi dari konsumen" +response.body().getResponseServer() );
                String verifikasi = response.body().getResponseServer();
                if (response.isSuccessful()) {
                    if (verifikasi.equals("success")) {

                        //  ProfilModel profil = new ProfilModel();



                        Toast.makeText(login.this, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
                        String nama = inputNama.getText().toString();
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, nama);
                        // Shared Pref ini berfungsi untuk menjadi trigger session login
                        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                        startActivity(new Intent(login.this, MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();


                    } else if (verifikasi.equals("failed")) {
                        Toast.makeText(login.this, "Username dan Password anda salah", Toast.LENGTH_SHORT).show();
                    }

                    else if (verifikasi.equals("verifikasi")) {
                        Toast.makeText(login.this, "Silahkan Verifikasi akun anda dulu", Toast.LENGTH_SHORT).show();
                    }
                    else if (verifikasi.equals("null")) {
                        Toast.makeText(login.this, "Username dan Password anda kosong", Toast.LENGTH_SHORT).show();
                    }



                }
            }

            @Override
            public void onFailure(Call<ProfilModel> call, Throwable t) {

                Toast.makeText(login.this, "Isikan Data Anda dengan Benar" , Toast.LENGTH_SHORT).show();
                Log.v("Coba","isi dari konsumen" +inputPassword.getText().toString() );
            }
        });
    }


    }

