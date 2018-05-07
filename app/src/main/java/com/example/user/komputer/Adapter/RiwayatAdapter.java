package com.example.user.komputer.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.komputer.Model.Notifikasi;
import com.example.user.komputer.R;

import java.util.List;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.MyViewHolder>  {

    private List<Notifikasi> mKontakList;

    public RiwayatAdapter(List <Notifikasi> KontakList) {
        mKontakList = KontakList;
    }

    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_riwayat_service, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position){
        holder.mTextViewId.setText(( mKontakList.get(position).getKerusakan()));
        holder.mTextViewNama.setText(( mKontakList.get(position).getNamaToko()));
        holder.mTextViewTanggal.setText(( mKontakList.get(position).getTanggalMasuk()));

    }



    @Override
    public int getItemCount () {

        return mKontakList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId, mTextViewNama,mTextViewTanggal;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId = (TextView) itemView.findViewById(R.id.kerusakan);
            mTextViewNama = (TextView) itemView.findViewById(R.id.toko);
            mTextViewTanggal = (TextView) itemView.findViewById(R.id.tanggal);
        }
    }

}
