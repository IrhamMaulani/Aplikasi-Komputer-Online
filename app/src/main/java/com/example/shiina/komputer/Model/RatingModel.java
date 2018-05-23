package com.example.shiina.komputer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RatingModel {

    @SerializedName("idRating")
    @Expose
    private String idRating;


    @SerializedName("idService")
    @Expose
    private String idService;

    @SerializedName("totalRating")
    @Expose
    private float totalRating;

    @SerializedName("Username")
    @Expose
    private String username;

    @SerializedName("response")
    @Expose
    private String responseServer;

    @SerializedName("userRating")
    @Expose
    private float userRating;

    @SerializedName("idTransaksi")
    @Expose
    private int idTransaksi;

    public void setUserRating(float userRating) {
        this.userRating = userRating;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getResponseServer() {
        return responseServer;
    }

    public void setResponseServer(String responseServer) {
        this.responseServer = responseServer;
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getIdRating() {
        return idRating;
    }

    public void setIdRating(String idRating) {
        this.idRating = idRating;
    }

    public float getUserRating() {
        return userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public float getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(float totalRating) {
        this.totalRating = totalRating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
