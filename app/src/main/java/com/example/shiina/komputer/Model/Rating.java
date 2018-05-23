package com.example.shiina.komputer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("idRating")
    @Expose
    private int idRating;
    @SerializedName("userRating")
    @Expose
    private String userRating;
    @SerializedName("idService")
    @Expose
    private String idService;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("idTransaksi")
    @Expose
    private int idTransaksi;
    @SerializedName("response")
    @Expose
    private String responseServer;

    public String getResponseServer() {
        return responseServer;
    }

    public void setResponseServer(String responseServer) {
        this.responseServer = responseServer;
    }

    public int getIdRating() {
        return idRating;
    }

    public void setIdRating(int idRating) {
        this.idRating = idRating;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
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

    public int getIdTransaksi() {
        return idTransaksi;
    }



    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }
}
