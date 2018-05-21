package com.example.shiina.komputer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RatingModel {

    @SerializedName("idRating")
    @Expose
    private String idRating;

    @SerializedName("userRating")
    @Expose
    private String userRating;

    @SerializedName("idService")
    @Expose
    private String idService;

    @SerializedName("totalRating")
    @Expose
    private float totalRating;

    @SerializedName("response")
    @Expose
    private String responseServer;

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

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public float getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(float totalRating) {
        this.totalRating = totalRating;
    }
}
