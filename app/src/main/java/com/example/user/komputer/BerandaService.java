package com.example.user.komputer;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

public class BerandaService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda_service);
        //agar fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


       //menerima intent
        TextView textView=(TextView) findViewById(R.id.nama_toko);
        Intent intent=getIntent();
        String simpan = intent.getStringExtra("message");




        /*if (savedInstanceState == null) {

            FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();

            BerandaSatuFragment fragobj = new BerandaSatuFragment();

            Bundle bundle = new Bundle();
            bundle.putString("edttext", simpan);
            // set Fragmentclass Arguments

            fragobj.setArguments(bundle);


            ft.replace(android.R.id.content, fragobj);
            ft.addToBackStack(null);
            ft.commit();
        }*/





        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBarAtas2 = (Toolbar)findViewById(R.id.toolbar_satu);
        setSupportActionBar(ToolBarAtas2);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ToolBarAtas2.setLogoDescription(getResources().getString(R.string.kosong));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        HalamanServicePagerAdapter adapter = new HalamanServicePagerAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    }
