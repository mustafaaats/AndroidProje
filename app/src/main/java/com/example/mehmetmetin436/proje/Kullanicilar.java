package com.example.mehmetmetin436.proje;

/**
 * Created by metin on 6.12.2017.
 */

public class Kullanicilar {
    private String kulad;
    private String soyad;
    private String ad;
    private String sifre;

    public Kullanicilar(){}

    public Kullanicilar(String ad, String soyad,String kulad,String sifre){
        this.kulad=kulad;
        this.soyad=soyad;
        this.sifre=sifre;
        this.ad=ad;
    }

    public Kullanicilar(String kulad, String sifre){
        this.kulad=kulad;
        this.sifre=sifre;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getKulad() {
        return kulad;
    }

    public void setKulad(String kulad) {
        this.kulad = kulad;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }
}
