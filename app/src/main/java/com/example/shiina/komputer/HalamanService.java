package com.example.shiina.komputer;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shiina.komputer.Model.ProfilModel;
import com.example.shiina.komputer.Model.RatingModel;
import com.example.shiina.komputer.Network.ApiClient;
import com.example.shiina.komputer.Network.ApiInterface;
import com.example.shiina.komputer.SharedPreference.SharedPrefManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HalamanService extends AppCompatActivity implements OnMapReadyCallback {
    private Context context;
   // private EditText keluhan;
    String nama,namaTokoService;
    int idServisan;
    ProgressDialog csprogress;
    String idService;
    RatingBar ratingBar;
    ApiInterface mApiInterface;
    float  ratingDouble ;
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
         nama = sharedPrefManager.getSPNama();

         ratingBar = (RatingBar) findViewById(R.id.ratingBar);




        mApiInterface = ApiClient.getClient().create(ApiInterface.class);





        csprogress=new ProgressDialog(HalamanService.this);

        Bundle b = this.getIntent().getExtras();
        String[] array=b.getStringArray("List");

       /* Bundle bundle = this.getIntent().getExtras();
        double [] array1 = bundle.getDoubleArray("Koordinat");*/

        TextView namaToko = (TextView) findViewById(R.id.nama_toko);
        TextView alamatToko = (TextView) findViewById(R.id.alamat);
        TextView jamBuka = (TextView) findViewById(R.id.openhours);

        // keluhan = (EditText) findViewById(R.id.edit_text_kerusakan);



         namaTokoService = array[0];

            namaToko.setText(array[0]);
            alamatToko.setText(array[1]);
            jamBuka.setText(array[4]);
           // jamBuka.setText("Open Hours " + array[2]);

            String foto = array[2];
             idService = array[3];
            idServisan = Integer.parseInt(idService);

            ImageView imageView = (ImageView) findViewById(R.id.header_cover_image);

            Picasso.with(context).load(foto).into(imageView);

            //
        //awal logika rating bar

        getRating();

//



        //



       Button submit = (Button) findViewById(R.id.btn_for_submit);

       submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Intent intent = new Intent(HalamanService.this, DaftarServiceActivity.class);
               intent.putExtra("idService", idService);
               intent.putExtra("namaPemesan", nama);
               intent.putExtra("namaToko", namaTokoService);
               startActivity(intent);






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
        Bundle b = this.getIntent().getExtras();
        double [] array1 = b.getDoubleArray("koordinat");
        if (array1 != null) {
            double longi = array1[0];
            double lat = array1[1];
            Log.v("cek sasja","isi dari konsumen" +lat );
            Log.v("cek sasja","isi dari konsumen" +longi );



            LatLng loc = new LatLng(lat, longi);

            map.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, longi))
                    .title(namaTokoService));
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

   /* public boolean validate() {
        boolean valid = true;

        String keluhanValid = keluhan.getText().toString();


        if (keluhanValid.isEmpty() || keluhanValid.length() < 3) {
            keluhan.setError("at least 3 characters");
            valid = false;
        } else {
            keluhan.setError(null);
        }


        return valid;
    }
    */

    /*
    public void submitData(){
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
    */

    public void getRating(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://pemrograman-web.ti.ulm.ac.id/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        ApiInterface client = retrofit.create(ApiInterface.class);
        Call<RatingModel> call = client.getNilaiKomputer(idServisan);
        call.enqueue(new Callback<RatingModel>() {
            @Override
            public void onResponse(Call<RatingModel> call, Response<RatingModel> response) {
                //progressDialog.dismiss();
                RatingModel p = response.body();


                ratingDouble = p.getTotalRating();
                //ratingString = "1";
                // Log.v("coba","isi dari rating String " + ratingDouble);

                //int ratingInt = (int) ratingDouble;

                //Log.v("coba","isi dari rating String " + ratingInt);

                ratingBar.setRating(ratingDouble);



            }

            @Override
            public void onFailure(Call<RatingModel> call, Throwable t) {
                // progressDialog.dismiss();
                Toast.makeText(HalamanService.this, "Failed to load", Toast.LENGTH_LONG).show();

            }
        });
        Log.v("coba","Isi dari idServisan " + idServisan);



    }

    @Override
    public void onResume(){
        super.onResume();
        getRating();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getRating();
    }
}

