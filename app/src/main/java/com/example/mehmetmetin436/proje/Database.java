package com.example.mehmetmetin436.proje;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public void kullaniciEkle(Kullanicilar kullanicilar){
        ContentValues cont = new ContentValues();

        cont.put(COLOUMN_AD,kullanicilar.getAd());
        cont.put(COLOUMN_SOYAD,kullanicilar.getSoyad());
        cont.put(COLOUMN_KULAD,kullanicilar.getKulad());
        cont.put(COLOUMN_SIFRE,kullanicilar.getSifre());

        SQLiteDatabase DB = this.getWritableDatabase();

        DB.insert(TABLE_NAME,null,cont);
        DB.close();
    }

    public boolean varMı(Kullanicilar kullanicilar){
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
