package com.example.shiina.komputer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokoServiceModel {

    @SerializedName("idService")
    @Expose
    private String idService;
    @SerializedName("Nama_Service")
    @Expose
    private String namaService;
    @SerializedName("Alamat_Service")
    @Expose
    private String alamatService;
    @SerializedName("Foto_Service")
    @Expose
    private String fotoService;
    @SerializedName("Total_Rating")
    @Expose
    private float totalRating;
    @SerializedName("Daftar_Service")
    @Expose
    private String daftarService;
    @SerializedName("Username_SA")
    @Expose
    private String usernameSA;
    @SerializedName("waktuBuka")
    @Expose
    private String waktuBuka;
    @SerializedName("longitud")
    @Expose
    private double longitud;
    @SerializedName("latitud")
    @Expose
    private double latitud;

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getNamaService() {
        return namaService;
    }

    public void setNamaService(String namaService) {
        this.namaService = namaService;
    }

    public String getAlamatService() {
        return alamatService;
    }

    public void setAlamatService(String alamatService) {
        this.alamatService = alamatService;
    }

    public String getFotoService() {
        return "http://pemrograman-web.ti.ulm.ac.id/Kelompok13/pages/tables_toko/upload/"+ fotoService;
    }

    public void setFotoService(String fotoService) {
        this.fotoService = fotoService;
    }


    public float getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(float totalRating) {
        this.totalRating = totalRating;
    }

    public String getDaftarService() {
        return daftarService;
    }

    public void setDaftarService(String daftarService) {
        this.daftarService = daftarService;
    }

    public String getUsernameSA() {
        return usernameSA;
    }

    public void setUsernameSA(String usernameSA) {
        this.usernameSA = usernameSA;
    }

    public String getWaktuBuka() {
        return waktuBuka;
    }

    public void setWaktuBuka(String waktuBuka) {
        this.waktuBuka = waktuBuka;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
}
