package com.example.mehmetmetin436.proje;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;

public class AnasayfaActivity extends AppCompatActivity {

    Button btnYukle;
    private static int RESULT_LOAD_IMAGE_GALERI=1;
    private static int RESULT_LOAD_IMAGE_KAMERA=2;
    PopupMenu popup;
    ImageView profilResmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//https://gelecegiyazanlar.turkcell.com.tr/konu/android/egitim/android-201/eylem-dugmeleri-eklemek
        //proje yazısının anasayfa gibi çalışması için yazılan kod mainActivityi manifest dosyasında ana activity olarak ayarlamamız gerekiyor

        btnYukle=(Button)findViewById(R.id.resimYukle);

        popup = new PopupMenu(AnasayfaActivity.this, btnYukle);
        popup.getMenuInflater().inflate(R.menu.kamera_galeri, popup.getMenu());

        profilResmi=(ImageView)findViewById(R.id.imageProfil);



        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.kameraAc:
                        Intent intentKamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intentKamera,RESULT_LOAD_IMAGE_KAMERA);
                    case R.id.galeriAc:
                        Intent intentGaleri = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intentGaleri,RESULT_LOAD_IMAGE_GALERI);
                    default :
                        return false;
                }
            }
        });
    }
    //gelen fotoğrafon image hhhh viewimize yerleşesini sağlar.
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==RESULT_LOAD_IMAGE_KAMERA){
            Bundle extras = data.getExtras();
            profilResmi.setImageBitmap((Bitmap) extras.get("data"));
        }
        else if(requestCode!=RESULT_LOAD_IMAGE_GALERI){
            Uri secFoto=data.getData();
            String []dosyaYolu={MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(secFoto,dosyaYolu,null,null,null);
            cursor.moveToFirst();


            int sutunIndex = cursor.getColumnIndex(dosyaYolu[0]);
            String resminYolu=cursor.getString(sutunIndex);
            cursor.close();
            profilResmi.setImageBitmap(BitmapFactory.decodeFile(resminYolu));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        getMenuInflater().inflate(R.menu.cikis_menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.uygulmaKapat:
                System.exit(0);
                return true;
            case  R.id.cikisi_yap:
                Intent intent  = new Intent(AnasayfaActivity.this,MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    public void ekleClicked(View view){
        popup.show();

    }
}
