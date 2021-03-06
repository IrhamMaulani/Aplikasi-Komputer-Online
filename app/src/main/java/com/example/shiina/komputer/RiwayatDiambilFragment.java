package com.example.shiina.komputer;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shiina.komputer.Adapter.NotifikasiRecyclerAdapter;
import com.example.shiina.komputer.Adapter.RiwayatAdapter;
import com.example.shiina.komputer.Model.Notifikasi;
import com.example.shiina.komputer.Model.Riwayat;
import com.example.shiina.komputer.Network.ApiInterface;
import com.example.shiina.komputer.SharedPreference.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class RiwayatDiambilFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static FragmentActivity ma;
    String nama;
    private SwipeRefreshLayout SwipeRefresh;
    ProgressBar progressBar ;
    TextView emptyView;
    private String statusService ;
    private List<Notifikasi> kontakList = new ArrayList<>();
    private NotifikasiRecyclerAdapter viewAdapter;


    public RiwayatDiambilFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public RiwayatDiambilFragment(String statusService){
        this.statusService = statusService;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_view, container, false);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar) ;
        emptyView = (TextView) rootView.findViewById(R.id.empty_view);
        SharedPrefManager sharedPrefManager = new SharedPrefManager(getContext());
        nama = sharedPrefManager.getSPNama();

        viewAdapter = new NotifikasiRecyclerAdapter(getContext(), kontakList);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(viewAdapter);

        ma=getActivity();

        refresh();

        SwipeRefresh = rootView.findViewById(R.id.swipe_for_refresh);
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




        return rootView;
    }
    public void refresh(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://pemrograman-web.ti.ulm.ac.id/")
                //.baseUrl("http://192.168.1.70/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();


        ApiInterface client = retrofit.create(ApiInterface.class);
        Call<Riwayat> call = client.getRiwayat(nama,statusService);
        call.enqueue(new Callback<Riwayat>() {
            @Override
            public void onResponse(Call<Riwayat> call, Response<Riwayat> response) {
                if(response.body() == null){
                    emptyView.setText("Anda Tidak terkoneksi ke Internet");
                }
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
                progressBar.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
                emptyView.setText("Anda Tidak terkoneksi ke Internet");

            }
        });
    }



}
