package com.example.user.komputer.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Riwayat {
    @SerializedName("result")
    List<Notifikasi> listDataNotifikasi;

    public List<Notifikasi> getListDataNotifikasi() {
        return listDataNotifikasi;
    }

    public void setListDataNotifikasi(List<Notifikasi> listDataNotifikasi) {
        this.listDataNotifikasi = listDataNotifikasi;
    }
}
