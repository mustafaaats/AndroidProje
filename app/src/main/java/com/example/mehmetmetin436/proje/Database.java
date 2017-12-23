/*package com.example.mehmetmetin436.proje;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by metin on 6.12.2017.
 */
/*
public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="DBproje";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="kullanicilar";
    private static final String COLOUMN_ID="id";
    private static final String COLOUMN_AD="ad";
    private static final String COLOUMN_SOYAD="soyad";
    private static final String COLOUMN_KULAD="kulad";
    private static final String COLOUMN_SIFRE="sifre";
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        String query = "CREATE TABLE "+TABLE_NAME+" ("+COLOUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLOUMN_AD+" TEXT NOT NULL,"+COLOUMN_SOYAD+" TEXT NOT NULL,"+COLOUMN_KULAD+" TEXT NOT NULL,"+COLOUMN_SIFRE+" TEXT NOT NULL)";
        DB.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        String query="DROP TABLE IF EXISTS "+TABLE_NAME;
        DB.execSQL(query);
        onCreate(DB);
    }

    public void kullaniciEkle(Kullanicilardb kullanicilar){
        ContentValues cont = new ContentValues();

        cont.put(COLOUMN_AD,kullanicilar.getAd());
        cont.put(COLOUMN_SOYAD,kullanicilar.getSoyad());
        cont.put(COLOUMN_KULAD,kullanicilar.getKulad());
        cont.put(COLOUMN_SIFRE,kullanicilar.getSifre());

        SQLiteDatabase DB = this.getWritableDatabase();

        DB.insert(TABLE_NAME,null,cont);
        DB.close();
    }

    public boolean varMı(Kullanicilardb kullanicilar){
        SQLiteDatabase DB = this.getReadableDatabase();

        String user[]={COLOUMN_ID,COLOUMN_AD,COLOUMN_SOYAD,COLOUMN_KULAD,COLOUMN_SIFRE};

        Cursor cursor = DB.query(TABLE_NAME,user,null, null, null, null, null);*/

        /*int IROW = cursor.getColumnIndex(COLOUMN_ID);
        int IAD = cursor.getColumnIndex(COLOUMN_AD);
        int ISOYAD = cursor.getColumnIndex(COLOUMN_SOYAD);
        int IKULAD = cursor.getColumnIndex(COLOUMN_KULAD);
        int ISIFRE = cursor.getColumnIndex(COLOUMN_SIFRE);*/
/*        int a=0;
        while (cursor.moveToNext()){
            String kulad = cursor.getString(3);
            String sifre = cursor.getString(4);
            if(sifre.equals(kullanicilar.getSifre()) && kulad.equals(kullanicilar.getKulad())){
                a=1;
            }
            else{
                a=-1;
            }
        }
        if (a==1){
            return true;
        }
        else
            return false;
    }

}
*/
package com.example.mehmetmetin436.proje;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by metin on 6.12.2017.
 */

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="DBproje";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="kullanicilar";
    private static final String COLOUMN_ID="id";
    private static final String COLOUMN_AD="ad";
    private static final String COLOUMN_SOYAD="soyad";
    private static final String COLOUMN_KULAD="kulad";
    private static final String COLOUMN_SIFRE="sifre";
    private static final String TABLE2_NAME="kitaplar";

    private static final String COLOUMN_IDKITAP="id_kitap";
    private static final String COLOUMN_KITAPAD="kitap_adi";
    private static final String COLOUMN_ISBN="isbn";
    private static final String COLOUMN_YAZARAD="yazar_adi";
    private static final String COLOUMN_KITAPTUR="kitap_turu";
    private static final String COLOUMN_IMAGEK="image";

    private static final String queryKullanici = "CREATE TABLE "+TABLE_NAME+" ("+COLOUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLOUMN_AD+" TEXT NOT NULL,"+COLOUMN_SOYAD+" TEXT NOT NULL,"+COLOUMN_KULAD+" TEXT NOT NULL,"+COLOUMN_SIFRE+" TEXT NOT NULL)";
    private static final String queryKitap = "CREATE TABLE "+TABLE2_NAME+" ("+COLOUMN_IDKITAP+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLOUMN_KITAPAD+" TEXT NOT NULL, "+COLOUMN_ISBN+" TEXT NOT NULL, "+COLOUMN_YAZARAD+" TEXT NOT NULL, "+COLOUMN_KITAPTUR+" TEXT NOT NULL,"+COLOUMN_IMAGEK+" BLOB NOT NULL)";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL(queryKitap);
        DB.execSQL(queryKullanici);
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        String queryKullanici="DROP TABLE IF EXISTS "+TABLE_NAME;
        String queryKitap="DROP TABLE IF EXISTS "+TABLE2_NAME;
        DB.execSQL(queryKullanici);
        DB.execSQL(queryKitap);
        onCreate(DB);
    }

    public void kullaniciEkle(Kullanicilardb kullanicilar){
        ContentValues cont = new ContentValues();

        cont.put(COLOUMN_AD,kullanicilar.getAd());
        cont.put(COLOUMN_SOYAD,kullanicilar.getSoyad());
        cont.put(COLOUMN_KULAD,kullanicilar.getKulad());
        cont.put(COLOUMN_SIFRE,kullanicilar.getSifre());

        SQLiteDatabase DB = this.getWritableDatabase();

        DB.insert(TABLE_NAME,null,cont);
        DB.close();
    }
    static String[] kullanici;
    public boolean varMı(Kullanicilardb kullanicilar){
        SQLiteDatabase DB = this.getReadableDatabase();

        String user[]={COLOUMN_ID,COLOUMN_AD,COLOUMN_SOYAD,COLOUMN_KULAD,COLOUMN_SIFRE};

        Cursor cursor = DB.query(TABLE_NAME,user,null, null, null, null, null);

        /*int IROW = cursor.getColumnIndex(COLOUMN_ID);
        int IAD = cursor.getColumnIndex(COLOUMN_AD);
        int ISOYAD = cursor.getColumnIndex(COLOUMN_SOYAD);
        int IKULAD = cursor.getColumnIndex(COLOUMN_KULAD);
        int ISIFRE = cursor.getColumnIndex(COLOUMN_SIFRE);*/
        int a=0;
        while (cursor.moveToNext()){
            String kulad = cursor.getString(3);
            String sifre = cursor.getString(4);
            if(sifre.equals(kullanicilar.getSifre()) && kulad.equals(kullanicilar.getKulad())){
                a=1;
                kullanici=new String[]{cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(0)};
                break;
            }
            else{
                a=-1;
            }
        }
        if (a==1){
            return true;
        }
        else
            return false;
    }

    public ArrayList<String> DBArray(){
        ArrayList<String> urunlist = new ArrayList<String>();

        SQLiteDatabase DB = this.getReadableDatabase();

        String kitap[]={COLOUMN_IDKITAP,COLOUMN_KITAPAD,COLOUMN_ISBN,COLOUMN_YAZARAD,COLOUMN_KITAPTUR};
        //Cursor cursor = DB.query(TABLE2_NAME,urun,null,null,null,null,null,null);
        Cursor cursor = DB.query(TABLE2_NAME,kitap,null, null, null, null, null);
        while (cursor.moveToNext()){
            urunlist.add(cursor.getInt(0)+"--"+cursor.getString(1)+"--"+cursor.getString(2)+"--"+cursor.getString(3)+"--"+cursor.getString(4));
        }
        return urunlist;
    }
    public void kitapEkle(Kitaplardb kitaplar){

        ContentValues cont = new ContentValues();
        cont.put(COLOUMN_KITAPAD,kitaplar.getKitapAdi());
        cont.put(COLOUMN_ISBN,kitaplar.getISBN());
        cont.put(COLOUMN_YAZARAD,kitaplar.getYazarAdi());
        cont.put(COLOUMN_KITAPTUR,kitaplar.getKitapTuru());

        cont.put(COLOUMN_IMAGEK,kitaplar.getData());

        SQLiteDatabase DB = this.getWritableDatabase();
        DB.insert(TABLE2_NAME,null,cont);
        DB.close();
    }

    public void kitapGuncelle(Kitaplardb kitaplar){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cont = new ContentValues();
        cont.put(COLOUMN_KITAPAD,kitaplar.getKitapAdi());
        cont.put(COLOUMN_ISBN,kitaplar.getISBN());
        cont.put(COLOUMN_YAZARAD,kitaplar.getYazarAdi());
        cont.put(COLOUMN_KITAPTUR,kitaplar.getKitapTuru());

        String where=COLOUMN_IDKITAP+"="+kitaplar.getId();
        DB.update(TABLE2_NAME,cont,where,null);
        DB.close();
    }

    public void KitapSil(Kitaplardb kitaplar){
        SQLiteDatabase DB = this.getWritableDatabase();
        String where=COLOUMN_IDKITAP+"="+kitaplar.getId();
        DB.delete(TABLE2_NAME,where,null);
        DB.close();

    }
    public String[] profil(){
        return kullanici;
    }

    public String[] kullaniciGuncelle(Kullanicilardb kullanici){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cont = new ContentValues();

        cont.put(COLOUMN_AD,kullanici.getAd());
        cont.put(COLOUMN_SOYAD,kullanici.getSoyad());
        cont.put(COLOUMN_KULAD,kullanici.getKulad());
        cont.put(COLOUMN_SIFRE,kullanici.getSifre());

        String where = COLOUMN_ID+"="+kullanici.getId();
        DB.update(TABLE_NAME,cont,where,null);
        DB.close();
        varMı(kullanici);
        String[] array = new String[]{kullanici.getAd(),kullanici.getSoyad(),kullanici.getSifre()};
        return array;
    }

    /*Kitaplardb getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String kitap[]={COLOUMN_IDKITAP,COLOUMN_KITAPAD,COLOUMN_ISBN,COLOUMN_YAZARAD,COLOUMN_KITAPTUR,COLOUMN_IMAGEK};
        Cursor cursor = db.query(TABLE2_NAME,kitap, COLOUMN_IDKITAP + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Kitaplardb kitap = new Kitaplardb(Integer.parseInt(cursor.getString(0)),
               ,cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getBlob(5));

        return kitap;

    }*/
}
