package com.example.smshelper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class loginmpinenter extends AppCompatActivity implements View.OnClickListener {

    View view_1, view_2, view_3, view_4;
    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0, btn_clear;
    ArrayList<String> numbers_list = new ArrayList<>();
    String passCode = "", m_pin;
    String myserr="0";
    String num_1, num_2, num_3, num_4;
    ProgressDialog progressDialog;
    DatabaseReference rootDatabaseref;
    String mob_pref;
    String lMp;
    static loginmpinenter lme;
    float v = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_enter_mpin);
        rootDatabaseref = FirebaseDatabase.getInstance().getReference().child("data-of-user");
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        initializeComponents();
        m_pin = getIntent().getStringExtra("m-pin");
        lme = this;
        ImageView backIm = (ImageView) findViewById(R.id.btn_back_otp);

        SharedPreferences mobile_login = getSharedPreferences("mobile_login", Context.MODE_PRIVATE);
        mob_pref = mobile_login.getString("mob", "");
        lMp = getIntent().getStringExtra("security");

        backIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("passcode", "");
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), loginmpin.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Please Enter M-Pin", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("passcode", "");
        editor.commit();
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
                    if (getPassCode().equals(m_pin)) {
                        savePassCode(passCode);
                        startActivity(new Intent(this, Onboarding_screens.class));
                        finish();
                    } else {
                        matchPassCode();
                    }
            }
        }

    }

    private void matchPassCode() {
        if (getPassCode().equals(passCode)) {
            String mobnotxtinmob = mob_pref;
            String m_pintxt = passCode;
            HashMap hashMap = new HashMap();
            hashMap.put("m_pin", m_pintxt);
            rootDatabaseref.child(mobnotxtinmob).updateChildren(hashMap);

            SharedPreferences preferences_un = getSharedPreferences("passcode_pref_un", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor_un = preferences_un.edit();
            editor_un.putString("passcode", passCode);
            editor_un.apply();

            SharedPreferences check_mpin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor_mpin = check_mpin.edit();
            editor_mpin.putString(logoMain.C_M_PIN, "true");
            editor_mpin.apply();

            SharedPreferences prefs_getin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor_getin = prefs_getin.edit();
            editor_getin.putString(logoMain.GET_IN, "true");
            editor_getin.apply();

            SharedPreferences prefs_first = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor_first = prefs_first.edit();
            editor_first.putBoolean("firstTime", true);
            editor_first.apply();

            SharedPreferences check_m_pass = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor_check_m_pass = check_m_pass.edit();
            editor_check_m_pass.putString(	logoMain.C_M_PASS, passCode);
            editor_check_m_pass.apply();

            SharedPreferences prefs_mob = getSharedPreferences("passcode_pref_mob", Context.MODE_PRIVATE);
            SharedPreferences.Editor eprefs_mob = prefs_mob.edit();
            eprefs_mob.putString(	logoMain.GET_MOB, mobnotxtinmob);
            eprefs_mob.apply();

            SharedPreferences myser = getSharedPreferences("myser", Context.MODE_PRIVATE);
            myserr=myser.getString("myser","");
            if(Objects.equals(lMp, "yes")) {
                Log.i("hii","bye");
                if (Objects.equals(myserr, "true")) {
                    Log.i("hii","true");
                    SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("value", true);
                    editor.apply();
                }
                if (Objects.equals(myserr, "false")) {
                    Log.i("hii","false");
                    SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("value", false);
                    editor.apply();
                }
            }

            progressdialog();
            startActivity(new Intent(getApplicationContext(), Dashboard_screen.class));
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

    public SharedPreferences.Editor savePassCode(String passCode) {

        SharedPreferences preferences = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("passcode", passCode);
        editor.apply();
        return editor;
    }

    public String getPassCode() {
        SharedPreferences preferences = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
        return preferences.getString("passcode", "");
    }

    private void progressdialog() {

        progressDialog = new ProgressDialog(this);

        progressDialog.show();

        progressDialog.setContentView(R.layout.progress_dialog);

        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        progressDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
    }

}