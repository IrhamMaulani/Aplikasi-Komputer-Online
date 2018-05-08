package com.example.user.komputer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.komputer.Model.ProfilModel;
import com.example.user.komputer.Network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {
    EditText inputUserNama;
    //EditText inputNama;
    EditText inputEmail;
    EditText inputPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputUserNama = (EditText) findViewById(R.id.input_user_name);
       // inputNama = (EditText) findViewById(R.id.input_name);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);


        final Button registerUser = (Button) findViewById(R.id.btn_signup);

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });



    }

    public void registerUser(){

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://pemrograman-web.ti.ulm.ac.id/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        ApiInterface client = retrofit.create(ApiInterface.class);
        Call<ProfilModel> call = client.registrasiUser(inputUserNama.getText().toString(), inputPassword.getText().toString(), inputEmail.getText().toString());

        call.enqueue(new Callback<ProfilModel>() {
            @Override
            public void onResponse(Call<ProfilModel> call, Response<ProfilModel> response) {

                final ProgressDialog progressDialog = new ProgressDialog(Register.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();
                Toast.makeText(Register.this, "Anda berhasil mendaftar " + response.body().getResponseServer(), Toast.LENGTH_SHORT).show();
                // Log.v("cek sasja","isi dari konsumen" +response.body().getResponseServer() );
                //
                Intent myIntent = new Intent(Register.this, login.class);
                Register.this.startActivity(myIntent);
                finish();


            }

            @Override
            public void onFailure(Call<ProfilModel> call, Throwable t) {

                Toast.makeText(Register.this, "Isikan Data anda Dengan Benar", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void keLogin(View view ){
        Intent registerIntent = new Intent(Register.this, login.class);

        // Start the new activity
        startActivity(registerIntent);
    }


}
