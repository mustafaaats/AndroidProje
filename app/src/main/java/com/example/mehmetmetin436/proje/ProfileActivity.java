package com.example.mehmetmetin436.proje;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ProfileActivity extends MainActivity {
    String[] kullanici;

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
        kullanici=db.profil();
        ad.setText(kullanici[0]);
        soyad.setText(kullanici[1]);
        kulad.setText(kullanici[2]);
        parola.setText(kullanici[3]);
        parolaTkr.setText(kullanici[3]);

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


}
