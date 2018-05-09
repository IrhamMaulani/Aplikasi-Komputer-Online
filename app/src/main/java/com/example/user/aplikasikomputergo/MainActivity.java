package com.example.user.aplikasikomputergo;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView teks;
    private BottomNavigationView.OnNavigationItemSelectedListener botnav = new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigasi_home:
                    teks.setText(R.string.title_home);
                    return true;
                case R.id.navigasi_book:
                    teks.setText(R.string.title_book);
                    return true;
                case R.id.navigasi_music:
                    teks.setText(R.string.title_music);
                    return true;
                case R.id.navigasi_video:
                    teks.setText(R.string.title_video);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teks = (TextView) findViewById(R.id.teksView);
        BottomNavigationView BNV = (BottomNavigationView) findViewById(R.id.navigasi);
        BNV.setOnNavigationItemSelectedListener(botnav);
    }

    public void klikSaya(View view){
        Intent KeluargKamus = new Intent(this, Splash.class);
        startActivity(KeluargKamus);
    }
}