package com.example.user.komputer;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.komputer.Adapter.NotifikasiAdapter;
import com.example.user.komputer.Adapter.NotifikasiRecyclerAdapter;
import com.example.user.komputer.Adapter.RiwayatAdapter;
import com.example.user.komputer.Model.Notifikasi;
import com.example.user.komputer.Model.Riwayat;
import com.example.user.komputer.Network.ApiInterface;
import com.example.user.komputer.SharedPreference.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RiwayatService extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static RiwayatService ma;
    String nama;
    private SwipeRefreshLayout SwipeRefresh;
    ProgressBar progressBar ;
    TextView emptyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar) ;
        emptyView = (TextView) findViewById(R.id.empty_view);
        SharedPrefManager sharedPrefManager = new SharedPrefManager(RiwayatService.this);
        nama = sharedPrefManager.getSPNama();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ma=this;
        refresh();

        SwipeRefresh = findViewById(R.id.swipe_for_refresh);
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
                        refresh();

                    }
                },2000); //4000 millisecond = 4 detik
            }
        });
    }

    public void refresh() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://pemrograman-web.ti.ulm.ac.id/")
                //.baseUrl("http://192.168.1.70/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();


        ApiInterface client = retrofit.create(ApiInterface.class);
        Call<Riwayat> call = client.getRiwayat(nama);
        call.enqueue(new Callback<Riwayat>() {
            @Override
            public void onResponse(Call<Riwayat> call, Response<Riwayat> response) {
                String value = response.body().getValue();
                progressBar.setVisibility(View.GONE);



                if (value.equals("1")) {
                    List<Notifikasi> KontakList = response.body().getListDataNotifikasi();
                    mAdapter = new RiwayatAdapter(KontakList);
                    mRecyclerView.setAdapter(mAdapter);


                }
                else{
                    progressBar.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                    emptyView.setText("Data Kosong,Lakukan transaksi terlebih dahulu");

                }







            }

            @Override
            public void onFailure(Call<Riwayat>call, Throwable t) {
                Toast.makeText(RiwayatService.this, "error :(", Toast.LENGTH_SHORT).show();
                emptyView.setText("Anda Tidak terkoneksi ke Internet");

            }
        });
    }
}
