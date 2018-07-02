package com.steventanjung.gowork;

/**
 * Created by Hans on 12/4/2017.
 */

public class NotaList {
    private String tanggal, namaBarang;
    private int id, no_nota, harga_satuan, jumlah, diskon;

    public NotaList(String tanggal, String namaBarang, int id, int no_nota, int harga_satuan, int jumlah, int diskon) {
        this.tanggal = tanggal;
        this.namaBarang = namaBarang;
        this.id = id;
        this.no_nota = no_nota;
        this.harga_satuan = harga_satuan;
        this.jumlah = jumlah;
        this.diskon = diskon;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
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

    public int getHarga_satuan() {
        return harga_satuan;
    }

    public void setHarga_satuan(int harga_satuan) {
        this.harga_satuan = harga_satuan;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getDiskon() {
        return diskon;
    }

    public void setDiskon(int diskon) {
        this.diskon = diskon;
    }
}
