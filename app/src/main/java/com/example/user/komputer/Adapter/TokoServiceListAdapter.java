package com.example.user.komputer.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.komputer.Model.TokoService;
import com.example.user.komputer.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TokoServiceListAdapter extends ArrayAdapter<TokoService>{

    private Context context;
    private List<TokoService> values;

    public TokoServiceListAdapter(Context context, List<TokoService> values) {
        super(context, R.layout.activity_list_service, values);

        this.context = context;
        this.values = values;
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
       TokoService currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view.
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.namatoko);
        // Get the Miwok translation from the currentWord object and set this text on
        // the Miwok TextView.

            miwokTextView.setText(currentWord.getNamaService());


        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.alamat);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.

            defaultTextView.setText(currentWord.getAlamatService());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        Picasso.with(context).load(currentWord.getFotoService()).into(imageView);

        //imageView.setImageResource(currentWord.getFotoService());


        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }


}
