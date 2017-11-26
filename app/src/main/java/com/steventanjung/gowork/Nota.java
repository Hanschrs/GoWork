package com.steventanjung.gowork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by StevenTanjung on 10/24/2017.
 */

public class Nota {
    private String id;
    private String tanggal;
    private ArrayList<Barang> barang;

    public Nota(String id, String tanggal, ArrayList<Barang> barang) {
        this.id = id;
        this.tanggal = tanggal;
        this.barang = barang;
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

    public ArrayList<Barang> getBarang() {
        return barang;
    }

    public void setBarang(ArrayList<Barang> barang) {
        this.barang = barang;
    }
}
