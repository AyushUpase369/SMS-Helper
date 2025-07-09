package com.example.smshelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class logoMain extends AppCompatActivity {

    // ✅ Constant keys (required for SharedPreferences, Intents, etc.)
    public static final String C_M_PASS = "c_m_pass";
    public static final String C_M_PIN = "c_mpin";
    public static final String GET_IN = "getin";
    public static String GET_MOB = "getmob";
    public static final String PASSCODE_PREF = "passcode_pref";
    public static final String PASSCODE_PREF_MOB = "passcode_pref_mob";

    // 🔁 Runtime values
    public static logoMain logomain;
    public String network_conn = "yes";
    public String getmobb = "";
    public String c_m_passs = "";
    public String c_mpinn = "";
    public String getinn = "";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        logomain = this;

        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.newlogo);

        net(); // Check internet

        ImageView im5 = findViewById(R.id.imageView5);
        TextView text = findViewById(R.id.textView3);

        Animation ani5 = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);
        text.startAnimation(ani5);
        Animation ani6 = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);
        im5.startAnimation(ani6);

        mAuth = FirebaseAuth.getInstance();

        // ✅ Load SharedPreferences using CONSTANT KEYS
        SharedPreferences check_mpin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        c_mpinn = check_mpin.getString(C_M_PIN, "");

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        getinn = prefs.getString(GET_IN, "");

        SharedPreferences prefs_mob = getSharedPreferences(PASSCODE_PREF_MOB, Context.MODE_PRIVATE);
        getmobb = prefs_mob.getString(GET_MOB, "");

        SharedPreferences check_m_pass = getSharedPreferences(PASSCODE_PREF, Context.MODE_PRIVATE);
        c_m_passs = check_m_pass.getString(C_M_PASS, "");

        new Handler().postDelayed(() -> {
            if (network_conn.equals("yes")) {
                if (getinn.equals("true") && c_mpinn.equals("true")) {
                    startActivity(new Intent(getApplicationContext(), enter_old_m_pin.class));
                } else {
                    startActivity(new Intent(getApplicationContext(), Onboarding_screens.class));
                }
            } else {
                Intent LoginI = new Intent(getApplicationContext(), network_error.class);
                LoginI.putExtra("Net", "Logo");
                startActivity(LoginI);
            }
            finish();
        }, 3000);
    }

    private boolean isConnected(logoMain logoMain) {
        ConnectivityManager connectivityManager = (ConnectivityManager) logoMain.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return (wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected());
    }

    public void net() {
        network_conn = isConnected(this) ? "yes" : "no";
    }

    @Override
    public void onBackPressed() {
        // Disabled back press on logo screen
        super.onBackPressed();
    }
}
