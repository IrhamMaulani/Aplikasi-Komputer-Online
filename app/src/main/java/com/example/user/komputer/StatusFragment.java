package com.example.user.komputer;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.komputer.Adapter.NotifikasiAdapter;
import com.example.user.komputer.Adapter.TokoServiceListAdapter;
import com.example.user.komputer.Model.Notifikasi;
import com.example.user.komputer.Model.TokoService;
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
    private ListView listView;
    private SwipeRefreshLayout SwipeRefresh;
    String nama;

    public StatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.daftar_list, container, false);



        SharedPrefManager sharedPrefManager = new SharedPrefManager(getContext());
        nama = sharedPrefManager.getSPNama();


        listView = (ListView) rootView.findViewById(R.id.list);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://pemrograman-web.ti.ulm.ac.id/")
                //.baseUrl("http://192.168.1.70/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        ApiInterface client = retrofit.create(ApiInterface.class);
        Call<List<Notifikasi>> call = client.getNotifikasi(nama);
        call.enqueue(new Callback<List<Notifikasi>>() {
            @Override
            public void onResponse(Call<List<Notifikasi>> call, Response<List<Notifikasi>> response) {
                if (response.isSuccessful()) {
                    final List<Notifikasi> repos = response.body();

                    listView.setAdapter(new NotifikasiAdapter(getActivity(), repos));

                }

                else if(response.code() == 400){
                    Toast.makeText(getActivity(), "Server busuk :(", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Notifikasi>> call, Throwable t) {
                Toast.makeText(getActivity(), "error :(", Toast.LENGTH_SHORT).show();

            }
        });

        SwipeRefresh = rootView.findViewById(R.id.swipe_refresh);
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
                        refreshDataKomputer();

                    }
                },2000); //4000 millisecond = 4 detik
            }
        });




        return rootView;


    }
    public void refreshDataKomputer(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://pemrograman-web.ti.ulm.ac.id/")
                //.baseUrl("http://192.168.1.70/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        ApiInterface client = retrofit.create(ApiInterface.class);
        Call<List<Notifikasi>> call = client.getNotifikasi(nama);
        call.enqueue(new Callback<List<Notifikasi>>() {
            @Override
            public void onResponse(Call<List<Notifikasi>> call, Response<List<Notifikasi>> response) {
                if (response.isSuccessful()) {
                    final List<Notifikasi> repos = response.body();

                    listView.setAdapter(new NotifikasiAdapter(getActivity(), repos));

                }

                else if(response.code() == 400){
                    Toast.makeText(getActivity(), "Server busuk :(", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Notifikasi>> call, Throwable t) {
                Toast.makeText(getActivity(), "error :(", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
