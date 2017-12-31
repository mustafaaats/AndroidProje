package com.example.mehmetmetin436.proje;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class KaydolActivity extends MainActivity {

    Bitmap thumbnail;
    private static int RESULT_LOAD_IMAGE_GALERI=101;
    private static int RESULT_LOAD_IMAGE_KAMERA=102;
    PopupMenu popup;
    ImageView kulFoto;


    EditText adEd,soyadEd,kuladEd,kuladTekarEd,sifreEd,sifreTekrarEd;

    Button kaydolButon,resimBtn;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaydol);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        adEd = (EditText)findViewById(R.id.edAd);
        soyadEd = (EditText)findViewById(R.id.edSoyad);
        kuladEd = (EditText)findViewById(R.id.edKulad);
        sifreEd = (EditText)findViewById(R.id.edSifre);
        sifreTekrarEd  = (EditText)findViewById(R.id.edSifreTekrar);
        kaydolButon = (Button)findViewById(R.id.butonKaydol);
        kulFoto=(ImageView)findViewById(R.id.imageView);
        resimBtn=(Button)findViewById(R.id.btnKitapResmi);

        popup = new PopupMenu(KaydolActivity.this, resimBtn);
        popup.getMenuInflater().inflate(R.menu.kamera_galeri, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.kameraAc:
                        Intent intentKamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intentKamera,RESULT_LOAD_IMAGE_KAMERA);
                        return  true;
                    case R.id.galeriAc:
                        Intent intentGaleri = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intentGaleri,RESULT_LOAD_IMAGE_GALERI);
                        return true;
                    default :
                        return false;
                }
            }
        });

    }

    //gelen fotoğrafon image viewimize yerleşesini sağlar.
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_LOAD_IMAGE_GALERI){
            if(resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                    //set profile picture form gallery
                    kulFoto.setImageBitmap(selectedImage);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }else if(requestCode == RESULT_LOAD_IMAGE_KAMERA){
            if(resultCode == RESULT_OK) {
                onCaptureImageResult(data);
            }
        }
    }

    private void onCaptureImageResult(Intent data) {
        thumbnail = (Bitmap) data.getExtras().get("data");
        kulFoto.setMaxWidth(200);
        kulFoto.setImageBitmap(thumbnail);
    }

    public void resimClicked(View view){
        popup.show();
    }




    public void kayitClicked(View view){

        kulFoto.setDrawingCacheEnabled(true);
        kulFoto.buildDrawingCache();
        Bitmap bitmap = kulFoto.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

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
                if (kulFoto.getDrawable()==null){
                    data= ByteBuffer.allocate(4).putInt(1905).array();
                }
                Kullanicilardb kullanici = new Kullanicilardb(adi, soyadi, kulad, sifre,data);
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

}
