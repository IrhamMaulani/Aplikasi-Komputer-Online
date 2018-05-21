package com.example.shiina.komputer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokoService {

    @SerializedName("idService")
    @Expose
    private String idService;
    @SerializedName("Nama_Service")
    @Expose
    private String namaService;
    @SerializedName("Alamat_Service")
    @Expose
    private String alamatService;
    @SerializedName("Koordinat_Service")
    @Expose
    private String koordinatService;
    @SerializedName("Foto_Service")
    @Expose
    private String fotoService;

    @SerializedName("Daftar_Service")
    @Expose
    private int daftarService;
    @SerializedName("Username_SA")
    @Expose
    private String usernameSA;
    @SerializedName("longitud")
    @Expose
    private double longitud;

    @SerializedName("waktuBuka")
    @Expose
    private String waktuBuka;

    public String getWaktuBuka() {
        return waktuBuka;
    }

    public void setWaktuBuka(String waktuBuka) {
        this.waktuBuka = waktuBuka;
    }

    public double getLongitud() {
        return longitud;
    }

    public double getLatitud() {
        return latitud;
    }

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

    public String getKoordinatService() {
        return koordinatService;
    }

    public void setKoordinatService(String koordinatService) {
        this.koordinatService = koordinatService;
    }

    public String getFotoService() {
        return "http://pemrograman-web.ti.ulm.ac.id/Kelompok13/pages/tables_toko/upload/" + fotoService;
    }

    public void setFotoService(String fotoService) {
        this.fotoService = fotoService;
    }


    public int getDaftarService() {
        return daftarService;
    }



    public String getUsernameSA() {
        return usernameSA;
    }

    public void setUsernameSA(String usernameSA) {
        this.usernameSA = usernameSA;
    }

}

