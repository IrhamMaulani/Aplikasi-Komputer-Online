package com.example.user.komputer;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.komputer.Adapter.NotifikasiAdapter;
import com.example.user.komputer.Adapter.NotifikasiRecyclerAdapter;
import com.example.user.komputer.Adapter.RiwayatAdapter;
import com.example.user.komputer.Adapter.TokoServiceListAdapter;
import com.example.user.komputer.Model.Notifikasi;
import com.example.user.komputer.Model.Riwayat;
import com.example.user.komputer.Model.TokoService;
import com.example.user.komputer.Network.ApiClient;
import com.example.user.komputer.Network.ApiInterface;
import com.example.user.komputer.SharedPreference.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
   // private RecyclerView.LayoutManager mLayoutManager;
   private LinearLayoutManager mLayoutManager;
    public static StatusFragment ma;
    String nama;
     private SwipeRefreshLayout SwipeRefresh;
    ApiInterface mApiInterface;
    TextView emptyView;
    ProgressBar progressBar;

    public StatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.list_view, container, false);


         emptyView = (TextView)rootView.findViewById(R.id.empty_view);
         progressBar = (ProgressBar)rootView.findViewById(R.id.progress_bar);

        SharedPrefManager sharedPrefManager = new SharedPrefManager(getContext());
        nama = sharedPrefManager.getSPNama();



        SwipeRefresh = rootView.findViewById(R.id.swipe_for_refresh );
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
                        refreshData();

                    }
                },2000); //4000 millisecond = 4 detik
            }
        });
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        ma=this;
        refreshData();




        return rootView;


    }
    public void refreshData(){


        //Retrofit.Builder builder = new Retrofit.Builder()
               // .baseUrl("http://pemrograman-web.ti.ulm.ac.id/")
               // //.baseUrl("http://192.168.1.70/")
             //   .addConverterFactory(GsonConverterFactory.create());

       // Retrofit retrofit = builder.build();




       // ApiInterface client = retrofit.create(ApiInterface.class);
        //Call<Riwayat> call = client.getNotifikasi(nama);

        Call<Riwayat> call = mApiInterface.getNotifikasi(nama);
        call.enqueue(new Callback<Riwayat>() {
            @Override
            public void onResponse(Call<Riwayat> call, Response<Riwayat> response) {
                String value = response.body().getValue();
                progressBar.setVisibility(View.GONE);



               // List<Notifikasi> KontakList = response.body().getListDataNotifikasi();
                //Log.d("Retrofit Get", "Jumlah data Kontak: " +
                        //String.valueOf(KontakList.size()));

                if (value.equals("1")) {
                    List<Notifikasi> KontakList = response.body().getListDataNotifikasi();
                    mAdapter = new NotifikasiRecyclerAdapter(KontakList);
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
                Toast.makeText(getContext(), "error :(", Toast.LENGTH_SHORT).show();
                mRecyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
                emptyView.setText("Anda Tidak terkoneksi ke Internet");



            }
        });

    }

}
