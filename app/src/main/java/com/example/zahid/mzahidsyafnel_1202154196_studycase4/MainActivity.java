package com.example.zahid.mzahidsyafnel_1202154196_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button y, z;

    //Method override oncreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //variable untuk pencari dan list
        y = (Button) findViewById(R.id.pencari);
        z = (Button) findViewById(R.id.list);

        //mensetting toolbar yang akan muncul di atas dengan nama pilih menu
        Toolbar mToolbar = (Toolbar) findViewById(R.id.main_activity);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Pilihan Menu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //variable Jika di click akan mencari gambar
        y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ab = new Intent(MainActivity.this,PencariGambar.class);
                startActivity(ab);
            }
        });

        // Variable untuk click dari main activity dan ListNamaMahasiswa
        z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ac = new Intent(MainActivity.this,ListNamaMahasiswa.class);
                startActivity(ac);
            }
        });
    }
}
