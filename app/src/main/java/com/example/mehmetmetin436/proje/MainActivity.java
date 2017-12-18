package com.example.mehmetmetin436.proje;

import android.content.Intent;
import android.content.SharedPreferences;
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
        if(varmi==true){
            Toast.makeText(this,"Kullanıcı girişiniz başarılı.",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,AnasayfaActivity.class);
            startActivity(intent);

        }
        else {
            Toast.makeText(this,"Kullanıcı girişiniz başarısız.",Toast.LENGTH_SHORT).show();
        }

        if(hatirlaCb.isChecked()){
            editor.putString(KUL_KEY,kuladEdit.getText().toString());
            editor.putString(SIFRE_KEY,sifreEdit.getText().toString());
            editor.commit();//Değişikliklerinizin kaydedilmesi için commit() kullanılır.
            Toast.makeText(this,"Check Box işaretli",Toast.LENGTH_LONG).show();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.uygulmaKapat:
                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
