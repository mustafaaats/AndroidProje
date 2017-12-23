package com.example.mehmetmetin436.proje;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KaydolActivity extends AppCompatActivity {

    EditText adEd,soyadEd,kuladEd,kuladTekarEd,sifreEd,sifreTekrarEd;

    Button kaydolButon;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaydol);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//https://gelecegiyazanlar.turkcell.com.tr/konu/android/egitim/android-201/eylem-dugmeleri-eklemek
        //proje yazısının anasayfa gibi çalışması için yazılan kod mainActivityi manifest dosyasında ana activity olarak ayarlamamız gerekiyor


        adEd = (EditText)findViewById(R.id.edAd);
        soyadEd = (EditText)findViewById(R.id.edSoyad);
        kuladEd = (EditText)findViewById(R.id.edKulad);
        sifreEd = (EditText)findViewById(R.id.edSifre);
        sifreTekrarEd  = (EditText)findViewById(R.id.edSifreTekrar);
        kaydolButon = (Button)findViewById(R.id.butonKaydol);

    }


    public void kayitClicked(View view){
        String adi = adEd.getText().toString();
        String soyadi =soyadEd.getText().toString();
        String kulad = kuladEd.getText().toString();
        String sifre = sifreEd.getText().toString();
        String sifreTekrar = sifreTekrarEd.getText().toString();
        if (adi.isEmpty() || soyadi.isEmpty() || kulad.isEmpty() || sifre.isEmpty() || sifreTekrar.isEmpty()){
            Toast.makeText(this,R.string.bos_alan,Toast.LENGTH_SHORT).show();
        }
        else {
            if (sifre.equals(sifreTekrar)) {
                Kullanicilardb kullanici = new Kullanicilardb(adi, soyadi, kulad, sifre);
                Database db = new Database(KaydolActivity.this);
                db.kullaniciEkle(kullanici);
                Toast.makeText(this, R.string.kayit_basarili, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.kull_hata, Toast.LENGTH_SHORT).show();
            }
        }
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        menu.getItem(0).setVisible(false);
        menu.getItem(1).getSubMenu().getItem(0).setVisible(false);
        menu.getItem(1).getSubMenu().getItem(2).setVisible(false);
        return super.onCreateOptionsMenu(menu);

    }
    /*public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.uygulmaKapat:
                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }*/

}
