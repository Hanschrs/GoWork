package com.steventanjung.gowork;

/**
 * Created by StevenTanjung on 10/24/2017.
 */

public class Barang
{
    private String id;
    private String nama;
    private String harga;

    public Barang(String id, String nama, String harga, int what) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
