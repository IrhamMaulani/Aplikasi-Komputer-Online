package com.example.shiina.komputer;

public class Service {

    private String namaToko;
    private String jamBuka;
    private String alamatToko;
    private int kodePerbaikan;
    private double latitude;
    private double longitude;
    private int gambarToko = GAMBAR_TIDAK_ADA;
    private static final int GAMBAR_TIDAK_ADA = -1;

    private String waktuBuka;



    public Service(String namaToko , String jamBuka , String alamatToko , int kodePerbaikan ,double latitude , double longitude ,String waktuBuka){

        this.namaToko = namaToko;
        this.jamBuka = jamBuka;
        this.alamatToko = alamatToko;
        this.kodePerbaikan = kodePerbaikan;
        this.latitude = latitude;
        this.longitude = longitude;
        this.waktuBuka = waktuBuka;

    }
    public Service(String namaToko , String jamBuka , String alamatToko , int kodePerbaikan  ){

        this.namaToko = namaToko;
        this.jamBuka = jamBuka;
        this.alamatToko = alamatToko;
        this.kodePerbaikan = kodePerbaikan;

    }

    public Service(String namaToko , String jamBuka , String alamatToko  ){

        this.namaToko = namaToko;
        this.jamBuka = jamBuka;
        this.alamatToko = alamatToko;


    }

    public String getNamaToko() {
        return namaToko;
    }

    public String getJamBuka() {
        return jamBuka;
    }



    public String getAlamatToko() {
        return alamatToko;
    }

    public int getKodePerbaikan() {
        return kodePerbaikan;

    }

    public String getWaktuBuka() {
        return waktuBuka;
    }

    public int getGambarToko() {
        return gambarToko;
    }

    public double getLatitude (){
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
