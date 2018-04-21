package com.example.user.komputer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;


public class HalamanService extends AppCompatActivity implements OnMapReadyCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_service);



        Toolbar ToolBarAtas2 = (Toolbar)findViewById(R.id.toolbar_satu);
        setSupportActionBar(ToolBarAtas2);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ToolBarAtas2.setLogoDescription(getResources().getString(R.string.kosong));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Bundle b = this.getIntent().getExtras();
        String[] array=b.getStringArray("List");

        TextView namaToko = (TextView) findViewById(R.id.nama_toko);
        TextView alamatToko = (TextView) findViewById(R.id.alamat);
        TextView jamBuka = (TextView) findViewById(R.id.openhours);

        if (array != null) {
            namaToko.setText(array[0]);
            alamatToko.setText(array[1]);
            jamBuka.setText("Open Hours " + array[2]);
        }


        //Maps dimulai dari sini
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }
    @Override
    public void onMapReady(GoogleMap map) {

        LatLng loc = new LatLng(-3.297065, 114.585885);
        map.addMarker(new MarkerOptions()
                .position(new LatLng(-3.297065, 114.585885))
                .title("Marker"));
        map.moveCamera(CameraUpdateFactory.newLatLng(loc));
        map.animateCamera(CameraUpdateFactory.zoomTo(map.getCameraPosition().zoom - 0.5f));
    }



}
