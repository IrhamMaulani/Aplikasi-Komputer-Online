package com.example.shiina.komputer.Network;

import com.example.shiina.komputer.Model.DaftarService;
import com.example.shiina.komputer.Model.ProfilModel;
import com.example.shiina.komputer.Model.Riwayat;
import com.example.shiina.komputer.Model.TokoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/Kelompok13/pages/notification/viewService.php")
   // @GET("servicekomputer/pages/notification/viewService.php")
    Call<List<TokoService>> getRatingKomputer();

    @GET("/Kelompok13/pages/notification/viewdaftarservice.php")
        // @GET("servicekomputer/pages/notification/viewService.php")
    Call<List<DaftarService>> getDaftarService();

    @FormUrlEncoded
    @POST("/Kelompok13/pages/user/signin.php")
    Call<ProfilModel> postLogin(@Field("username") String userName,
                               @Field("password") String kataSandi);


    @GET("/Kelompok13/pages/notification/viewProfil.php")
    Call<ProfilModel> getProfile(@Query("username") String userName);


    @FormUrlEncoded
    @POST("/Kelompok13/pages/notification/editProfil.php")
    Call<ProfilModel> updateProfil(@Field("userName") String userName,
                                 @Field("Nama_User") String namaUser,
                            @Field("Nomor_Hape") String nomorTelepon,
                            @Field("alamat_User") String alamatUser);

    @FormUrlEncoded
    @POST("/Kelompok13/pages/notification/orderService.php")
    Call<ProfilModel> orderService(@Field("Username") String userName,
                                   @Field("idService") int idService,
                                   @Field("Kerusakan") String kerusakan);

    @GET("/Kelompok13/pages/notification/viewpemberitahuan.php")
        // @GET("servicekomputer/pages/notification/viewService.php")
    Call<Riwayat> getNotifikasi(@Query("Username") String userName);

    @GET("/Kelompok13/pages/notification/viewRiwayat.php")
        // @GET("servicekomputer/pages/notification/viewService.php")
    Call<Riwayat> getRiwayat(@Query("Username") String userName,
                             @Query("statusTransaksi") String statusTransaksi);

    @FormUrlEncoded
    @POST("/Kelompok13/pages/user/registrasi.php")
    Call<ProfilModel> registrasiUser(@Field("Username") String userName,
                                   @Field("Password") String kataSandi,
                                   @Field("Email") String email);


}
