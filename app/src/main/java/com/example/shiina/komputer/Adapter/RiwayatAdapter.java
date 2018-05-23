package com.example.shiina.komputer.Adapter;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shiina.komputer.DetailStatusServiceActivity;
import com.example.shiina.komputer.Model.Notifikasi;
import com.example.shiina.komputer.R;

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
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        holder.mTextViewId.setText((mKontakList.get(position).getKerusakan()));
        holder.mTextViewNama.setText((mKontakList.get(position).getNamaToko()));
        holder.mTextViewTanggal.setText((mKontakList.get(position).getTanggalMasuk()));

        switch (mKontakList.get(position).getStatusTransaksi()) {


            case "DIBATALKAN":
                holder.gambarStatus.setImageResource(R.drawable.icon_batal_cross);
                //imageView.setBackgroundColor(ContextCompat.getColor(context, R.color.kuningKartu));
                //holder.gambarStatus.setBackgroundColor(Color.parseColor("#FFFFFF"));
                holder.gambarStatus.setBackgroundColor(Color.parseColor("#ffcc0000"));

                break;

            case "DIAMBIL":
                holder.gambarStatus.setImageResource(R.drawable.ic_diambil);
                //imageView.setBackgroundColor(ContextCompat.getColor(context, R.color.kuningKartu));
                //holder.gambarStatus.setBackgroundColor(Color.parseColor("#FFFFFF"));
                holder.gambarStatus.setBackgroundColor(Color.parseColor("#ff33b5e5"));
                break;


            default:

                break;


        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), DetailStatusServiceActivity.class);
                mIntent.putExtra("Id", mKontakList.get(position).getIdTransaksi());
                mIntent.putExtra("IdToko", mKontakList.get(position).getIdService());
                mIntent.putExtra("NamaToko", mKontakList.get(position).getNamaToko());
                mIntent.putExtra("Alamat", mKontakList.get(position).getAlamatToko());
                mIntent.putExtra("NamaService", mKontakList.get(position).getKerusakan());
                mIntent.putExtra("FotoService", mKontakList.get(position).getFotoToko());

                if(mKontakList.get(position).getStatusTransaksi().equals("DIAMBIL")){

                    mIntent.putExtra("Boleh", "3");
                    mIntent.putExtra("Rating", mKontakList.get(position).getUserRating());
                }
                else if(mKontakList.get(position).getStatusTransaksi().equals("DIBATALKAN")){
                    mIntent.putExtra("Boleh", "4");
                    mIntent.putExtra("AlasanPembatalan", mKontakList.get(position).getAlasanPembatalan());
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
        public TextView mTextViewId, mTextViewNama,mTextViewTanggal;
        public ImageView gambarStatus;
        public CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId = (TextView) itemView.findViewById(R.id.kerusakan);
            mTextViewNama = (TextView) itemView.findViewById(R.id.toko);
            mTextViewTanggal = (TextView) itemView.findViewById(R.id.tanggal);
            gambarStatus = (ImageView) itemView.findViewById(R.id.gambar_status);
            cardView = (CardView) itemView.findViewById(R.id.list_service);
        }
    }

}
