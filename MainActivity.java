package com.example.facebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

        Button btn1,btn2,btn3,btn4;

       ViewPager viewPager;
       TabLayout tabLayout;

       MyFragmentAdapter adapter;

        protected void onCreate(Bundle savedInstanceState){

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            viewPager = findViewById(R.id.fragment_pager);
            tabLayout=findViewById(R.id.tab1);

            adapter = new MyFragmentAdapter(getSupportFragmentManager());

           viewPager.setAdapter(adapter);

           tabLayout.setupWithViewPager(viewPager);

           setContentView(getFragment2());
        }

    protected int getFragment2() {
        return 0;
    }


}

