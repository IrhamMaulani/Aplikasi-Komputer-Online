package com.example.shiina.komputer;


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

import com.example.shiina.komputer.Adapter.TokoServiceListAdapter;
import com.example.shiina.komputer.Model.TokoService;
import com.example.shiina.komputer.Network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class RatingFragment extends Fragment {
    private ListView listView;
    private SwipeRefreshLayout SwipeRefresh;



    public RatingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.daftar_list, container, false);

        listView = (ListView) rootView.findViewById(R.id.list);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://pemrograman-web.ti.ulm.ac.id/")
                //.baseUrl("http://192.168.1.70/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        ApiInterface client = retrofit.create(ApiInterface.class);
        Call<List<TokoService>> call = client.getRatingKomputer();
        call.enqueue(new Callback<List<TokoService>>() {
            @Override
            public void onResponse(Call<List<TokoService>> call, Response<List<TokoService>> response) {
                if (response.isSuccessful()) {
                    final List<TokoService> repos = response.body();

                    listView.setAdapter(new TokoServiceListAdapter(getActivity(), repos));
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                            TokoService tokoService = repos.get(position);
                            // Intent intent = new Intent(MainActivity.this, UpdateData.class);
                            //intent.putExtra("EXTRA_SESSION_ID", konsumen.getIdkonsumen());

                            //String message="Terpilih : " + konsumen.getIdkonsumen();
                            // Toast.makeText(MainActivity.this, "aa:(" + message, Toast.LENGTH_SHORT).show();

                            // Start the new activity
                            //startActivity(intent);
                            Bundle b = new Bundle();
                            b.putStringArray("List", new String[]{tokoService.getNamaService(),tokoService.getAlamatService(),tokoService.getFotoService(),tokoService.getIdService(),tokoService.getWaktuBuka()});
                            b.putDoubleArray("koordinat", new double[]{tokoService.getLongitud(),tokoService.getLatitud()});
                            Intent i=new Intent(getActivity()   , HalamanService.class);
                            i.putExtras(b);
                            startActivity(i);

                            /*Bundle bundle = new Bundle();
                            bundle.putDoubleArray("koordinat", new double[]{tokoService.getLatitud(),tokoService.getLongitud()});
                            Intent intent=new Intent(getActivity()   , HalamanService.class);
                            intent.putExtras(bundle);
                            startActivity(intent);*/

                        }
                    });
                }

                else if(response.code() == 400){
                    Toast.makeText(getActivity(), "Server busuk :(", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<TokoService>> call, Throwable t) {
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
        Call<List<TokoService>> call = client.getRatingKomputer();
        call.enqueue(new Callback<List<TokoService>>() {
            @Override
            public void onResponse(Call<List<TokoService>> call, Response<List<TokoService>> response) {
                if (response.isSuccessful()) {
                    final List<TokoService> repos = response.body();

                    listView.setAdapter(new TokoServiceListAdapter(getActivity(), repos));

                }

                else if(response.code() == 400){
                    Toast.makeText(getActivity(), "Server busuk :(", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<TokoService>> call, Throwable t) {
                Toast.makeText(getActivity(), "error :(", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
