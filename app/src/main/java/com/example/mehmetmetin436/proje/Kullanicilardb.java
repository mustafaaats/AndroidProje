package com.example.mehmetmetin436.proje;

/**
 * Created by metin on 6.12.2017.
 */

public class Kullanicilardb {
    private String kulad;
    private String soyad;
    private String ad;
    private String sifre;
    private int id;
    private byte[]data;
    public Kullanicilardb(){}

    public Kullanicilardb(String ad, String soyad, String kulad, String sifre,byte[]data){
        this.data=data;
        this.kulad=kulad;
        this.soyad=soyad;
        this.sifre=sifre;
        this.ad=ad;
    }

    public Kullanicilardb(int id ,String ad, String soyad, String kulad, String sifre,byte[]data){
        this.id=id;
        this.data=data;
        this.kulad=kulad;
        this.soyad=soyad;
        this.sifre=sifre;
        this.ad=ad;
    }

    public Kullanicilardb(String ad, String soyad, String kulad, String sifre){
        this.kulad=kulad;
        this.soyad=soyad;
        this.sifre=sifre;
        this.ad=ad;
    }


    public Kullanicilardb(String kulad, String sifre){
        this.kulad=kulad;
        this.sifre=sifre;
    }
    public Kullanicilardb(int id,String ad, String soyad, String kulad, String sifre){
        this.kulad=kulad;
        this.soyad=soyad;
        this.sifre=sifre;
        this.ad=ad;
        this.id=id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
