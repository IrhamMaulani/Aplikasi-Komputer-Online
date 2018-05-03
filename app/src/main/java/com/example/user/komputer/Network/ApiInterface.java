package com.example.user.komputer.Network;

import com.example.user.komputer.Model.TokoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/ServiceComputer/pages/notification/viewService.php")
    Call<List<TokoService>> getRatingKomputer();





}
