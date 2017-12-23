package com.example.mehmetmetin436.proje;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ProfileActivity extends MainActivity {
    String[] kullaniciBilgi;

    Button guncelleBtn;
    EditText ad,soyad,parola,parolaTkr,kulad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        guncelleBtn = (Button) findViewById(R.id.butonGuncelle);
        ad = (EditText)findViewById(R.id.edAd);
        soyad = (EditText)findViewById(R.id.edSoyad);
        kulad = (EditText)findViewById(R.id.edKulad);
        parola = (EditText)findViewById(R.id.edSifre);
        parolaTkr = (EditText)findViewById(R.id.edSifreTekrar);

        Database db = new Database(ProfileActivity.this);
        kullaniciBilgi=db.profil();
        ad.setText(kullaniciBilgi[0]);
        soyad.setText(kullaniciBilgi[1]);
        kulad.setText(kullaniciBilgi[2]);
        parola.setText(kullaniciBilgi[3]);
        parolaTkr.setText(kullaniciBilgi[3]);

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    public void guncelle(View v){
        final Database db = new Database(ProfileActivity.this);
        final String adi = ad.getText().toString();
        final String soyadi = soyad.getText().toString();
        final String kullaniciAdi = kulad.getText().toString();
        final String parol = parola.getText().toString();
        final String parolTkr = parolaTkr.getText().toString();

        if (adi.isEmpty() || soyadi.isEmpty() || kullaniciAdi.isEmpty() || parol.isEmpty() || parolTkr.isEmpty()){
            Toast.makeText(this, R.string.bos_alan, Toast.LENGTH_SHORT).show();
        }else {
            if (parol.equals(parolTkr)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.onay).setCancelable(false).setPositiveButton(R.string.evet,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String[] gelen_id=db.profil();
                        int foo = Integer.parseInt(gelen_id[4]);
                        Kullanicilardb kullanici = new Kullanicilardb(foo,adi,soyadi,kullaniciAdi,parol);
                        db.kullaniciGuncelle(kullanici);
                        Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }).setNegativeButton(R.string.hayir, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(getIntent());
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.setTitle(R.string.uyari);
                alert.show();
            }


        }
    }

}


