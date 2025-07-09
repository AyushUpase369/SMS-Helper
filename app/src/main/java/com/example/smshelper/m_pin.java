package com.example.smshelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class m_pin extends AppCompatActivity implements View.OnClickListener {
    static m_pin m_pin_obj;
    View view_1, view_2, view_3, view_4;
    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0, btn_clear;
    ArrayList<String> numbers_list = new ArrayList<>();
    String passCode ="";
    String num_1, num_2, num_3, num_4;
    float v = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_pin);
        m_pin_obj = this;
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        initializeComponents();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        super.onBackPressed();
        Toast.makeText(this, "Please Enter M-Pin", Toast.LENGTH_SHORT).show();
    }

    private void initializeComponents() {

        view_1 = findViewById(R.id.view_1);
        view_2 = findViewById(R.id.view_2);
        view_3 = findViewById(R.id.view_3);
        view_4 = findViewById(R.id.view_4);

        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_0 = findViewById(R.id.btn_0);
        btn_clear = findViewById(R.id.btn_clear);


        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_0.setOnClickListener(this);
        btn_clear.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn_1) {
            numbers_list.add("1");
            passNumber(numbers_list);
        } else if (id == R.id.btn_2) {
            numbers_list.add("2");
            passNumber(numbers_list);
        } else if (id == R.id.btn_3) {
            numbers_list.add("3");
            passNumber(numbers_list);
        } else if (id == R.id.btn_4) {
            numbers_list.add("4");
            passNumber(numbers_list);
        } else if (id == R.id.btn_5) {
            numbers_list.add("5");
            passNumber(numbers_list);
        } else if (id == R.id.btn_6) {
            numbers_list.add("6");
            passNumber(numbers_list);
        } else if (id == R.id.btn_7) {
            numbers_list.add("7");
            passNumber(numbers_list);
        } else if (id == R.id.btn_8) {
            numbers_list.add("8");
            passNumber(numbers_list);
        } else if (id == R.id.btn_9) {
            numbers_list.add("9");
            passNumber(numbers_list);
        } else if (id == R.id.btn_0) {
            numbers_list.add("0");
            passNumber(numbers_list);
        } else if (id == R.id.btn_clear) {
            numbers_list.clear();
            passNumber(numbers_list);
        }
    }


    private void passNumber(ArrayList<String> numbers_list) {
        if (numbers_list.size() == 0) {
            view_1.setBackgroundResource(R.drawable.bg_view_grey_m_pin);
            view_2.setBackgroundResource(R.drawable.bg_view_grey_m_pin);
            view_3.setBackgroundResource(R.drawable.bg_view_grey_m_pin);
            view_4.setBackgroundResource(R.drawable.bg_view_grey_m_pin);
        } else {
            switch (numbers_list.size()) {
                case 1:
                    num_1 = numbers_list.get(0);
                    view_1.setBackgroundResource(R.drawable.bg_view_blue_m_pin);
                    break;
                case 2:
                    num_2 = numbers_list.get(1);
                    view_2.setBackgroundResource(R.drawable.bg_view_blue_m_pin);
                    break;
                case 3:
                    num_3 = numbers_list.get(2);
                    view_3.setBackgroundResource(R.drawable.bg_view_blue_m_pin);
                    break;
                case 4:
                    num_4 = numbers_list.get(3);
                    view_4.setBackgroundResource(R.drawable.bg_view_blue_m_pin);
                    passCode = num_1 + num_2 + num_3 + num_4;
                    if (getPassCode().length() == 0) {
                        savePassCode(passCode);
                        Intent intent = new Intent(getApplicationContext(),re_enter_m_pin.class);
                        intent.putExtra("m-pin", Arrays.toString(passCode.getBytes()));
                        intent.putExtra("back","false");
                        startActivity(intent);
                        finish();
                    } else {
                        matchPassCode();
                    }
                    break;

            }
        }

    }

    private void matchPassCode() {
        if (getPassCode().equals(passCode)) {
            startActivity(new Intent(this, re_enter_m_pin.class));
            finish();
        } else {
            view_1.setBackgroundResource(R.drawable.bg_view_red_m_pin);
            view_2.setBackgroundResource(R.drawable.bg_view_red_m_pin);
            view_3.setBackgroundResource(R.drawable.bg_view_red_m_pin);
            view_4.setBackgroundResource(R.drawable.bg_view_red_m_pin);

            Thread timer = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(1500);
                        numbers_list.clear();
                        passNumber(numbers_list);
                        super.run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.start();
        }
    }

    private SharedPreferences.Editor savePassCode(String passCode) {

        SharedPreferences preferences = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("passcode", passCode);
        editor.commit();

        return editor;
    }

    private String getPassCode() {
        SharedPreferences preferences = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
        return preferences.getString("passcode", "");
    }
}