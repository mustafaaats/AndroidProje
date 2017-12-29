package com.example.mehmetmetin436.proje;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.preference.DialogPreference;
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
import java.util.ArrayList;

public class ProfileActivity extends MainActivity {

    Bitmap thumbnail;
    private static int RESULT_LOAD_IMAGE_GALERI=101;
    private static int RESULT_LOAD_IMAGE_KAMERA=102;
    PopupMenu popup;

    String[] kullaniciBilgi;

    static String[] gelen_id;

    Button guncelleBtn,fotoBtn;
    EditText ad,soyad,parola,parolaTkr,kulad;
    ImageView im;
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
        fotoBtn = (Button)findViewById(R.id.fotobutton);

        Database db = new Database(ProfileActivity.this);
        kullaniciBilgi=db.profil();
        ad.setText(kullaniciBilgi[0]);
        soyad.setText(kullaniciBilgi[1]);
        kulad.setText(kullaniciBilgi[2]);
        parola.setText(kullaniciBilgi[3]);
        parolaTkr.setText(kullaniciBilgi[3]);

        im = (ImageView)findViewById(R.id.imageView2);
        String[]idim=db.profil();
        int foo = Integer.parseInt(idim[4]);
        profil_foto(foo);

        popup = new PopupMenu(ProfileActivity.this, fotoBtn);
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
                    im.setImageBitmap(selectedImage);


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
        im.setMaxWidth(200);
        im.setImageBitmap(thumbnail);
    }

    public void resimClicked(View view){
        popup.show();
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

        im.setDrawingCacheEnabled(true);
        im.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable)im.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        final byte[] data_guncel = baos.toByteArray();


        if (adi.isEmpty() || soyadi.isEmpty() || kullaniciAdi.isEmpty() || parol.isEmpty() || parolTkr.isEmpty()){
            Toast.makeText(this, R.string.bos_alan, Toast.LENGTH_SHORT).show();
        }else {
            if (parol.equals(parolTkr)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.onay).setCancelable(false).setPositiveButton(R.string.evet,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        gelen_id=db.profil();
                        int foo = Integer.parseInt(gelen_id[4]);
                        Kullanicilardb kullanici = new Kullanicilardb(foo,adi,soyadi,kullaniciAdi,parol,data_guncel);
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
    @SuppressLint("WrongConstant")
    public void profil_foto(int idim){
        Database DB = new Database(ProfileActivity.this);
        byte[] a=DB.profil_fotom(idim);
        Bitmap bMap = BitmapFactory.decodeByteArray(a, 0, a.length);
        im.setImageBitmap(bMap);
    }

}


