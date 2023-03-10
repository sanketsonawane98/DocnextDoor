package com.example.medilyf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SignUp extends AppCompatActivity {


    TextInputLayout regName, regUsername, regPassword, regEmail, regPhoneno;
    Button regBtn, regLoginbtn;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        regName = findViewById(R.id.name);
        regUsername = findViewById(R.id.username);
        regPassword = findViewById(R.id.password);
        regEmail = findViewById(R.id.email);
        regPhoneno = findViewById(R.id.PhoneNo);
        regBtn = findViewById(R.id.button);
        regLoginbtn = findViewById(R.id.button2);

        myDB = new DBHelper(SignUp.this);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = Objects.requireNonNull(regName.getEditText()).getText().toString();
                String username = Objects.requireNonNull(regUsername.getEditText()).getText().toString();
                String password = Objects.requireNonNull(regPassword.getEditText()).getText().toString();
                String email = Objects.requireNonNull(regEmail.getEditText()).getText().toString();
                String phno = Objects.requireNonNull(regPhoneno.getEditText()).getText().toString();
                //int phoneNo = Integer.parseInt(phno);

                if (!validateName() | !validateUsername() | !validateEmail() | !validatePhoneno() | !validatePassword()) {
                   return;
                }
                else
                {
                    boolean usercheckresult = myDB.checkusername(username);
                    if (usercheckresult) {
                        Toast.makeText(SignUp.this, "Username Already exists..", Toast.LENGTH_SHORT).show();
                    } else {

                       boolean regResult = myDB.insertData(name, username, email, phno, password );
                       if (regResult){
                           Toast.makeText(SignUp.this, "Sign In Successfull..", Toast.LENGTH_SHORT).show();

                       }
                       else{
                           Toast.makeText(SignUp.this, "Sign In Failed..", Toast.LENGTH_SHORT).show();
                       }
                    }
                }



            }
        });

        regLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateName() {
        String val = Objects.requireNonNull(regName.getEditText()).getText().toString();
        if (val.isEmpty()){
            regName.setError("Field cannot be empty");
            return false;
        }
        else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUsername() {
        String val = Objects.requireNonNull(regUsername.getEditText()).getText().toString();
        if (val.isEmpty()){
            regUsername.setError("Field cannot be empty");
            return false;
        }else if(val.length()>=30){
            regUsername.setError("Username too long");
            return false;
        }
        else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = Objects.requireNonNull(regEmail.getEditText()).getText().toString();
        String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()){
            regEmail.setError("Field cannot be empty");
            return false;
        }else if(!val.matches(emailpattern)){
            regEmail.setError("Invalid email address");
            return false;
        }
        else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePhoneno() {
        String val = Objects.requireNonNull(regPhoneno.getEditText()).getText().toString();

        if (val.isEmpty()){
            regPhoneno.setError("Field cannot be empty");
            return false;
        }
        else {
            regPhoneno.setError(null);
            regPhoneno.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = Objects.requireNonNull(regPassword.getEditText()).getText().toString();
        if (val.isEmpty()){
            regPassword.setError("Field cannot be empty");
            return false;
        }else if(val.length()<5){
            regPassword.setError("Password is too weak");
            return false;
        }
        else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }
}