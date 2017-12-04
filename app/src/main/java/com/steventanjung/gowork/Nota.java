package com.steventanjung.gowork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by StevenTanjung on 10/24/2017.
 */

public class Nota {
    private String id, tanggal;
    private ArrayList<Belanjaan> belanjaan;
    private int diskon, total_harga;

    public Nota(String id, String tanggal, ArrayList<Belanjaan> belanjaan, int diskon, int total_harga) {
        this.id = id;
        this.tanggal = tanggal;
        this.belanjaan = belanjaan;
        this.diskon = diskon;
        this.total_harga = total_harga;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public ArrayList<Belanjaan> getBelanjaan() {
        return belanjaan;
    }

    public void setBelanjaan(ArrayList<Belanjaan> belanjaan) {
        this.belanjaan = belanjaan;
    }

    public int getDiskon() {
        return diskon;
    }

    public void setDiskon(int diskon) {
        this.diskon = diskon;
    }

    public int getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(int total_harga) {
        this.total_harga = total_harga;
    }
}
