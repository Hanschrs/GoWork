package com.steventanjung.gowork;

/**
 * Created by Hans on 12/4/2017.
 */

public class Belanjaan {
    String nama_barang;
    int jumlah_barang, harga_satuan;

    public Belanjaan(String nama_barang, int jumlah_barang, int harga_satuan) {
        this.nama_barang = nama_barang;
        this.jumlah_barang = jumlah_barang;
        this.harga_satuan = harga_satuan;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public int getJumlah_barang() {
        return jumlah_barang;
    }

    public void setJumlah_barang(int jumlah_barang) {
        this.jumlah_barang = jumlah_barang;
    }

    public int getHarga_satuan() {
        return harga_satuan;
    }

    public void setHarga_satuan(int harga_satuan) {
        this.harga_satuan = harga_satuan;
    }
}
