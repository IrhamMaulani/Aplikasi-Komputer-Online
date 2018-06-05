package com.example.shiina.komputer;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shiina.komputer.Adapter.DaftarServiceAdapter;
import com.example.shiina.komputer.Model.DaftarService;
import com.example.shiina.komputer.Model.ProfilModel;
import com.example.shiina.komputer.Model.TokoService;
import com.example.shiina.komputer.Network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DaftarServiceActivity   extends AppCompatActivity {

    private ListView listView;
    private SwipeRefreshLayout SwipeRefresh;
    private String namaUser;
    private int idTokoService;
    private double hargaService;
    private ProgressDialog csprogress;
    TextView emptyView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_list);

        listView = (ListView) findViewById(R.id.list);
        emptyView = (TextView) findViewById(R.id.empty_view);

        namaUser = getIntent().getStringExtra("namaPemesan");
        String namaToko = getIntent().getStringExtra("namaToko");
        String idTokoServiceString = getIntent().getStringExtra("idService");
        idTokoService = Integer.parseInt(idTokoServiceString);


        Log.v("Coba","isi dari idtoko service" + idTokoService);
        Log.v("Coba","isi dari namaUser" + namaUser);

        csprogress=new ProgressDialog(DaftarServiceActivity.this);

        //getActionBar().setTitle("List Service di toko " + namaToko);
        getSupportActionBar().setTitle( namaToko);


        getData();

        SwipeRefresh = findViewById(R.id.swipe_refresh);
        // Mengeset properti warna yang berputar pada SwipeRefreshLayout
        SwipeRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);

        // Mengeset listener yang akan dijalankan saat layar di refresh/swipe
        SwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Handler digunakan untuk menjalankan jeda selama 5 detik
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Berhenti berputar/refreshing
                        SwipeRefresh.setRefreshing(false);

                        //Berganti Text Setelah Layar di Refresh
                       getData();

                    }
                },2000); //4000 millisecond = 4 detik
            }
        });




    }

    public void getData(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://pemrograman-web.ti.ulm.ac.id/")
                //.baseUrl("http://192.168.1.70/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        ApiInterface client = retrofit.create(ApiInterface.class);
        Call<List<DaftarService>> call = client.getDaftarService(idTokoService);
        call.enqueue(new Callback<List<DaftarService>>() {
            @Override
            public void onResponse(Call<List<DaftarService>> call, Response<List<DaftarService>> response) {
                if (response.isSuccessful()) {
                    if(response.body() == null){
                        emptyView.setText("Anda Tidak terkoneksi ke Internet");
                    }

                    final List<DaftarService> repos = response.body();

                    listView.setAdapter(new DaftarServiceAdapter(DaftarServiceActivity.this, repos));
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                            final DaftarService daftarService = repos.get(position);

                           Log.v("Coba","isi dari daftar Servicer" + daftarService.getNamaService());
                           hargaService = daftarService.getHargaService();



                            //Mulai


                            AlertDialog.Builder alert = new AlertDialog.Builder(DaftarServiceActivity.this);
                            alert.setCancelable(true);
                            alert.setMessage("Apakah Anda ingin memesan Service ini? \n \n" + "Nama Service : " + daftarService.getNamaService() + "\n \n" + "Harga Service : Rp."+daftarService.getHargaService());
                           // alert.setMessage("Nama Service : " + daftarService.getIdNamaService());
                           // alert.setMessage("Harga Service : Rp."+daftarService.getHargaService());
                            alert.setPositiveButton("Confirm",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //isi

                                            csprogress.setMessage("Loading...");
                                            csprogress.show();
                                            new Handler().postDelayed(new Runnable() {

                                                @Override
                                                public void run() {
                                                    csprogress.dismiss();
//whatever you want just you have to launch overhere.
                                                    sendData(daftarService.getNamaService());


                                                }
                                            }, 1000);

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




                            //selesai


                        }
                    });

                }

                else if(response.code() == 400){
                    Toast.makeText(DaftarServiceActivity.this, "Server busuk :(", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<DaftarService>> call, Throwable t) {
               // Toast.makeText(DaftarServiceActivity.this, "Terjadi Gangguan Silahkan coba lagi", Toast.LENGTH_SHORT).show();
                emptyView.setVisibility(View.VISIBLE);
                emptyView.setText("Empty Here!");
                listView.setVisibility(View.GONE);

            }
        });

    }

    public void sendData(String namaService){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://pemrograman-web.ti.ulm.ac.id/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        ApiInterface client = retrofit.create(ApiInterface.class);
        Call<ProfilModel> call = client.orderService(namaUser,idTokoService,namaService,hargaService);

        call.enqueue(new Callback<ProfilModel>() {
            @Override
            public void onResponse(Call<ProfilModel> call, Response<ProfilModel> response) {
                Toast.makeText(DaftarServiceActivity.this, "" + response.body().getResponseServer(), Toast.LENGTH_SHORT).show();
                // Log.v("cek sasja","isi dari konsumen" +response.body().getResponseServer() );
                //



            }

            @Override
            public void onFailure(Call<ProfilModel> call, Throwable t) {

                Toast.makeText(DaftarServiceActivity.this, "Gagal Meng-Order" , Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onResume() {
        getData();
        super.onResume();
    }
}
