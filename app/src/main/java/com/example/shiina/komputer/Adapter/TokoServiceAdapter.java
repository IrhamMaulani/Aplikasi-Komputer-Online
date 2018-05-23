package com.example.shiina.komputer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shiina.komputer.HalamanService;
import com.example.shiina.komputer.Model.TokoServiceModel;
import com.example.shiina.komputer.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static java.sql.Types.NULL;

public class TokoServiceAdapter extends RecyclerView.Adapter<TokoServiceAdapter.MyViewHolder>  {

    private List<TokoServiceModel> mKontakList;
    private Context context;

    public TokoServiceAdapter(List <TokoServiceModel> KontakList) {
        mKontakList = KontakList;
    }
    public TokoServiceAdapter(Context context,List <TokoServiceModel> results) {
        this.context = context;
        mKontakList = results;

    }



    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_service, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position){
        Context context = holder.gambarToko.getContext(); //<----- Add this line


        //change context here
        //Picasso.with(context).load(item.imageLink).into(viewHolder.postImage);

      Picasso.with(context).load(mKontakList.get(position).getFotoService()).into(holder.gambarToko);

        holder.namaToko.setText(( mKontakList.get(position).getNamaService()));
        holder.alamatToko.setText(( mKontakList.get(position).getAlamatService()));
        holder.jamBukaToko.setText((mKontakList.get(position).getWaktuBuka()));
        holder.gambarLaptop.setImageResource(R.drawable.laptophitam);
        holder.gambarPrinter.setImageResource(R.drawable.ic_print_black_24dp);

        switch (mKontakList.get(position).getDaftarService()) {
            case "2":
                holder.gambarLaptop.setVisibility(View.VISIBLE);
                holder.gambarPrinter.setVisibility(View.VISIBLE);
                break;

            case "1":
                holder.gambarLaptop.setVisibility(View.VISIBLE);
                holder.gambarPrinter.setVisibility(View.INVISIBLE);
                break;

            case "0":
                holder.gambarLaptop.setVisibility(View.INVISIBLE);
                holder.gambarPrinter.setVisibility(View.VISIBLE);

                break;

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), HalamanService.class);
                mIntent.putExtra("IdTokoService", mKontakList.get(position).getIdService());
                mIntent.putExtra("NamaTokoService", mKontakList.get(position).getNamaService());
                mIntent.putExtra("AlamatService", mKontakList.get(position).getAlamatService());
                mIntent.putExtra("FotoService", mKontakList.get(position).getFotoService());
                mIntent.putExtra("WaktuBuka", mKontakList.get(position).getWaktuBuka());
                mIntent.putExtra("Longi", mKontakList.get(position).getLongitud());
                mIntent.putExtra("Lat", mKontakList.get(position).getLatitud());
                if(mKontakList.get(position).getTotalRating() == NULL){
                    mIntent.putExtra("Rating", 1.0F);

                }
                else{
                    mIntent.putExtra("Rating", mKontakList.get(position).getTotalRating());
                }
                view.getContext().startActivity(mIntent);
            }
        });


    }
    @Override
    public int getItemCount () {


        return mKontakList.size();

    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView namaToko, jamBukaToko,alamatToko;
        public ImageView gambarToko,gambarLaptop,gambarPrinter;


        public MyViewHolder(View itemView) {
            super(itemView);
            namaToko = (TextView) itemView.findViewById(R.id.nama_toko_komputer);
            jamBukaToko = (TextView) itemView.findViewById(R.id.waktubuka);
            alamatToko = (TextView) itemView.findViewById(R.id.alamat_toko);
            gambarToko = (ImageView) itemView.findViewById(R.id.image_toko);
            gambarLaptop = (ImageView) itemView.findViewById(R.id.laptop);
            gambarPrinter = (ImageView) itemView.findViewById(R.id.printer);



        }
    }
}
