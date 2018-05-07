package com.example.user.komputer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.komputer.Model.ProfilModel;
import com.example.user.komputer.Network.ApiInterface;
import com.example.user.komputer.SharedPreference.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class login extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText inputNama= (EditText) findViewById(R.id.input_user_name);
        final EditText inputPassword=(EditText) findViewById(R.id.input_password) ;





        sharedPrefManager = new SharedPrefManager(this);

        if (sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(login.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
        Button inputData = (Button) findViewById(R.id.btn_login);
        inputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                        Toast.makeText(login.this, "Gagal memasukkan Data :(" , Toast.LENGTH_SHORT).show();
                        Log.v("Coba","isi dari konsumen" +inputPassword.getText().toString() );
                    }
                });
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
}
