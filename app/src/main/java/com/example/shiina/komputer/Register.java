package com.example.shiina.komputer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shiina.komputer.Model.ProfilModel;
import com.example.shiina.komputer.Network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {
    EditText inputUserNama;
    //EditText inputNama;
    EditText inputEmail;
    EditText inputPassword,confirmPassword;
    ProgressDialog csprogress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputUserNama = (EditText) findViewById(R.id.input_user_name);
       // inputNama = (EditText) findViewById(R.id.input_name);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        confirmPassword = (EditText) findViewById(R.id.input_confirm_password);


        final Button registerUser = (Button) findViewById(R.id.btn_signup);
        csprogress=new ProgressDialog(Register.this);

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                csprogress.setMessage("Loading...");
                csprogress.show();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        csprogress.dismiss();
//whatever you want just you have to launch overhere.
                        if (!validate()) {
                            Toast.makeText(Register.this, "Isikan data anda dengan benar"  , Toast.LENGTH_SHORT).show();
                            return;
                        }

                        registerUser();


                    }
                }, 1000);

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



                if(response.body().getResponseServer().equals("Username sudah ada")) {
                    Toast.makeText(Register.this, "Username sudah ada" , Toast.LENGTH_SHORT).show();
                    inputUserNama.setError("Username sudah ada");
                }
                else{

                    final ProgressDialog progressDialog = new ProgressDialog(Register.this);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Authenticating...");
                    progressDialog.show();
                    Toast.makeText(Register.this, "" + response.body().getResponseServer(), Toast.LENGTH_SHORT).show();
                    // Log.v("cek sasja","isi dari konsumen" +response.body().getResponseServer() );
                    //
                    Intent myIntent = new Intent(Register.this, login.class);
                    Register.this.startActivity(myIntent);
                    finish();

                }

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

    public boolean validate() {
        boolean valid = true;

        String namaRegister = inputUserNama.getText().toString();
        String emailRegister = inputEmail.getText().toString();
        String passwordRegister = inputPassword.getText().toString();
        String confirmPasswordRegister = confirmPassword.getText().toString();
        //String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
       // String emailPattern ="^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";


        if (namaRegister.isEmpty() || namaRegister.length() < 3) {
            inputUserNama.setError("at least 3 characters");
            valid = false;
        } else {
            inputUserNama.setError(null);
        }

        if (emailRegister.isEmpty() || emailRegister.length() < 3 || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailRegister).matches() )  {
            inputEmail.setError("Input right email");
            valid = false;
        } else {
            inputEmail.setError(null);
        }

        if (passwordRegister.isEmpty() || passwordRegister.length() < 8) {
            inputPassword.setError("Password at least 8 characters");
            valid = false;
        } else {
            inputPassword.setError(null);
        }
        if (confirmPasswordRegister.isEmpty()||!confirmPasswordRegister.equals(passwordRegister)) {
            confirmPassword.setError("Confirm password berbeda dengan Password");
            valid = false;
        } else {
            confirmPassword.setError(null);
        }




        return valid;
    }


}
