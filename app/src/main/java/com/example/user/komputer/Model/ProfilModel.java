package com.example.user.komputer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfilModel {

    @SerializedName("username")
    @Expose
    private String userName;

    @SerializedName("password")
    @Expose
    private String kataSandi;

    @SerializedName("response")
    @Expose
    private String responseServer;

    @SerializedName("Nama_User")
    @Expose
    private String namaUser;

    @SerializedName("Nomor_Hape")
    @Expose
    private String namaTelpon;

    @SerializedName("Alamat_User")
    @Expose
    private String alamatUser;

    @SerializedName("Email")
    @Expose
    private String email;



    public ProfilModel(String namaUser, String namaTelpon) {
        this.namaUser = namaUser;
        this.namaTelpon = namaTelpon;
    }

    public ProfilModel() {

    }

    public String getEmail() {
        return email;
    }
    public String getResponseServer() {
        return responseServer;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public String getNamaTelpon() {
        return namaTelpon;
    }

    public String getAlamatUser() {
        return alamatUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public void setNamaTelpon(String namaTelpon) {
        this.namaTelpon = namaTelpon;
    }
}
