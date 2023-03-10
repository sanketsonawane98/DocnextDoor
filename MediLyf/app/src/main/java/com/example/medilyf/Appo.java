package com.example.medilyf;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;


public class Appo extends AppCompatActivity {



    DatePickerDialog picker;
    EditText eText;
    Button btnGet, confirm;
    FloatingActionButton fb;
    TextView tvw, text, receiver_msg;
    DBHelper myDB;
    CheckBox android, java, angular, python;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appo);

        tvw = findViewById(R.id.textView1);
        eText = findViewById(R.id.editText1);

        myDB = new DBHelper(Appo.this);

        android = findViewById(R.id.checkBox);
        angular = findViewById(R.id.checkBox1);
        java = findViewById(R.id.checkBox2);
        python = findViewById(R.id.checkBox3);
        text = findViewById(R.id.txt);
        Button btn = findViewById(R.id.getbtn);
        receiver_msg =findViewById(R.id.textView5);
        // create the get Intent object
        Intent intent = getIntent();
        String str = intent.getStringExtra("dr_name");

        // display the string into textView
        receiver_msg.setText(str);


        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(Appo.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        btnGet = findViewById(R.id.button1);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = eText.getText().toString();
                tvw.setText(txt);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result ="";
                if (android.isChecked()) {
                    result += "\n5:00 - 6:00 PM";
                }
                if (angular.isChecked()) {
                    result += "\n3:00 - 4:00 PM";
                }
                if (java.isChecked()) {
                    result += "\n1:00 - 2:00 PM";
                }
                if (python.isChecked()) {
                    result += "\n12:00 - 1:00 PM";
                }
                text.setText(result);
            }
        });

        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String time = text.getText().toString();
               String date = tvw.getText().toString();
                if (time.equals("") | date.equals("")) {
                    Toast.makeText(Appo.this, "Blank values:Fill all input fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean booking = myDB.insertData2(str, time, date);
                    if (booking) {
                        Toast.makeText(Appo.this, "Booking Confirmed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Appo.this, "Booking not confirmed", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        /*fb = findViewById(R.id.fbtn);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),fetchdata.class));
            }
        });*/
    }



        public void onCheckboxClicked(View view) {
            boolean checked = ((CheckBox) view).isChecked();
            String str="";
            // Check which checkbox was clicked
            switch(view.getId()) {
                case R.id.checkBox:
                    str = checked?"5:00 - 6:00 PM Selected":"5:00 - 6:00 PM Deselected";
                    break;
                case R.id.checkBox1:
                    str = checked?"3:00 - 4:00 PM Selected":"3:00 - 4:00 PM Deselected";
                    break;
                case R.id.checkBox2:
                    str = checked?"1:00 - 2:00 PM Selected":"1:00 - 2:00 PM Deselected";
                    break;
                case R.id.checkBox3:
                    str = checked?"12:00 - 1:00 PM Selected":"12:00 - 1:00 PM Deselected";
                    break;
            }
            Toast.makeText(Appo.this, str, Toast.LENGTH_SHORT).show();
    }

}
