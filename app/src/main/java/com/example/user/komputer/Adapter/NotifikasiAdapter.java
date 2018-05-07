package com.example.user.komputer.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.komputer.Model.Notifikasi;
import com.example.user.komputer.Model.TokoService;
import com.example.user.komputer.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NotifikasiAdapter extends ArrayAdapter<Notifikasi> {

    private Context context;
    private List<Notifikasi> values;

    public NotifikasiAdapter(Context context, List<Notifikasi> values) {
        super(context, R.layout.fragment_status, values);

        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.fragment_status, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Notifikasi currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view.
        TextView statusTransaksi = (TextView) listItemView.findViewById(R.id.statusproses);
        // Get the Miwok translation from the currentWord object and set this text on
        // the Miwok TextView.

        statusTransaksi.setText(currentWord.getStatusTransaksi());


        CardView cardView = (CardView) listItemView.findViewById(R.id.list_service);

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView namaToko = (TextView) listItemView.findViewById(R.id.toko_komputer);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.

        namaToko.setText(currentWord.getNamaToko());


       ImageView imageView = (ImageView) listItemView.findViewById(R.id.gambar_status);

        switch (currentWord.getStatusTransaksi()) {
            case "Menunggu":
                imageView.setImageResource(R.drawable.ic_help);
                imageView.setBackgroundColor(ContextCompat.getColor(context, R.color.green));

                break;
            case "ON-PROSES":
                imageView.setImageResource(R.drawable.notifikasi_putih);
                imageView.setBackgroundColor(ContextCompat.getColor(context, R.color.kuningKartu));
                break;

            case "SELESAI":
                cardView.setVisibility(View.GONE);


            default:
                imageView.setVisibility(View.INVISIBLE);
                break;
        }

        //imageView.setImageResource(currentWord.getFotoService());



        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }



}
