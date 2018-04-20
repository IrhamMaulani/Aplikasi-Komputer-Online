package com.example.user.komputer;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Profil extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent parentIntent = NavUtils.getParentActivityIntent(this);
                if(parentIntent == null) {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        Toolbar ToolBarAtas2 = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(ToolBarAtas2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


       EditText et1 = (EditText) findViewById(R.id.edit_text_nama);
       et1.setText("Irham");



        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);



        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Pilih Kecamatan");
        categories.add("Banjarmasin Utara");
        categories.add("Banjarmasin Selatan");
        categories.add("Banjarmasin Timur");
        categories.add("Banjarmasin Barat");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();


       if(position == 0){

        }else{
           Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

       }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




    public void submit(View view) {
        view.startAnimation(buttonClick);
        Toast.makeText(this, "Data Anda Telah Tersimpan", Toast.LENGTH_SHORT).show();
    }
}
