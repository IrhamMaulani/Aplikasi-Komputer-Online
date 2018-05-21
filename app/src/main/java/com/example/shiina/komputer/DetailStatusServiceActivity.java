package com.example.shiina.komputer;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shiina.komputer.Model.Notifikasi;
import com.example.shiina.komputer.Network.ApiClient;
import com.example.shiina.komputer.Network.ApiInterface;
import com.example.shiina.komputer.SharedPreference.SharedPrefManager;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailStatusServiceActivity extends AppCompatActivity {

    String idService,alamatToko,namaKerusakan,fotoService,namaToko,validasi,perubahanStatus,pesanDialog,alasanPembatalan,nama,idToko;
    Button submit,diambil ;
    int idServiceInt,idTokoInt;
    ApiInterface mApiInterface;
    ProgressDialog csprogress;
    float nilairating;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_status_service);

        SharedPrefManager sharedPrefManager = new SharedPrefManager(this);
        nama = sharedPrefManager.getSPNama();


        idService = getIntent().getStringExtra("Id");
        idToko = getIntent().getStringExtra("IdToko");
        alamatToko = getIntent().getStringExtra("Alamat");
        namaKerusakan = getIntent().getStringExtra("NamaService");
        fotoService = getIntent().getStringExtra("FotoService");
        namaToko = getIntent().getStringExtra("NamaToko");
        validasi =  getIntent().getStringExtra("Boleh");
        LinearLayout containerBatal = (LinearLayout) findViewById(R.id.container_untuk_status);
        idTokoInt = Integer.parseInt(idToko);
        idServiceInt = Integer.parseInt(idService);
        csprogress=new ProgressDialog(DetailStatusServiceActivity.this);


        Log.v("Coba","Isi dari variabel validasi : " + validasi);

        TextView txtNamaToko = (TextView) findViewById(R.id.txt_for_namatoko);
        txtNamaToko.setText(namaToko);

        TextView txtAlamatToko = (TextView) findViewById(R.id.txt_for_alamat);
        txtAlamatToko.setText(alamatToko);

        TextView txtNamaService = (TextView) findViewById(R.id.txt_for_service);
        txtNamaService.setText(namaKerusakan);

        ImageView imgFotoService = (ImageView) findViewById(R.id.foto_toko_service);

        Picasso.with(DetailStatusServiceActivity.this).load(fotoService).into(imgFotoService);

        submit = (Button) findViewById(R.id.btn_for_submit);
        diambil = (Button)findViewById(R.id.btn_for_selesai);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        TextView txtStatus = (TextView) findViewById(R.id.txt_pemberitahuan);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);

        //check box
        RadioButton checkBox1 = (RadioButton) findViewById(R.id.checkbox_1);
        RadioButton checkBox2 = (RadioButton) findViewById(R.id.checkbox_2);
        RadioButton checkBox3 = (RadioButton) findViewById(R.id.checkbox_3);
        RadioButton checkBox4 = (RadioButton) findViewById(R.id.checkbox_4);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.RGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.checkbox_1) {
                    alasanPembatalan =  "Respon Terlalu lama" ;
                } else if(checkedId == R.id.checkbox_2) {
                    alasanPembatalan =  "Komputer Normal" ;
                } else if(checkedId == R.id.checkbox_3){
                    alasanPembatalan =  "Sudah ke tempat service lain" ;
                } else if(checkedId == R.id.checkbox_4){
                    alasanPembatalan =  "Lainnya" ;
                }
            }

        });



        //end check box



        if(validasi.equals("0")){
            containerBatal.setVisibility(View.GONE);
            txtStatus.setVisibility(View.VISIBLE);
            submit.setVisibility(View.GONE);
            alasanPembatalan = "";

        }
        else if(validasi.equals("2")){
            containerBatal.setVisibility(View.GONE);
            txtStatus.setVisibility(View.VISIBLE);
            ratingBar.setVisibility(View.VISIBLE);
            txtStatus.setText("Barang telah selesai,Klik tombol di bawah untuk konfirmasi telah diambil Dan berikan rating (wajib)");
            txtStatus.setTextSize(10);
            submit.setText("Diambil");
            perubahanStatus = "DIAMBIL";
            pesanDialog = "Anda telah mengambil barang anda?";
            alasanPembatalan = "";
            diambil.setVisibility(View.VISIBLE);


        }
        else{
            perubahanStatus = "DIBATALKAN";
            pesanDialog = "Apakah Anda ingin Membatalkan Pesanan Ini?";

        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(DetailStatusServiceActivity.this);
                alert.setCancelable(true);
                alert.setMessage(pesanDialog);
                alert.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //isi

                                csprogress.setMessage("Loading...");
                                csprogress.show();
                                new Handler().postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
                                        csprogress.dismiss();
//whatever you want just you have to launch overhere.

                                        sendData();


                                    }
                                }, 1000);

                            }

                        });
                alert.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(HalamanService.this, "SA :(", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();

            }
        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

               // Toast.makeText(getApplicationContext(),"Rating : " + String.valueOf(rating),Toast.LENGTH_LONG).show();
                nilairating = rating;


            }
        });

        diambil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(DetailStatusServiceActivity.this);
                alert.setCancelable(true);
                alert.setMessage(pesanDialog);
                alert.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //isi

                                csprogress.setMessage("Loading...");
                                csprogress.show();
                                new Handler().postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
                                        csprogress.dismiss();
//whatever you want just you have to launch overhere.

                                        sendRatingSelesai();


                                    }
                                }, 1000);

                            }

                        });
                alert.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(HalamanService.this, "SA :(", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();

            }
        });






    }

    public void sendData(){
        Call<Notifikasi> call = mApiInterface.UpdateStatusTransaksi(idServiceInt,perubahanStatus,alasanPembatalan);

        call.enqueue(new Callback<Notifikasi>() {
            @Override
            public void onResponse(Call<Notifikasi> call, Response<Notifikasi> response) {
                Toast.makeText(DetailStatusServiceActivity.this, "" + response.body().getResponseServer(), Toast.LENGTH_SHORT).show();

                finish();



            }

            @Override
            public void onFailure(Call<Notifikasi> call, Throwable t) {

                Toast.makeText(DetailStatusServiceActivity.this, "Terjadi Gangguan Silahkan coba lagi", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void sendRatingSelesai(){
        Call<Notifikasi> call = mApiInterface.masukanRating(nilairating,idTokoInt,nama,idServiceInt,perubahanStatus,alasanPembatalan);

        Log.v("cobalagi","Isi nilai rating" + nilairating);
        Log.v("cobalagi","Isi nilai idtoko" + idTokoInt);
        Log.v("cobalagi","Isi nilai nama" + nama);

        call.enqueue(new Callback<Notifikasi>() {
            @Override
            public void onResponse(Call<Notifikasi> call, Response<Notifikasi> response) {
                Toast.makeText(DetailStatusServiceActivity.this, "" + response.body().getResponseServer(), Toast.LENGTH_SHORT).show();

                finish();



            }

            @Override
            public void onFailure(Call<Notifikasi> call, Throwable t) {

                Toast.makeText(DetailStatusServiceActivity.this, "Terjadi Gangguan Silahkan coba lagi", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
