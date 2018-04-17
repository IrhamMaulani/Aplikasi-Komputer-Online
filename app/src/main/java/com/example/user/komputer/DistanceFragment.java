package com.example.user.komputer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DistanceFragment extends Fragment {


    public DistanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.daftar_list, container, false);


        final ArrayList<Service> services = new ArrayList<Service>();
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

            }
        });
        return rootView;

    }

}
