package com.example.smshelper;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;

public class Dashboard_screen extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    Dialog logoutDialog;
    TextView logout_txt;
    ImageView logout_img;
    private String[] PERMISSIONS;
    static Dashboard_screen main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_screen);
        logoMain.logomain.net();
        if (logoMain.logomain.network_conn.equals("yes")) {
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            loadFragment(new home_screen());
            main = this;
            toolbar = findViewById(R.id.toolbar);
            drawerLayout = findViewById(R.id.drawerlayout);
            navigationView = findViewById(R.id.navigation_view);
            logout_txt = findViewById(R.id.txt_logout);
            logout_img = findViewById(R.id.img_logout);

            logout_txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    logoutDialog = new Dialog(Dashboard_screen.this);
                    logoutDialog.setContentView(R.layout.logout_dialog);
                    logoutDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.bg_logout));
                    logoutDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    logoutDialog.setCancelable(false);
                    logoutDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                    logoutDialog.getWindow().setLayout(550, 400);
                    TextView yes = (TextView) logoutDialog.findViewById(R.id.btn_yes);
                    TextView no = (TextView) logoutDialog.findViewById(R.id.btn_no);

                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent logA = new Intent(getApplicationContext(), Main_Login_Signup_Activity.class);
                            SharedPreferences check_mpin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor_mpin = check_mpin.edit();
                            editor_mpin.putString(	logoMain.C_M_PIN, "");
                            editor_mpin.apply();

                            SharedPreferences prefs_getin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor_getin = prefs_getin.edit();
                            editor_getin.putString(logoMain.GET_IN, "");
                            editor_getin.apply();

                            SharedPreferences check_m_pass = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor_check_m_pass = check_m_pass.edit();
                            editor_check_m_pass.putString(logoMain.C_M_PASS, "");
                            editor_check_m_pass.apply();

                            SharedPreferences prefs_first = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor_first = prefs_first.edit();
                            editor_first.putBoolean("firstTime", false);
                            editor_first.apply();

                            SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                            editor.putBoolean("value", false);
                            editor.apply();

                            stopService(new Intent(Dashboard_screen.this, MyService.class));

                            startActivity(logA);
                            finish();
                            logoutDialog.dismiss();

                        }
                    });

                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            logoutDialog.dismiss();
                        }
                    });

                    logoutDialog.show();

                }
            });

            logout_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    logoutDialog = new Dialog(Dashboard_screen.this);
                    logoutDialog.setContentView(R.layout.logout_dialog);
                    logoutDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.bg_logout));
                    logoutDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    logoutDialog.setCancelable(false);
                    logoutDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                    logoutDialog.getWindow().setLayout(550, 400);
                    TextView yes = (TextView) logoutDialog.findViewById(R.id.btn_yes);
                    TextView no = (TextView) logoutDialog.findViewById(R.id.btn_no);


                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent logA = new Intent(getApplicationContext(), Main_Login_Signup_Activity.class);
                            SharedPreferences check_mpin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor_mpin = check_mpin.edit();
                            editor_mpin.putString(	logoMain.C_M_PIN, "");
                            editor_mpin.apply();

                            SharedPreferences prefs_getin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor_getin = prefs_getin.edit();
                            editor_getin.putString(logoMain.GET_IN, "");
                            editor_getin.apply();

                            SharedPreferences check_m_pass = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor_check_m_pass = check_m_pass.edit();
                            editor_check_m_pass.putString(logoMain.C_M_PASS, "");
                            editor_check_m_pass.apply();

                            SharedPreferences prefs_first = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor_first = prefs_first.edit();
                            editor_first.putBoolean("firstTime", false);
                            editor_first.apply();

                            SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                            editor.putBoolean("value", false);
                            editor.apply();

                            stopService(new Intent(Dashboard_screen.this, MyService.class));

                            startActivity(logA);
                            finish();
                            logoutDialog.dismiss();

                        }
                    });

                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            logoutDialog.dismiss();
                        }
                    });

                    logoutDialog.show();

                }
            });

            View header = navigationView.getHeaderView(0);
            ToggleButton tb = (ToggleButton) header.findViewById(R.id.toggleButton);
            SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
            tb.setChecked(sharedPreferences.getBoolean("value", false));
            PackageManager pm = Dashboard_screen.this.getPackageManager();
            ComponentName componentName = new ComponentName(Dashboard_screen.this, MyReceiver.class);
            pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);
            PERMISSIONS = new String[]{

                    Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.SEND_SMS,

                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS,

                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            };
            if (!hasPermissions(Dashboard_screen.this, PERMISSIONS)) {

                ActivityCompat.requestPermissions(Dashboard_screen.this, PERMISSIONS, 1);
            }

            tb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tb.isChecked()) {
                        SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                        editor.putBoolean("value", true);
                        editor.apply();
                        tb.setChecked(true);
                        PackageManager pm = Dashboard_screen.this.getPackageManager();
                        ComponentName componentName = new ComponentName(Dashboard_screen.this, MyReceiver.class);
                        pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                                PackageManager.DONT_KILL_APP);
                        Toast.makeText(getApplicationContext(), "Helper starting.....", Toast.LENGTH_SHORT).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            startForegroundService(new Intent(Dashboard_screen.this, MyService.class));
                        } else {
                            startService(new Intent(Dashboard_screen.this, MyService.class));
                        }

                    } else {
                        SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                        editor.putBoolean("value", false);
                        editor.apply();
                        tb.setChecked(false);
                        stopService(new Intent(Dashboard_screen.this, MyService.class));
                        PackageManager pm = Dashboard_screen.this.getPackageManager();
                        ComponentName componentName = new ComponentName(Dashboard_screen.this, MyReceiver.class);
                        pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                                PackageManager.DONT_KILL_APP);
                        Toast.makeText(getApplicationContext(), "Helper Stop's", Toast.LENGTH_SHORT).show();
                    }
                }
            });


            setSupportActionBar(toolbar);
            SharedPreferences prefs_mob = getSharedPreferences("passcode_pref_mob", Context.MODE_PRIVATE);
            logoMain.GET_MOB = prefs_mob.getString(	logoMain.GET_MOB, "");

            toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            navigationView.getMenu().getItem(0).setActionView(R.layout.menu_end_icon);
            navigationView.getMenu().getItem(1).setActionView(R.layout.menu_end_icon);
            navigationView.getMenu().getItem(2).setActionView(R.layout.menu_end_icon);
            navigationView.getMenu().getItem(3).setActionView(R.layout.menu_end_icon);
            navigationView.getMenu().getItem(4).setActionView(R.layout.menu_end_icon);
            navigationView.getMenu().getItem(5).setActionView(R.layout.menu_end_icon);

            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();

                    if (id == R.id.home_menu) {

                        loadFragment(new home_screen());

                    } else if (id == R.id.profile_menu) {

                        logoMain.logomain.net();
                        if (logoMain.logomain.network_conn.equals("yes")) {

                            loadFragment(new profile_textviews());

                        } else {
                            Intent SingupI = new Intent(getApplicationContext(), network_error.class);
                            SingupI.putExtra("Net", "profile");
                            startActivity(SingupI);
                            finish();
                        }

                    } else if (id == R.id.permissions_menu) {

                        loadFragment(new Permission_screen());

                    } else if (id == R.id.security_menu) {

                        logoMain.logomain.net();
                        if (logoMain.logomain.network_conn.equals("yes")) {

                            loadFragment(new security_fragment());

                        } else {
                            Intent SingupI = new Intent(getApplicationContext(), network_error.class);
                            SingupI.putExtra("Net", "security");
                            startActivity(SingupI);
                            finish();
                        }

                    } else if (id == R.id.about_us_menu) {

                        Toast.makeText(getApplicationContext(), "About Us Menu", Toast.LENGTH_SHORT).show();

                    } else if (id == R.id.Help_menu) {

                        Toast.makeText(getApplicationContext(), "Help Menu", Toast.LENGTH_SHORT).show();
                    }

                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
            });
        } else {
            Intent LoginI = new Intent(getApplicationContext(), network_error.class);
            LoginI.putExtra("Net", "dash");
            startActivity(LoginI);
            finish();
        }

    }

    // Alert Dialog code *******************************************************************

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.logout) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard_screen.this);
//            builder.setMessage("Do You Want To Exit ?");
//            builder.setCancelable(true);
//
//            builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                    //Code of logout function will be written here ********************************************
//                    finish();
//                }
//            });
//
//            builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.cancel();
//                }
//            });
//
//            AlertDialog alertDialog = builder.create();
//            alertDialog.show();
//        }
//
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {

            logoutDialog = new Dialog(Dashboard_screen.this);
            logoutDialog.setContentView(R.layout.logout_dialog);
            logoutDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.bg_logout));
            logoutDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            logoutDialog.setCancelable(false);
            logoutDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
            logoutDialog.getWindow().setLayout(550, 400);
            TextView yes = (TextView) logoutDialog.findViewById(R.id.btn_yes);
            TextView no = (TextView) logoutDialog.findViewById(R.id.btn_no);


            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent logA = new Intent(getApplicationContext(), Main_Login_Signup_Activity.class);
                    SharedPreferences check_mpin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor_mpin = check_mpin.edit();
                    editor_mpin.putString(	logoMain.C_M_PIN, "");
                    editor_mpin.apply();

                    SharedPreferences prefs_getin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor_getin = prefs_getin.edit();
                    editor_getin.putString(logoMain.GET_IN, "");
                    editor_getin.apply();

                    SharedPreferences check_m_pass = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor_check_m_pass = check_m_pass.edit();
                    editor_check_m_pass.putString(logoMain.C_M_PASS, "");
                    editor_check_m_pass.apply();

                    SharedPreferences prefs_first = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor_first = prefs_first.edit();
                    editor_first.putBoolean("firstTime", false);
                    editor_first.apply();

                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);
                    editor.apply();

                    stopService(new Intent(Dashboard_screen.this, MyService.class));

                    startActivity(logA);
                    finish();
                    logoutDialog.dismiss();

                }
            });

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    logoutDialog.dismiss();
                }
            });

            logoutDialog.show();
        }
        return true;
    }


    private boolean hasPermissions(Context context, String... PERMISSIONS) {

        if (context != null && PERMISSIONS != null) {

            for (String permission : PERMISSIONS) {

                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }

        return true;
    }

    public static Dashboard_screen getInstance() {
        return main;
    }

    public String getPhoneContacts(String SName, String FNo) {
        String foundNo = "Not Found";
        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                @SuppressLint("Range") String ContactNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String CName = contactName.toLowerCase();
                String LName = CName.replaceAll(" ", "");
                String CNo = ContactNum.toLowerCase();
                if (LName.equals(SName)) {
                    foundNo = CNo;
                }
            }
        }
        return foundNo;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public String ChangeProfileMain(String proMa) {
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        String Work;
        if (proMa.equals("mute")) {
            audioManager.adjustVolume(AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI);
            Work = "Profile Changed Successfully";
        } else if (proMa.equals("unmute")) {
            audioManager.adjustVolume(AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI);
            Work = "Profile Changed Successfully";
        } else {
            Work = "Wrong Syntax";
        }
        return Work;
    }

    public String ring(String lr) {
        String ringed;
        MediaPlayer mp;
        mp = MediaPlayer.create(Dashboard_screen.this, R.raw.denofo);
        if (lr.equals("start")) {
            mp.start();
            ringed = "Ringing.......";
        } else if (lr.equals("stop")) {
            mp.stop();
            ringed = "Ringing Stop";
        } else {
            ringed = "Unable to Ring";
        }
        return ringed;
    }

    public double[] getLocation(String pn) {
        SmsManager sms = SmsManager.getDefault();
        final double[] Lastl = new double[2];
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getApplication().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            double lat = location.getLatitude();
                            double longt = location.getLongitude();
                            Lastl[0] = lat;
                            for (int i = 1; i <= 1; i++) {
                                Lastl[i] = longt;
                            }
                            sms.sendTextMessage(pn, null, "Your Mobile Location is:\n " + Lastl[0] + " , " + Lastl[1], null, null);
                        }
                    }
                });
            }
        }
        return Lastl;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void loadFragment(Fragment fragment) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.container, fragment);
        ft.commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    if (grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                        SharedPreferences pref_RWS_SMS = getSharedPreferences("pref_RWS_SMS", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor_RWS_SMS = pref_RWS_SMS.edit();
                        editor_RWS_SMS.putString("SMS", "true");
                        editor_RWS_SMS.apply();
                    }
                }
            } else {
                SharedPreferences pref_RWS_SMS = getSharedPreferences("pref_RWS_SMS", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor_RWS_SMS = pref_RWS_SMS.edit();
                editor_RWS_SMS.putString("SMS", "false");
                editor_RWS_SMS.apply();
            }

            if (grantResults[3] == PackageManager.PERMISSION_GRANTED) {
                if (grantResults[4] == PackageManager.PERMISSION_GRANTED) {
                    SharedPreferences pref_RW_CON = getSharedPreferences("pref_RW_CON", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor_RW_CON = pref_RW_CON.edit();
                    editor_RW_CON.putString("CONTACTS", "true");
                    editor_RW_CON.apply();
                }
            } else {
                SharedPreferences pref_RW_CON = getSharedPreferences("pref_RW_CON", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor_RW_CON = pref_RW_CON.edit();
                editor_RW_CON.putString("CONTACTS", "false");
                editor_RW_CON.apply();
            }

            if (grantResults[5] == PackageManager.PERMISSION_GRANTED) {
                if (grantResults[6] == PackageManager.PERMISSION_GRANTED) {
                    SharedPreferences pref_A_LOC = getSharedPreferences("pref_A_LOC", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor_A_LOC = pref_A_LOC.edit();
                    editor_A_LOC.putString("LOCATION", "true");
                    editor_A_LOC.apply();
                }
            } else {
                SharedPreferences pref_A_LOC = getSharedPreferences("pref_A_LOC", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor_A_LOC = pref_A_LOC.edit();
                editor_A_LOC.putString("LOCATION", "false");
                editor_A_LOC.apply();
            }
        }
    }
}