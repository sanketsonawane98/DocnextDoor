package com.example.medilyf;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class ForgetPass extends AppCompatActivity {

    ImageView forget;
    TextView for_text;
    Button reset;
    TextInputLayout username;
    DBHelper db;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        username = findViewById(R.id.forusername);
        for_text = findViewById(R.id.forgetText);
        reset = findViewById(R.id.reset);
        forget = findViewById(R.id.resetpass);

        db = new DBHelper(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = Objects.requireNonNull(username.getEditText()).getText().toString();
                Boolean checkuser  = db.checkusername(user);
                if(checkuser)
                {
                    Intent intent = new Intent(ForgetPass.this,Resetpass.class);

                        intent.putExtra("username",user);
                        startActivity(intent);

                }
                else
                {
                    Toast.makeText(ForgetPass.this,"User does not exists..",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}