package com.example.v1;

import static com.google.android.material.internal.ContextUtils.getActivity;
import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.print.PrintHelper;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);

       findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
                Bitmap bm = getScreenShot(rootView);
                String temp = "quote";
                File file = store(bm,temp);
                PrintHelper photoPrinter = new PrintHelper(MainActivity2.this);
                photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
                photoPrinter.printBitmap("droids.jpg - test print", bm);
            }
            public Bitmap getScreenShot(View view) {
                View screenView = view.getRootView();
                screenView.setDrawingCacheEnabled(true);
                Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
                screenView.setDrawingCacheEnabled(false);
                return bitmap;
            }
            public File store(Bitmap bm, String fileName) {
                final String dirPath = Environment.getDataDirectory().getAbsolutePath() + "/Screenshots";
                File dir = new File(dirPath);
                if (!dir.exists())
                    dir.mkdirs();
                File file = new File(dirPath, fileName);

                try {
                    FileOutputStream fOut = new FileOutputStream(file);
                    bm.compress(Bitmap.CompressFormat.PNG, 85, fOut);
                    fOut.flush();
                    fOut.close();
                    //return file;
                } catch (Exception e) {
                    e.printStackTrace();
                }
               return file;
            }
            private void shareImage(File file){
                Uri uri = Uri.fromFile(file);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("image/*");

                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                try {
                    startActivity(Intent.createChooser(intent, "Share Screenshot"));
                } catch (ActivityNotFoundException e) {
                    //Toast.makeText(context, "No App Available", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}