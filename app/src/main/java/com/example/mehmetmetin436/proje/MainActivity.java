package com.example.mehmetmetin436.proje;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String KUL_KEY="com.example.mehmetmetin436.KUL";
    private String SIFRE_KEY="com.example.mehmetmetin436.SIFRE";
    private String MAIN_DATA="com.example.mehmetmetin436.MAIN_DATA";

    boolean varmi=false;

    EditText kuladEdit,sifreEdit;
    Button girisButton;
    Button kaydolB;
    CheckBox hatirlaCb;

    String veriKulad,veriSifre;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        kuladEdit = (EditText)findViewById(R.id.editKulad);
        sifreEdit = (EditText)findViewById(R.id.editSifre);
        girisButton = (Button)findViewById(R.id.buttonGiris);
        hatirlaCb = (CheckBox)findViewById(R.id.cbHatirla);
        kaydolB = (Button)findViewById(R.id.butonKaydol);

        sharedPreferences=getSharedPreferences(MAIN_DATA,MODE_PRIVATE);
        editor=sharedPreferences.edit();



        veriKulad=getSharedPreferences(MAIN_DATA,MODE_PRIVATE).getString(KUL_KEY,"");
        veriSifre=getSharedPreferences(MAIN_DATA,MODE_PRIVATE).getString(SIFRE_KEY,"");
        sifreEdit.setText(veriSifre);
        kuladEdit.setText(veriKulad);


    }
    public void kaydolClicked(View view){
        Intent intent = new Intent(MainActivity.this,KaydolActivity.class);
        startActivity(intent);
    }

    public void girisyapClicked(View view){
        String kulad = kuladEdit.getText().toString();
        String sifre = sifreEdit.getText().toString();
        Database db = new Database(MainActivity.this);
        Kullanicilardb kullanici = new Kullanicilardb(kulad,sifre);
        varmi=db.varMı(kullanici);
        if (kulad.isEmpty() || sifre.isEmpty()) {
            Toast.makeText(this, R.string.bos_alan,Toast.LENGTH_SHORT).show();
        }
        else {
            if (varmi == true) {
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle(R.string.app_name);
                progressDialog.setMessage(getResources().getString(R.string.bekle));
                progressDialog.show();
                progressDialog.setCancelable(false);
                Toast.makeText(this, R.string.kul_giris, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, AnasayfaActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.kul_giris_hata, Toast.LENGTH_SHORT).show();
            }

            if (hatirlaCb.isChecked()) {
                editor.putString(KUL_KEY, kuladEdit.getText().toString());
                editor.putString(SIFRE_KEY, sifreEdit.getText().toString());
                editor.commit();//Değişikliklerinizin kaydedilmesi için commit() kullanılır.
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
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.anasayafa:
                Intent intent3=new Intent(this,AnasayfaActivity.class);
                startActivity(intent3);
                break;
            case R.id.uygulmaKapat:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.kapat).setCancelable(false).setPositiveButton(R.string.evet, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finishAffinity();

                    }
                }).setNegativeButton(R.string.hayir, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.setTitle(R.string.uyari);
                alert.show();
                break;
            case R.id.diliDegistir:
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setClassName("com.android.settings", "com.android.settings.LanguageSettings");
                startActivity(intent);
                break;
            case R.id.cikisi_yap:
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle(R.string.app_name);
                progressDialog.setMessage(getResources().getString(R.string.bekle1));
                progressDialog.show();
                progressDialog.setCancelable(false);
                Intent intent1 = new Intent(this,MainActivity.class);
                startActivity(intent1);
                Toast.makeText(this, R.string.kul_cikis,Toast.LENGTH_SHORT).show();
                break;
            case R.id.profile:
                Intent intent2 = new Intent(this,ProfileActivity.class);
                startActivity(intent2);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}
