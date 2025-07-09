package com.example.smshelper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.smshelper.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class enter_old_m_pin extends AppCompatActivity implements View.OnClickListener {

    static enter_old_m_pin eomp;

    View view_1, view_2, view_3, view_4;
    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0, btn_clear;
    ArrayList<String> numbers_list = new ArrayList<>();
    String passCode = "";
    String usernamefire;
    String emailidfire;
    String num_1, num_2, num_3, num_4;
    float v = 0;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference("data-of-user");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_old_mpin);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        eomp = this;
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        initializeComponents();
        TextView loginA=(TextView) findViewById(R.id.LoginAgain);
        TextView forget_mpin=(TextView) findViewById(R.id.forgin_mpin);

        loginA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logA=new Intent(getApplicationContext(),Main_Login_Signup_Activity.class);
                SharedPreferences check_mpin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor_mpin = check_mpin.edit();
                editor_mpin.putString(logoMain.C_M_PIN,"");
                editor_mpin.apply();

                SharedPreferences prefs_getin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor_getin = prefs_getin.edit();
                editor_getin.putString(logoMain.GET_IN,"");
                editor_getin.apply();

                SharedPreferences check_m_pass = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor_check_m_pass = check_m_pass.edit();
                editor_check_m_pass.putString(	logoMain.C_M_PASS,"");
                editor_check_m_pass.apply();

                SharedPreferences prefs_first = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor_first = prefs_first.edit();
                editor_first.putBoolean("firstTime", false);
                editor_first.apply();
                startActivity(logA);
                finish();

                SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("value", false);
                editor.apply();
            }
        });
        forget_mpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logA=new Intent(getApplicationContext(),Main_Login_Signup_Activity.class);
                SharedPreferences check_mpin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor_mpin = check_mpin.edit();
                editor_mpin.putString(logoMain.C_M_PIN,"");
                editor_mpin.apply();

                SharedPreferences prefs_getin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor_getin = prefs_getin.edit();
                editor_getin.putString(logoMain.GET_IN,"");
                editor_getin.apply();

                SharedPreferences check_m_pass = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor_check_m_pass = check_m_pass.edit();
                editor_check_m_pass.putString(	logoMain.C_M_PASS,"");
                editor_check_m_pass.apply();

                SharedPreferences prefs_first = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor_first = prefs_first.edit();
                editor_first.putBoolean("firstTime", false);
                editor_first.apply();
                startActivity(logA);
                finish();

                SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("value", false);
                editor.apply();
            }
        });
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
                    logoMain.logomain.net();
                    if(logoMain.logomain.network_conn.equals("yes")) {
                        passCode = num_1 + num_2 + num_3 + num_4;
                        matchPassCode();
                    }
                    else{
                        Intent LoginI = new Intent(getApplicationContext(),network_error.class);
                        LoginI.putExtra("Net","Unlockmpin");
                        startActivity(LoginI);
                        finish();
                    }
                    break;
            }
        }
    }

    /// password get from loginminenter

    private void matchPassCode() {
        if (logoMain.logomain.c_m_passs.equals(passCode)) {
            String numberpref = logoMain.logomain.getmobb;
            SharedPreferences preferences_un = getSharedPreferences("passcode_pref_un", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor_un = preferences_un.edit();
            editor_un.putString("passcode", passCode);
            editor_un.apply();
            final String mobile_number = numberpref;

            Query check_number = databaseReference.orderByChild("mobilenum").equalTo(mobile_number);

            check_number.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        usernamefire = snapshot.child(mobile_number).child("username").getValue(String.class);
                        emailidfire = snapshot.child(mobile_number).child("email").getValue(String.class);
                        startActivity(new Intent(enter_old_m_pin.this, Dashboard_screen.class));
                        finish();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

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

    private SharedPreferences.Editor savePassCodee(String passCode) {

        SharedPreferences preferences_un = getSharedPreferences("passcode_pref_un", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor_un = preferences_un.edit();
        editor_un.putString("passcode", passCode);
        editor_un.apply();

        return editor_un;
    }

    public String getPassCodee() {
        SharedPreferences preferences = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
        return preferences.getString(logoMain.C_M_PASS, "");
    }
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        super.onBackPressed();
        Toast.makeText(this, "Please Enter M-Pin", Toast.LENGTH_SHORT).show();
    }
}