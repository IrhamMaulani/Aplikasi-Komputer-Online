package com.example.user.komputer.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.komputer.Model.Notifikasi;
import com.example.user.komputer.R;

import java.util.ArrayList;
import java.util.List;

public class NotifikasiRecyclerAdapter extends RecyclerView.Adapter<NotifikasiRecyclerAdapter.MyViewHolder>  {

    private List<Notifikasi> mKontakList;
    private Context context;

    public NotifikasiRecyclerAdapter(List <Notifikasi> KontakList) {
        mKontakList = KontakList;
    }

    public NotifikasiRecyclerAdapter(Activity context) {

    }

    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_status, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position){
        holder.mTextViewStatusTransaksi.setText(( mKontakList.get(position).getStatusTransaksi()));
        holder.mTextViewNamaToko.setText(( mKontakList.get(position).getNamaToko()));
        holder.mTextViewKerusakan.setText((mKontakList.get(position).getKerusakan()));


        switch (mKontakList.get(position).getStatusTransaksi()) {
            case "Menunggu":
                holder.gambarStatus.setImageResource(R.drawable.ic_help);
               //holder.gambarStatus.setBackgroundColor(ContextCompat.getColor(context, R.color.green));
                //holder.gambarStatus.setBackgroundColor.context.getResources().getColor(R.color.green);
                holder.gambarStatus.setBackgroundColor(Color.parseColor("#3F51B5"));
                holder.mTextViewStatusTransaksi.setTextColor(Color.parseColor("#3F51B5"));

                break;
            case "ON-PROSES":
                holder.gambarStatus.setImageResource(R.drawable.notifikasi_putih);
                //imageView.setBackgroundColor(ContextCompat.getColor(context, R.color.kuningKartu));
                //holder.gambarStatus.setBackgroundColor(Color.parseColor("#FFFFFF"));
                holder.gambarStatus.setBackgroundColor(Color.parseColor("#FFEB3B"));
                holder.mTextViewStatusTransaksi.setTextColor(Color.parseColor("#FF9800"));
                break;

            case "DIBATALKAN":
                holder.gambarStatus.setImageResource(R.drawable.icon_batal_cross);
                //imageView.setBackgroundColor(ContextCompat.getColor(context, R.color.kuningKartu));
                //holder.gambarStatus.setBackgroundColor(Color.parseColor("#FFFFFF"));
                holder.gambarStatus.setBackgroundColor(Color.parseColor("#B71C1C"));
                holder.mTextViewStatusTransaksi.setTextColor(Color.parseColor("#B71C1C"));
                break;

            case "SELESAI":
                holder.gambarStatus.setImageResource(R.drawable.ic_check);
                //imageView.setBackgroundColor(ContextCompat.getColor(context, R.color.kuningKartu));
                //holder.gambarStatus.setBackgroundColor(Color.parseColor("#FFFFFF"));
                holder.gambarStatus.setBackgroundColor(Color.parseColor("#00bfa5"));
                holder.mTextViewStatusTransaksi.setTextColor(Color.parseColor("#00bfa5"));
                break;



            default:

                break;

        }

    }



    @Override
    public int getItemCount () {


        return mKontakList.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewStatusTransaksi, mTextViewNamaToko,mTextViewTanggal,mTextViewKerusakan;
        public ImageView gambarStatus;
        public CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewStatusTransaksi = (TextView) itemView.findViewById(R.id.statusproses);
            mTextViewNamaToko = (TextView) itemView.findViewById(R.id.toko_komputer);
            gambarStatus = (ImageView) itemView.findViewById(R.id.gambar_status);
             cardView = (CardView) itemView.findViewById(R.id.list_service);
            mTextViewKerusakan = (TextView) itemView.findViewById(R.id.kerusakan_info);

        }
    }
}
