package com.example.shiina.komputer.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Riwayat {
    @SerializedName("result")
    List<Notifikasi> listDataNotifikasi;

    @SerializedName("value")
    private String value;

    public String getValue() {
        return value;
    }

    public List<Notifikasi> getListDataNotifikasi() {
        return listDataNotifikasi;
    }

    public void setListDataNotifikasi(List<Notifikasi> listDataNotifikasi) {
        this.listDataNotifikasi = listDataNotifikasi;
    }
}
