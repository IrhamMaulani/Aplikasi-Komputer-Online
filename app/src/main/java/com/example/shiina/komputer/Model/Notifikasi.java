package com.example.shiina.komputer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notifikasi {

    @SerializedName("idTransaksi")
    @Expose
    private String idTransaksi;
    @SerializedName("Tanggal_Masuk")
    @Expose
    private String tanggalMasuk;
    @SerializedName("Status_Service")
    @Expose
    private String statusService;
    @SerializedName("idService")
    @Expose
    private String idService;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("Status_Transaksi")
    @Expose
    private String statusTransaksi;

    @SerializedName("Kerusakan")
    @Expose
    private String kerusakan;

    @SerializedName("Nama_Service")
    @Expose
    private String namaToko;

    public String getNamaToko() {
        return namaToko;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(String tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public String getStatusService() {
        return statusService;
    }

    public void setStatusService(String statusService) {
        this.statusService = statusService;
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatusTransaksi() {
        return statusTransaksi;
    }

    public void setStatusTransaksi(String statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
    }


    public String getKerusakan() {
        return kerusakan;
    }

    public void setKerusakan(String kerusakan) {
        this.kerusakan = kerusakan;
    }
}
