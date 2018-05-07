package com.example.user.komputer;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.komputer.Model.ProfilModel;
import com.example.user.komputer.Network.ApiInterface;
import com.example.user.komputer.SharedPreference.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class Profil extends AppCompatActivity {
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent parentIntent = NavUtils.getParentActivityIntent(this);
                if (parentIntent == null) {
                    finish();
                    return true;
                } else {
                    parentIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(parentIntent);
                    finish();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }


    EditText et1;
    EditText editTelepon;
    EditText editAlamat;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        Toolbar ToolBarAtas2 = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(ToolBarAtas2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPrefManager sharedPrefManager = new SharedPrefManager(this);
        final String nama = sharedPrefManager.getSPNama();

        TextView textUserName = (TextView) findViewById(R.id.userName);

        textUserName.setText(nama);


        et1 = (EditText) findViewById(R.id.edit_text_nama);
        editTelepon = (EditText) findViewById(R.id.edit_text_telpon);
        editAlamat = (EditText) findViewById(R.id.edit_text_alamat);
        email = (EditText) findViewById(R.id.edit_text_email);
        // et1.setText(nama);

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

                et1.setText(p.getNamaUser());
                editTelepon.setText(p.getNamaTelpon());
                editAlamat.setText(p.getAlamatUser());
                email.setText(p.getEmail());


            }

            @Override
            public void onFailure(Call<ProfilModel> call, Throwable t) {
                // progressDialog.dismiss();
                Toast.makeText(Profil.this, "Failed to load", Toast.LENGTH_LONG).show();
            }
        });

        //EndGetData

        Button submit = (Button) findViewById(R.id.btn_to_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("http://pemrograman-web.ti.ulm.ac.id/")
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();
                ApiInterface client = retrofit.create(ApiInterface.class);
                Call<ProfilModel> call = client.updateProfil(nama, et1.getText().toString(), editTelepon.getText().toString(), editAlamat.getText().toString());

                call.enqueue(new Callback<ProfilModel>() {
                    @Override
                    public void onResponse(Call<ProfilModel> call, Response<ProfilModel> response) {
                        Toast.makeText(Profil.this, "Data telah di update " + response.body().getResponseServer(), Toast.LENGTH_SHORT).show();
                        // Log.v("cek sasja","isi dari konsumen" +response.body().getResponseServer() );
                        //


                    }

                    @Override
                    public void onFailure(Call<ProfilModel> call, Throwable t) {

                        Toast.makeText(Profil.this, "Gagal memasukkan Data :(", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


        // Spinner element
        //Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // spinner.setOnItemSelectedListener(this);


        // Spinner Drop down elements
        // List<String> categories = new ArrayList<String>();
        // categories.add("Pilih Kecamatan");
        //categories.add("Banjarmasin Utara");
        //categories.add("Banjarmasin Selatan");
        //categories.add("Banjarmasin Timur");
        // categories.add("Banjarmasin Barat");
        // Creating adapter for spinner
        // ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        // dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        //spinner.setAdapter(dataAdapter);

        // }

        // public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        //  String item = parent.getItemAtPosition(position).toString();


        //  if(position == 0){

        //  }else{
        //     Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        //  }

        //  }


        // @Override
        //  public void onNothingSelected(AdapterView<?> parent) {

        //  }




   /* public void submit(View view) {
        view.startAnimation(buttonClick);
        Toast.makeText(this, "Data Anda Telah Tersimpan", Toast.LENGTH_SHORT).show();
    }
*/


    }
}


