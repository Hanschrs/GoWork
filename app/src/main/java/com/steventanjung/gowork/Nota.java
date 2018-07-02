package com.steventanjung.gowork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by StevenTanjung on 10/24/2017.
 */

public class Nota {
    private String tanggal;
    private ArrayList<Belanjaan> belanjaan;
    private int id, no_nota, diskon, total_harga;

    public Nota(String tanggal, ArrayList<Belanjaan> belanjaan, int id, int no_nota, int diskon, int total_harga) {
        this.tanggal = tanggal;
        this.belanjaan = belanjaan;
        this.id = id;
        this.no_nota = no_nota;
        this.diskon = diskon;
        this.total_harga = total_harga;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNo_nota() {
        return no_nota;
    }

    public void setNo_nota(int no_nota) {
        this.no_nota = no_nota;
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
