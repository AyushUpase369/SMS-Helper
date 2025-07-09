package com.example.smshelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class network_error extends AppCompatActivity {

    View no_connection;
    float v = 0;
    String commingN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();// Hides action bar
        setContentView(R.layout.activity_network_error);

        commingN = getIntent().getStringExtra("Net");

        no_connection = findViewById(R.id.no_wifi_red);
        Button conn_wifi_btn = (Button) findViewById(R.id.conn_wifi_btn);
        Button conn_internet_btn = (Button) findViewById(R.id.conn_internet_btn);
        TextView Refresh_btn = (TextView) findViewById(R.id.refresh_btn);
        no_connection.setTranslationY(-100);
        no_connection.setAlpha(v);
        no_connection.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();

        conn_wifi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        });

        conn_internet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS));
            }
        });

        Refresh_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoMain.logomain.net();
                if(logoMain.logomain.network_conn.equals("yes")) {
                    if(commingN.equals("Logo"))
                        startActivity(new Intent(getApplicationContext(), logoMain.class));
                    if (commingN.equals("Login"))
                        startActivity(new Intent(getApplicationContext(), Main_Login_Signup_Activity.class));
                    if (commingN.equals("Singup"))
                        startActivity(new Intent(getApplicationContext(), Main_Login_Signup_Activity.class));
                    if(commingN.equals("Unlockmpin"))
                        startActivity(new Intent(getApplicationContext(), enter_old_m_pin.class));
                    if(commingN.equals("Loginrempin"))
                        startActivity(new Intent(getApplicationContext(), loginmpinenter.class));
                    if(commingN.equals("Singuprempin"))
                        startActivity(new Intent(getApplicationContext(), re_enter_m_pin.class));
                    if(commingN.equals("CreateNewPass"))
                        startActivity(new Intent(getApplicationContext(), create_new_password.class));
                    if(commingN.equals("dash"))
                        startActivity(new Intent(getApplicationContext(), Dashboard_screen.class));
                    if(commingN.equals("profile"))
                        startActivity(new Intent(getApplicationContext(), Dashboard_screen.class));
                    if(commingN.equals("profile_edit"))
                        startActivity(new Intent(getApplicationContext(), Dashboard_screen.class));
                    if(commingN.equals("profile_save"))
                        startActivity(new Intent(getApplicationContext(), Dashboard_screen.class));
                    if(commingN.equals("security"))
                        startActivity(new Intent(getApplicationContext(), Dashboard_screen.class));
                    if(commingN.equals("security_reset_pass"))
                        startActivity(new Intent(getApplicationContext(), Dashboard_screen.class));
                    if(commingN.equals("security_update"))
                        startActivity(new Intent(getApplicationContext(), Dashboard_screen.class));



//                    if(commingN.equals("profile"))
//                        startActivity(new Intent(getApplicationContext(), profile_textviews.class));
//                    if(commingN.equals("profile_edit"))
//                        startActivity(new Intent(getApplicationContext(), profile_textviews.class));
//                    if(commingN.equals("profile_save"))
//                        startActivity(new Intent(getApplicationContext(), Profile_screens.class));
//                    if(commingN.equals("security"))
//                        startActivity(new Intent(getApplicationContext(), security_fragment.class));
//                    if(commingN.equals("security_reset_pass"))
//                        startActivity(new Intent(getApplicationContext(), reset_password_fragment.class));
//                    if(commingN.equals("security_update"))
//                        startActivity(new Intent(getApplicationContext(), Dashboard_screen.class));


                    finish();
                }
                else{
                    Toast.makeText(network_error.this, "Please connect to Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void loadFragment(Fragment fragment) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.container, fragment);
        ft.commit();
    }
}