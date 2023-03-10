package com.example.medilyf;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class Login extends AppCompatActivity {

    Button signup, login, forgetPass;
    ImageView image;
    TextView logo_txt, slogan_txt;
    TextInputLayout username, password;
    SharedPreferences sp;
    public static final String Username1 = "nameKey";
    public static final String Password1 = "passKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);
        image = findViewById(R.id.logo_image);
        logo_txt = findViewById(R.id.logo_name);
        slogan_txt = findViewById(R.id.slogan_name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        forgetPass = findViewById(R.id.forget);

        sp = getSharedPreferences("login", Context.MODE_PRIVATE);

        if(sp.getBoolean("logged",false)){
            goToMainActivity();
        }

        DBHelper myDB = new DBHelper(Login.this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,SignUp.class);

                Pair[] pairs = new Pair[8];
                pairs[0] = new Pair<View,String>(image,"logo_image");
                pairs[1] = new Pair<View,String>(logo_txt,"logo_text");
                pairs[2] = new Pair<View,String>(slogan_txt,"signup_tran");
                pairs[3] = new Pair<View,String>(username,"username_tran");
                pairs[4] = new Pair<View,String>(password,"password_tran");
                pairs[5] = new Pair<View,String>(login,"button_tran");
                pairs[6] = new Pair<View,String>(forgetPass,"button3_tran");
                pairs[7] = new Pair<View,String>(signup,"button2_tran");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
                    startActivity(intent, options.toBundle());
                }

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);*/
                String Username = Objects.requireNonNull(username.getEditText()).getText().toString();
                String Password = Objects.requireNonNull(password.getEditText()).getText().toString();
                if (!validateUsername() | !validatePassword()){
                    return;
                }
                else {

                    boolean result = myDB.checkusernamepassword(Username, Password);
                    if (result){

                        Toast.makeText(Login.this, "Signed in successfully..!!", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(Login.this,MainActivity.class);
                        startActivity(intent2);
                        sp.edit().putBoolean("logged",true).apply();

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(Username1, Username);
                        editor.putString(Password1, Password);
                        editor.commit();

                    }
                    else {
                        Toast.makeText(Login.this, "Invalid credentials..", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,ForgetPass.class);
                Pair[] pairs = new Pair[8];
                pairs[0] = new Pair<View,String>(image,"logo_image");
                pairs[1] = new Pair<View,String>(logo_txt,"logo_text");
                pairs[2] = new Pair<View,String>(slogan_txt,"signup_tran");
                pairs[3] = new Pair<View,String>(username,"username_tran");
                pairs[4] = new Pair<View,String>(password,"password_tran");
                pairs[5] = new Pair<View,String>(login,"button_tran");
                pairs[6] = new Pair<View,String>(forgetPass,"button3_tran");
                pairs[7] = new Pair<View,String>(signup,"button2_tran");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
                    startActivity(intent, options.toBundle());
                }
            }
        });


    }

    private void goToMainActivity() {
        Intent i = new Intent(Login.this,MainActivity.class);
        startActivity(i);
    }

    private boolean validateUsername() {
        String val = Objects.requireNonNull(username.getEditText()).getText().toString();
        if (val.isEmpty()){
            username.setError("Field cannot be empty");
            return false;
        }else if(val.length()>=30){
            username.setError("Username too long");
            return false;
        }
        else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = Objects.requireNonNull(password.getEditText()).getText().toString();
        if (val.isEmpty()){
            password.setError("Field cannot be empty");
            return false;
        }
        else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

}