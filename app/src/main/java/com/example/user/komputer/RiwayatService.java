package com.example.user.komputer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.user.komputer.Adapter.NotifikasiAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        SharedPrefManager sharedPrefManager = new SharedPrefManager(RiwayatService.this);
        nama = sharedPrefManager.getSPNama();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ma=this;
        refresh();
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



                   List<Notifikasi> KontakList = response.body().getListDataNotifikasi();
                    Log.d("Retrofit Get", "Jumlah data Kontak: " +
                            String.valueOf(KontakList.size()));


                    mAdapter = new RiwayatAdapter(KontakList);
                    mRecyclerView.setAdapter(mAdapter);







            }

            @Override
            public void onFailure(Call<Riwayat>call, Throwable t) {
                Toast.makeText(RiwayatService.this, "error :(", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
