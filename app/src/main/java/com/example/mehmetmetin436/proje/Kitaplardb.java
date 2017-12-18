package com.example.mehmetmetin436.proje;

/**
 * Created by metin on 18.12.2017.
 */

public class Kitaplardb {
    private String kitapAdi;
    private String ISBN;
    private String yazarAdi;
    private String kitapTuru;
    private int id;

    public Kitaplardb (){}

    public Kitaplardb(String kitapAdi,String ISBN, String yazarAdi, String kitapTuru){
        this.kitapAdi=kitapAdi;
        this.ISBN=ISBN;
        this.yazarAdi=yazarAdi;
        this.kitapTuru=kitapTuru;

    }

    public Kitaplardb(int id, String kitapAdi,String ISBN, String yazarAdi, String kitapTuru){
        this.kitapAdi=kitapAdi;
        this.ISBN=ISBN;
        this.yazarAdi=yazarAdi;
        this.kitapTuru=kitapTuru;
        this.id=id;
    }

    public Kitaplardb(int id){
        this.id=id;
    }

    public String getKitapAdi() {
        return kitapAdi;
    }

    public void setKitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getYazarAdi() {
        return yazarAdi;
    }

    public void setYazarAdi(String yazarAdi) {
        this.yazarAdi = yazarAdi;
    }

    public String getKitapTuru() {
        return kitapTuru;
    }

    public void setKitapTuru(String kitapTuru) {
        this.kitapTuru = kitapTuru;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
