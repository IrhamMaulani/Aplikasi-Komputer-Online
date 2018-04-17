package com.example.user.komputer;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ServiceAdapter extends ArrayAdapter<Service> {


    public ServiceAdapter(Context context, ArrayList<Service> services) {
        super(context, 0, services);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_list_service, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Service currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view.
        TextView namaTokoTextView = (TextView) listItemView.findViewById(R.id.namatoko);
        // Get the Miwok translation from the currentWord object and set this text on
        // the Miwok TextView.
        namaTokoTextView.setText(currentWord.getNamaToko());


        TextView waktuBukaTextView = (TextView) listItemView.findViewById(R.id.waktubuka);
        waktuBukaTextView.setText(currentWord.getJamBuka());

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView alamatTokoTextView = (TextView) listItemView.findViewById(R.id.alamat);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        alamatTokoTextView.setText(currentWord.getAlamatToko());

        ImageView imageLaptop = (ImageView) listItemView.findViewById(R.id.laptop);
        ImageView imagePrinter = (ImageView) listItemView.findViewById(R.id.printer);

        if(currentWord.getKodePerbaikan() == 2 ){
            imageLaptop.setVisibility(View.VISIBLE);
            imagePrinter.setVisibility(View.VISIBLE);
        }
        else if(currentWord.getKodePerbaikan() == 1){
            imageLaptop.setVisibility(View.VISIBLE);
            imagePrinter.setVisibility(View.INVISIBLE);
        }
        else if(currentWord.getKodePerbaikan() == 0){
            imagePrinter.setVisibility(View.VISIBLE);
            imageLaptop.setVisibility(View.INVISIBLE);
        }






        /*
        // Find the ImageView in the list_item.xml layout with the ID image.
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        // Check if an image is provided for this word or not
        if (currentWord.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            imageView.setImageResource(currentWord.getImageResourceId());
            // Make sure the view is visible
            imageView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            imageView.setVisibility(View.GONE);
        }
        */


        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }


    }

