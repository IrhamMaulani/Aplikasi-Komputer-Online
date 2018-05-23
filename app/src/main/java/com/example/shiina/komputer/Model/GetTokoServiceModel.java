package com.example.shiina.komputer.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetTokoServiceModel {

    @SerializedName("result")
    List<TokoServiceModel> listDataNotifikasi;

    @SerializedName("value")
    private String value;

    public String getValue() {
        return value;
    }

    public List<TokoServiceModel> getListDataNotifikasi() {
        return listDataNotifikasi;
    }

    public void setListDataNotifikasi(List<TokoServiceModel> listDataNotifikasi) {
        this.listDataNotifikasi = listDataNotifikasi;
    }
}
