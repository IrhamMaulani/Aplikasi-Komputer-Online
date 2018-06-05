package com.example.shiina.komputer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DaftarService {

    @SerializedName("id_nama_service")
    @Expose
    private String idNamaService;

    @SerializedName("nama_service")
    @Expose
    private String namaService;

    @SerializedName("harga_service")
    @Expose
    private double hargaService;

    public String getIdNamaService() {
        return idNamaService;
    }

    public String getNamaService() {
        return namaService;
    }

    public double getHargaService() {
        return hargaService;
    }

    public void setIdNamaService(String idNamaService) {
        this.idNamaService = idNamaService;
    }

    public void setNamaService(String namaService) {
        this.namaService = namaService;
    }

    public void setHargaService(double hargaService) {
        this.hargaService = hargaService;
    }
}
