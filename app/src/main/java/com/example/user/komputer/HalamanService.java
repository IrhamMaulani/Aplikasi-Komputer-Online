package com.example.user.komputer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.komputer.Model.ProfilModel;
import com.example.user.komputer.Network.ApiInterface;
import com.example.user.komputer.SharedPreference.SharedPrefManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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


        SharedPrefManager sharedPrefManager = new SharedPrefManager(this);
        final String nama = sharedPrefManager.getSPNama();



        Bundle b = this.getIntent().getExtras();
        String[] array=b.getStringArray("List");

       /* Bundle bundle = this.getIntent().getExtras();
        double [] array1 = bundle.getDoubleArray("Koordinat");*/

        TextView namaToko = (TextView) findViewById(R.id.nama_toko);
        TextView alamatToko = (TextView) findViewById(R.id.alamat);
        TextView jamBuka = (TextView) findViewById(R.id.openhours);

        final EditText keluhan = (EditText) findViewById(R.id.edit_text_kerusakan);



            namaToko.setText(array[0]);
            alamatToko.setText(array[1]);
           // jamBuka.setText("Open Hours " + array[2]);

            String foto = array[2];
            String idService = array[3];
            final int idServisan = Integer.parseInt(idService);

            ImageView imageView = (ImageView) findViewById(R.id.header_cover_image);

            Picasso.with(context).load(foto).into(imageView);

       Button submit = (Button) findViewById(R.id.btn_for_submit);

       submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               AlertDialog.Builder alert = new AlertDialog.Builder(HalamanService.this);
               alert.setCancelable(true);
               alert.setMessage("Apakah Anda ingin memesan?");
               alert.setPositiveButton("Confirm",
                       new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                              //isi

                               Retrofit.Builder builder = new Retrofit.Builder()
                                       .baseUrl("http://pemrograman-web.ti.ulm.ac.id/")
                                       .addConverterFactory(GsonConverterFactory.create());

                               Retrofit retrofit = builder.build();
                               ApiInterface client = retrofit.create(ApiInterface.class);
                               Call<ProfilModel> call = client.orderService(nama,idServisan,keluhan.getText().toString());

                               call.enqueue(new Callback<ProfilModel>() {
                                   @Override
                                   public void onResponse(Call<ProfilModel> call, Response<ProfilModel> response) {
                                       Toast.makeText(HalamanService.this, "" + response.body().getResponseServer(), Toast.LENGTH_SHORT).show();
                                       // Log.v("cek sasja","isi dari konsumen" +response.body().getResponseServer() );
                                       //



                                   }

                                   @Override
                                   public void onFailure(Call<ProfilModel> call, Throwable t) {

                                       Toast.makeText(HalamanService.this, "Gagal Meng-Order" , Toast.LENGTH_SHORT).show();

                                   }
                               });
                           }

                       });
               alert.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(HalamanService.this, "SA :(", Toast.LENGTH_SHORT).show();
                   }
               });
               AlertDialog dialog = alert.create();
               dialog.show();


           }
       });






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
