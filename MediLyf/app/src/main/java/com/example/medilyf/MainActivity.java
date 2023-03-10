package com.example.medilyf;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medilyf.medicine.MedicineActivity;

public class MainActivity extends AppCompatActivity {

    ImageButton apt,hosp,pre,cnt;
    Button logout;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apt = findViewById(R.id.imageButton3);
        hosp = findViewById(R.id.imageButton1);
        pre = findViewById(R.id.imageButton2);
        cnt = findViewById(R.id.imageButton4);
        logout = findViewById(R.id.button4);



        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MedicineActivity.class);
                startActivity(intent);

            }
        });

        hosp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:19.7515,75.7139"));
                Intent chooser = Intent.createChooser(intent,"Search Hospitals");
                startActivity(chooser);
            }
        });

        apt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        cnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cntct_us.class);
                startActivity(intent);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedpreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this,"Signed Out Successfully..!!",Toast.LENGTH_SHORT).show();
            }
        });
    }


}