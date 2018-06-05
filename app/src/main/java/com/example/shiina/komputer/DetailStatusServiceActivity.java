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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shiina.komputer.Model.Notifikasi;
import com.example.shiina.komputer.Model.Rating;
import com.example.shiina.komputer.Model.RatingModel;
import com.example.shiina.komputer.Network.ApiClient;
import com.example.shiina.komputer.Network.ApiInterface;
import com.example.shiina.komputer.SharedPreference.SharedPrefManager;
import com.squareup.picasso.Picasso;


import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailStatusServiceActivity extends AppCompatActivity {

    String idService,alamatToko,namaKerusakan,fotoService,namaToko,validasi,perubahanStatus,pesanDialog,alasanPembatalan,nama,idToko,pembatalanString,prosesPengiriman,pesanOngkir;
    String statusPengiriman,totalHarga;
    Button submit,diambil ,deliveryButton;
    float ratingDouble;
    int idServiceInt,idTokoInt;
    ApiInterface mApiInterface;
    ProgressDialog csprogress;
    float nilairating,totalRating;
    RatingBar ratingBar;
    int nilaiRatingInt;
    double hargaOngkir;
    EditText keluhanText;
    RadioButton radioButton1,radioButton2,radioButton3,radioButton4;

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
        pembatalanString =  getIntent().getStringExtra("AlasanPembatalan");
        totalRating = getIntent().getFloatExtra("Rating",1F);
        totalHarga = getIntent().getStringExtra("totalHarga");
        String detailProsesPengiriman = getIntent().getStringExtra("prosesPengiriman");
        Log.v("isi","isi dari totalHarga"+ totalHarga);
        statusPengiriman = getIntent().getStringExtra("pengiriman");
        LinearLayout containerBatal = (LinearLayout) findViewById(R.id.container_untuk_status);
        idTokoInt = Integer.parseInt(idToko);
        idServiceInt = Integer.parseInt(idService);
        csprogress=new ProgressDialog(DetailStatusServiceActivity.this);

        TextView txtNamaToko = (TextView) findViewById(R.id.txt_for_namatoko);
        txtNamaToko.setText(namaToko);

        TextView txtAlamatToko = (TextView) findViewById(R.id.txt_for_alamat);
        txtAlamatToko.setText(alamatToko);

        TextView txtNamaService = (TextView) findViewById(R.id.txt_for_service);
        txtNamaService.setText(namaKerusakan);


        final TextView txtHargaOngkir = (TextView) findViewById(R.id.text_harga_ongkir);



        keluhanText = findViewById(R.id.alasan_txt);
        alasanPembatalan = keluhanText.getText().toString();


        ImageView imgFotoService = (ImageView) findViewById(R.id.foto_toko_service);
        Log.v("coba","isi dari foto " +fotoService);

        Picasso.with(DetailStatusServiceActivity.this).load(fotoService).into(imgFotoService);

        submit = (Button) findViewById(R.id.btn_for_submit);
        diambil = (Button)findViewById(R.id.btn_for_selesai);
        deliveryButton = (Button)findViewById(R.id.btn_for_delivery);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        TextView txtStatus = (TextView) findViewById(R.id.txt_pemberitahuan);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);

        TextView txtHarga = (TextView) findViewById(R.id.txt_for_harga);
        txtHarga.setText("Total Harga yang anda Pesan = Rp."+totalHarga);

        TextView txtKeterangan = (TextView) findViewById(R.id.txt_pemberitahuan);
        txtKeterangan.setText("PESANAN ANDA SEDANG DI PROSES");

        TextView txtKeteranganRadio = (TextView) findViewById(R.id.txt_keterangan_radio);


        radioButton1 = findViewById(R.id.checkbox_1);
        radioButton2 = findViewById(R.id.checkbox_2);
        radioButton3 = findViewById(R.id.checkbox_3);
        radioButton4 = findViewById(R.id.checkbox_4);


        keluhanText = findViewById(R.id.alasan_txt);

        keluhanText.setVisibility(View.GONE);



        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.RGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.checkbox_1) {
                    alasanPembatalan =  "Respon Terlalu lama" ;
                    keluhanText.setVisibility(View.GONE);

                } else if(checkedId == R.id.checkbox_2) {
                    alasanPembatalan =  "Komputer Normal" ;
                    keluhanText.setVisibility(View.GONE);

                } else if(checkedId == R.id.checkbox_3){
                    alasanPembatalan =  "Sudah ke tempat service lain" ;
                    keluhanText.setVisibility(View.GONE);

                } else if(checkedId == R.id.checkbox_4){
                    keluhanText.setVisibility(View.VISIBLE);


                }
            }

        });


        Log.v("tag isi","isi dari alasan pembatalan" + alasanPembatalan);


        if(validasi.equals("0")){
            //containerBatal.setVisibility(View.GONE);
            radioButton3.setVisibility(View.GONE);
            radioButton4.setVisibility(View.GONE);
            txtKeteranganRadio.setText(" Pilih Opsi Pengiriman");
            radioButton1.setText("Ambil Sendiri");
            radioButton2.setText("Delivery");
            txtStatus.setVisibility(View.VISIBLE);
            submit.setVisibility(View.GONE);
            alasanPembatalan = "";
            deliveryButton.setVisibility(View.VISIBLE);
            pesanOngkir = "Anda akan Mengambil sendiri?";

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // find which radio button is selected
                    if (checkedId == R.id.checkbox_1) {
                        txtHargaOngkir.setVisibility(View.GONE);
                        pesanOngkir = "Anda akan Mengambil sendiri?";

                    } else if (checkedId == R.id.checkbox_2) {
                        txtHargaOngkir.setVisibility(View.VISIBLE);
                        pesanOngkir = "Anda Akan di kenai biaya tambahan sebesar Rp.30.000";
                    }
                }
            });

            if(statusPengiriman.equals("kosong")){
            }
            else{
                containerBatal.setVisibility(View.GONE);
                deliveryButton.setVisibility(View.GONE);
                String txtBuruBuru = "PESANAN ANDA SEDANG DI PROSES \n" + detailProsesPengiriman;
                txtKeterangan.setText(txtBuruBuru);
            }

        }
        else if(validasi.equals("2")){
            containerBatal.setVisibility(View.GONE);
            txtStatus.setVisibility(View.VISIBLE);
            ratingBar.setVisibility(View.VISIBLE);
            txtStatus.setText("Barang telah selesai,Klik tombol di bawah untuk konfirmasi telah diambil Dan berikan rating ");
            txtStatus.setTextSize(10);
           // submit.setText("Diambil");
            submit.setVisibility(View.GONE);
            perubahanStatus = "DIAMBIL";
            pesanDialog = "Anda telah mengambil barang anda?";
            alasanPembatalan = "";
            diambil.setVisibility(View.VISIBLE);


        }
        else if(validasi.equals("1")){
            perubahanStatus = "DIBATALKAN";
            pesanDialog = "Apakah Anda ingin Membatalkan Pesanan Ini?";
          //  alasanPembatalan = keluhanText.getText().toString();

        }
        else if(validasi.equals("3")){
            containerBatal.setVisibility(View.GONE);
            txtStatus.setVisibility(View.VISIBLE);
            txtStatus.setText("Anda Memberikan rating");
            submit.setVisibility(View.GONE);
            ratingBar.setVisibility(View.VISIBLE);
            getRating();
           // ratingBar.setRating(totalRating);
            ratingBar.setIsIndicator(true);

        }
        else if(validasi.equals("4")){
            containerBatal.setVisibility(View.GONE);
            txtStatus.setVisibility(View.VISIBLE);
            txtStatus.setText("Alasan Pembatalan : " + pembatalanString);
            submit.setVisibility(View.GONE);

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
                                        if(radioButton4.isChecked()){
                                            alasanPembatalan = keluhanText.getText().toString();
                                        }

                                        Log.v("tag isi","isi dari alasan pembatalan" + alasanPembatalan);
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
                nilaiRatingInt = (int) nilairating;


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


        deliveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(DetailStatusServiceActivity.this);
                alert.setCancelable(true);
                alert.setMessage(pesanOngkir);
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

                                       if(radioButton1.isChecked()){
                                           prosesPengiriman = "ambil sendiri";
                                           hargaOngkir = 0;

                                       }
                                       else if(radioButton2.isChecked()){
                                           prosesPengiriman = "Delivery";
                                           hargaOngkir = 30000;

                                       }
                                       sendOngkir();


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

    public void getRating (){
        Call<Rating> call = mApiInterface.getNilaiKomputer(idServiceInt);
        call.enqueue(new Callback<Rating>() {
            @Override
            public void onResponse(Call<Rating> call, Response<Rating> response) {
                //progressDialog.dismiss();



                Rating p = response.body();


                String RatingString = null;
                if (p != null) {
                    RatingString = p.getUserRating();
                }
                else{
                    RatingString = "1";
                }
                float ratingDouble = Float.valueOf(RatingString);

                Log.v("Tag","isi dari response body " + response.body());
                Log.v("Tag","isi dari idtransaksi tertutp" + idServiceInt);
                Log.v("Tag","isi dari Rating tertutup" + RatingString);



                ratingBar.setRating(ratingDouble);



            }

            @Override
            public void onFailure(Call<Rating> call, Throwable t) {
                // progressDialog.dismiss();
                Toast.makeText(DetailStatusServiceActivity.this, "Failed to load", Toast.LENGTH_LONG).show();

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
        Call<RatingModel> call = mApiInterface.masukanRating(nilairating,idTokoInt,nama,idServiceInt,perubahanStatus," ");

        Log.v("cobalagi","Isi nilai rating" + nilaiRatingInt);
        Log.v("cobalagi","Isi nilai idtoko" + idTokoInt);
        Log.v("cobalagi","Isi nilai nama" + nama);
        Log.v("cobalagi","Isi nilai idservice" + idServiceInt);
        Log.v("cobalagi","Isi nilai perubahan" + perubahanStatus);
        Log.v("cobalagi","Isi nilai alasan" + alasanPembatalan);


        call.enqueue(new Callback<RatingModel>() {
            @Override
            public void onResponse(Call<RatingModel> call, Response<RatingModel> response) {
                Toast.makeText(DetailStatusServiceActivity.this, "" + response.body().getResponseServer(), Toast.LENGTH_SHORT).show();

                finish();



            }

            @Override
            public void onFailure(Call<RatingModel> call, Throwable t) {

                Toast.makeText(DetailStatusServiceActivity.this, "Terjadi Gangguan Silahkan coba lagi", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void sendOngkir(){
        Call<Notifikasi> call = mApiInterface.updateProsesPengiriman(idServiceInt,prosesPengiriman,hargaOngkir);
        Log.v("isi","isi dari idservice" +idServiceInt);
        Log.v("isi","isi dari proses Pengiriman" +prosesPengiriman);
        Log.v("isi","isi dari idservice" +hargaOngkir);

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
