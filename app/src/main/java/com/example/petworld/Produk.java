package com.example.petworld;

public class Produk {
    private String nama;
    private String gambar;
    private Integer harga;
    private String id;

    public Produk(){};

    public Produk(String id, String nama, String gambar, Integer harga){
        this.id    = id;
        this.nama  = nama;
        this.gambar= gambar;
        this.harga = harga;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getGambar() {
        return gambar;
    }

    public Integer getHarga() { return harga; }
}
