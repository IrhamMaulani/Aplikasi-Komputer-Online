package com.example.user.komputer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.Map;


public class HalamanService extends AppCompatActivity implements OnMapReadyCallback {
    private Context context;

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

       /* Bundle bundle = this.getIntent().getExtras();
        double [] array1 = bundle.getDoubleArray("Koordinat");*/

        TextView namaToko = (TextView) findViewById(R.id.nama_toko);
        TextView alamatToko = (TextView) findViewById(R.id.alamat);
        TextView jamBuka = (TextView) findViewById(R.id.openhours);

        if (array != null) {
            namaToko.setText(array[0]);
            alamatToko.setText(array[1]);
            jamBuka.setText("Open Hours " + array[2]);
            String foto = array[3];

            ImageView imageView = (ImageView) findViewById(R.id.header_cover_image);

            Picasso.with(context).load(foto).into(imageView);
        }






        //Maps dimulai dari sini
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        GoogleMapOptions mapOptions = new GoogleMapOptions();
        MapFragment.newInstance(mapOptions);
        mapFragment.getMapAsync(this);


    }
    @Override
    public void onMapReady(GoogleMap map) {
        Bundle bundle = this.getIntent().getExtras();
        double [] array1 = bundle.getDoubleArray("Koordinat");
        if (array1 != null) {
            double lat = array1[0];
            double lang = array1[1];



            LatLng loc = new LatLng(lat, lang);

            map.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lang))
                    .title("Marker"));
            map.moveCamera(CameraUpdateFactory.newLatLng(loc));
            map.animateCamera(CameraUpdateFactory.zoomTo(map.getCameraPosition().zoom - 0.5f));
        }
    }


    //over-riding back button fisik di android
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), ListService.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        super.onBackPressed();
    }
}
