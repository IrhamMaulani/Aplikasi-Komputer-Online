package com.example.shiina.komputer.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shiina.komputer.Model.DaftarService;
import com.example.shiina.komputer.R;

import java.util.List;

public class DaftarServiceAdapter extends ArrayAdapter<DaftarService> {

    private Context context;
    private List<DaftarService> values;

    public DaftarServiceAdapter(Context context, List<DaftarService> values) {
        super(context, R.layout.activity_daftar_service, values);

        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_daftar_service, parent, false);
        }

     DaftarService currentWord =  getItem(position);

        TextView namaService = (TextView) listItemView.findViewById(R.id.txt_for_nama);
        // Get the Miwok translation from the currentWord object and set this text on
        // the Miwok TextView.

        namaService.setText("Nama Service : "+currentWord.getNamaService());

        TextView hargaService = (TextView) listItemView.findViewById(R.id.txt_for_harga);

        hargaService.setText("Harga : Rp." + currentWord.getHargaService());

        return  listItemView;


    }


}
