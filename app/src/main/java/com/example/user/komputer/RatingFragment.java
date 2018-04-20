package com.example.user.komputer;


import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RatingFragment extends Fragment {


    public RatingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.daftar_list, container, false);


        final ArrayList<Service> services = new ArrayList<Service>();
        services.add(new Service("Bintang Komputer","09:00 - 17:30","Jl.Raya Banjar Indah,Banjarmasin Selatan",0));
        services.add(new Service("BSS Komputer","08:00 - 22:00","Jl.Adi Patra 1,Banjarmasin Selatan",1));
        services.add(new Service("Surabaya Com","08:30 - 17:30","Jl.Let.Jend S.paraman,Banjarmasin Barat",2));
        services.add(new Service("Untung Service Komputer","00:00 - 23:59","Jl.Raya Purna Sakti,Banjarmasin Selatan",2));
        services.add(new Service("Energi Komputer","09:00 - 17:30","Jl.Saka Permain No 20,Banjarmasin Barat",2));
        services.add(new Service("DPL Komputer","09:00 - 17:30","Jl.Padat Karya,Banjarmasin Utara",1));
        services.add(new Service("SMC Komputer","09:00 - 17:30","Jl.Sultan Adam ,Banjarmasin Utara",2));
        services.add(new Service("Amici Komputer","09:00 - 17:30","Jl.Mesjid Jami,Banjarmasin Utara",2));
        services.add(new Service("T4 Service ","09:00 - 17:30","Jl.Sultan Adam,Banjarmasin Utara",2));
        services.add(new Service("Asoka.com","09:00 - 17:30","Jl.Sultan Adam,Banjarmasin Utara",2));
        services.add(new Service("A&W Service","09:00 - 17:30","Jl.Sultan Adam,Banjarmasin Utara",2));
        services.add(new Service("Nix Computer","09:00 - 17:30","Jl.Sultan Adam,Banjarmasin Utara",2));

        ServiceAdapter adapter = new ServiceAdapter(getActivity(), services);

        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
              //untuk bisa di click
                Service service = services.get(position);

                /*String message="Terpilih : " + service.getNamaToko();

                Intent intent=new Intent(getActivity(),HalamanService.class);
                intent.putExtra("message", message);
                startActivity(intent);
                */

                Bundle b = new Bundle();
                b.putStringArray("List", new String[]{service.getNamaToko(), service.getAlamatToko(),service.getJamBuka()});
                Intent i=new Intent(getActivity(), HalamanService.class);
                i.putExtras(b);
                startActivity(i);



            }
        });
        return rootView;




    }

}
