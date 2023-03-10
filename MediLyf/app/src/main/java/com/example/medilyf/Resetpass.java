package com.example.medilyf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class Resetpass extends AppCompatActivity {

    TextView username;
    Button confirm;
    TextInputLayout pass, repass;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpass);

        username = findViewById(R.id.reset_user);
        confirm = findViewById(R.id.confirm_pass);
        pass = findViewById(R.id.resetpasswd);
        repass = findViewById(R.id.retypepasswd);

        db = new DBHelper(this);
        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String password = Objects.requireNonNull(pass.getEditText()).getText().toString();
                String repassword = Objects.requireNonNull(repass.getEditText()).getText().toString();
                if (password.equals(repassword)) {

                    Boolean checkpasswordupdate = db.updatePassword(user, password);
                    if (checkpasswordupdate == true) {
                        Intent intent = new Intent(Resetpass.this, Login.class);
                        startActivity(intent);
                        Toast.makeText(Resetpass.this, "Password updated successfully..!!", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(Resetpass.this, "Password not updated..", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Resetpass.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}