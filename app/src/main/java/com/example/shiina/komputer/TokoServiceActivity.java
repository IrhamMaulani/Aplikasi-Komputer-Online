package com.example.shiina.komputer;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import com.example.shiina.komputer.Adapter.TokoServiceAdapter;
import com.example.shiina.komputer.Model.GetTokoServiceModel;
import com.example.shiina.komputer.Model.TokoServiceModel;
import com.example.shiina.komputer.Network.ApiClient;
import com.example.shiina.komputer.Network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TokoServiceActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SwipeRefreshLayout SwipeRefresh;
    private List<TokoServiceModel> kontakList = new ArrayList<>();
    private TokoServiceAdapter viewAdapter;

    public static TokoServiceActivity ma;
    TextView emptyView;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        viewAdapter = new TokoServiceAdapter(this, kontakList);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        mRecyclerView.setAdapter(viewAdapter);
        ma=this;
        refreshData();

        emptyView = (TextView)findViewById(R.id.empty_view);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        SwipeRefresh = findViewById(R.id.swipe_for_refresh );
        // Mengeset properti warna yang berputar pada SwipeRefreshLayout
        SwipeRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);


        RelativeLayout relativeLayout = findViewById(R.id.activity_view);

        relativeLayout.setBackgroundColor(Color.parseColor("#ededed"));

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

}

public void refreshData(){
    Call<GetTokoServiceModel> call = mApiInterface.getTokoKomputer();
    call.enqueue(new Callback<GetTokoServiceModel>() {
        @Override
        public void onResponse(Call<GetTokoServiceModel> call, Response<GetTokoServiceModel> response) {
            if(response.body() == null){
                emptyView.setText("Anda Tidak terkoneksi ke Internet");
            }


            String value = response.body().getValue();

            progressBar.setVisibility(View.GONE);

            if(value == null){
                value = "0";
            }



            // List<Notifikasi> KontakList = response.body().getListDataNotifikasi();
            //Log.d("Retrofit Get", "Jumlah data Kontak: " +
            //String.valueOf(KontakList.size()));

            if (value.equals("1")) {


                    List<TokoServiceModel> KontakList = response.body().getListDataNotifikasi();
                    mAdapter = new TokoServiceAdapter(KontakList);
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
        public void onFailure(Call<GetTokoServiceModel>call, Throwable t) {

            mRecyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
            emptyView.setText("Anda Tidak terkoneksi ke Internet");
            progressBar.setVisibility(View.GONE);



        }
    });
}
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

       // searchView.setQueryHint("Cari Nama Mahasiswa");
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);
        searchView.setQuery("", false);
        searchView.setIconified(true);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mRecyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        Call<GetTokoServiceModel> call = mApiInterface.searchTokoKomputer(newText);
        call.enqueue(new Callback<GetTokoServiceModel>() {
            @Override
            public void onResponse(Call<GetTokoServiceModel> call, Response<GetTokoServiceModel> response) {
                String value = response.body().getValue();
                progressBar.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);



                // List<Notifikasi> KontakList = response.body().getListDataNotifikasi();
                //Log.d("Retrofit Get", "Jumlah data Kontak: " +
                //String.valueOf(KontakList.size()));

                if (value.equals("1")) {
                    if(response.body() == null){
                        emptyView.setText("Anda Tidak terkoneksi ke Internet");
                    }
                    else {
                        emptyView.setVisibility(View.GONE);
                        List<TokoServiceModel> KontakList = response.body().getListDataNotifikasi();
                        mAdapter = new TokoServiceAdapter(KontakList);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                }
                else{
                    progressBar.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                    emptyView.setText("Nobody But Us Chicken");

                }

            }

            @Override
            public void onFailure(Call<GetTokoServiceModel>call, Throwable t) {

                mRecyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
                emptyView.setText("Anda Tidak terkoneksi ke Internet");
                progressBar.setVisibility(View.GONE);



            }
        });
        return true;
    }


}