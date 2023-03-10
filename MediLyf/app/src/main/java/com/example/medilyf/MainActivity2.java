package com.example.medilyf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medilyf.databinding.ActivityMain2Binding;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ActivityMain2Binding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[] imageId = {R.drawable.phy2,R.drawable.phy2,R.drawable.physician,R.drawable.physician,R.drawable.phy2,R.drawable.physician,R.drawable.phy2,
                R.drawable.phy2,R.drawable.phy2,R.drawable.phy2,R.drawable.phy2,R.drawable.phy2,R.drawable.phy2,R.drawable.phy2,R.drawable.physician,R.drawable.phy2,R.drawable.phy2};
        String[] name = {"Dr. Anil Desousa","Dr. Nitin Patki","Dr. Kavita Patil","Dr. Jaya Kochure","Dr. Vijay Bang","Dr. Pravina Shah","Dr. Hemant Sant","Dr. Vinay Dhir","Dr. Aruna Bhave",
                "Dr. Arvind Varma","Dr. Manish Dane","Dr. Shirish Shah","Dr. S Rai","Dr. Suresh Shah","Dr. Nalini Kilara","Dr. MH Kamat","Dr. Shanish pure"};
        String[] lastMessage = {"Cardiologist","Cardiologist","ENT Specialist","ENT Specialist","Cardiologist","Neurologist","Neurologist",
                "Gastroenterologist","Gastroenterologist","Orthopedic","Orthopedic","Pulmonologist","Pulmonologist","Oncologist","Oncologist","Urologist","Urologist"};
        String[] lastmsgTime = {"Pune","Pune","Pune","Pune","Pune","Mumbai","Mumbai",
                "Mumbai","Mumbai","Pune","Pune","Mumbai","Mumbai","Mumbai","Pune","Mumbai","Pune"};
        String[] phoneNo = {"7656610000","9999043232","4264126754","9087654310","7834354323","9876543211","5434432343",
                "9439043232","7534354323","6545543211","7654432343","9963781527","9035182649","1265782398","7620891629","8765394210","9965120976"};
        String[] country = {"Jupitar Hospital,Pune","Jupitar Hospital,Pune","Jupitar Hospital,Pune","Aditya Birla Memorial Hospital Pune","CrediHealth, Pune","Fortis Hospital,Mulund,Mumbai","Jupitar Hospital,Pune","S L Raheja Hospital,Mahim,Mumbai",
                "Fortis Hospital,Mulund,Mumbai","Jupitar Hospital,Pune","Aditya Birla Memorial Hospital Pune","Navnathi Hospital,Vile Parle,Mumbai","Kokilaben Dhirubai Ambani Hospital,Mumbai",
                "Navnathi Hospital,Vile Parle,Mumbai","Sanjivani Hospital,Pune","Jaslok Hospital,Mumbai","Ruby Hospital,Pune"};

        ArrayList<User> userArrayList = new ArrayList<>();

        for(int i = 0;i< imageId.length;i++){

            User user = new User(name[i],lastMessage[i],lastmsgTime[i],phoneNo[i],country[i],imageId[i]);
            userArrayList.add(user);

        }


        ListAdapter listAdapter = new ListAdapter(MainActivity2.this,userArrayList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(MainActivity2.this,UserActivity.class);
                i.putExtra("name",name[position]);
                i.putExtra("phone",phoneNo[position]);
                i.putExtra("country",country[position]);
                i.putExtra("imageid",imageId[position]);
                startActivity(i);

            }
        });

    }
}