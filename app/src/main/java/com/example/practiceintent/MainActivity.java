package com.example.practiceintent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final int TAKE_PICTURE = 1;
    private ImageView imvPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imvPhoto = findViewById(R.id.imvPhoto);

        findViewById(R.id.tvSendEmail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:hieppdutck59@gmail.com"));
                startActivity(Intent.createChooser(intent,"Chooser Title"));
            }
        });

        findViewById(R.id.tvPlayStore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store/apps"));
                startActivity(Intent.createChooser(intent,"Chooser Title"));
            }
        });

        findViewById(R.id.tvPhoneCall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // nếu sử dụng Intent.ACTION_CALL cần thêm permission android.permission.CALL_PHONE
                // nếu sử dụng Intent.ACTION_DIAL thì không cần
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0966059345"));
                startActivity(intent);
            }
        });

        findViewById(R.id.tvCamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
//                intent.putExtra(MediaStore.EXTRA_OUTPUT,
//                        Uri.fromFile(photo));
//                imageUri = Uri.fromFile(photo);
                startActivityForResult(intent, TAKE_PICTURE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_PICTURE) {
            if (resultCode == Activity.RESULT_OK) {
                // sử dụng uri thì đang bị null
                // sử dụng bitmap thì được
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imvPhoto.setImageBitmap(bitmap);
            }
        }
    }
}