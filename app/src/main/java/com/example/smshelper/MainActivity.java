package com.example.smshelper;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
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
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    private String[] PERMISSIONS;
    static MainActivity main;
    private String FinalPin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main = this;
        ToggleButton OnOffBtn = (ToggleButton) findViewById(R.id.toggleButton);
        SharedPreferences sharedPreferences = getSharedPreferences("save",MODE_PRIVATE);
        OnOffBtn.setChecked(sharedPreferences.getBoolean("value",false));
        PackageManager pm  = MainActivity.this.getPackageManager();
        ComponentName componentName = new ComponentName(MainActivity.this, MyReceiver.class);
        pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
        PERMISSIONS = new String[] {

                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_SMS,
                Manifest.permission.SEND_SMS,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        if (!hasPermissions(MainActivity.this,PERMISSIONS)) {

            ActivityCompat.requestPermissions(MainActivity.this,PERMISSIONS,1);
        }

        OnOffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(OnOffBtn.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    OnOffBtn.setChecked(true);
                    PackageManager pm  = MainActivity.this.getPackageManager();
                    ComponentName componentName = new ComponentName(MainActivity.this, MyReceiver.class);
                    pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                            PackageManager.DONT_KILL_APP);
                    Toast.makeText(getApplicationContext(), "Helper starting.....", Toast.LENGTH_SHORT).show();

                    Log.d("Check: ", "Receiver Started");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        startForegroundService(new Intent(MainActivity.this, MyService.class));
                    } else {
                        startService(new Intent(MainActivity.this, MyService.class));
                    }

                }else{
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    OnOffBtn.setChecked(false);
                    stopService(new Intent(MainActivity.this,MyService.class));
                    PackageManager pm  = MainActivity.this.getPackageManager();
                    ComponentName componentName = new ComponentName(MainActivity.this, MyReceiver.class);
                    pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                            PackageManager.DONT_KILL_APP);
                    Toast.makeText(getApplicationContext(), "Helper Stop's", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean hasPermissions(Context context, String... PERMISSIONS) {

        if (context != null && PERMISSIONS != null) {

            for (String permission: PERMISSIONS){

                if (ActivityCompat.checkSelfPermission(context,permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }

        return true;
    }
    public static MainActivity getInstance(){
        return   main;
    }
    public String getPhoneContacts(String SName, String FNo){
        String foundNo = "Not Found";
        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = contentResolver.query(uri,null,null,null,null);
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                @SuppressLint("Range") String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                @SuppressLint("Range") String ContactNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String CName = contactName.toLowerCase();
                String LName = CName.replaceAll(" ","");
                String CNo = ContactNum.toLowerCase();
                if(LName.equals(SName)){
                    foundNo = CNo;
                }
            }
        }
        return foundNo;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public String ChangeProfileMain(String proMa){
        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        String Work;
        if(proMa.equals("mute")){
            audioManager.adjustVolume(AudioManager.ADJUST_MUTE,AudioManager.FLAG_SHOW_UI);
            Work = "Profile Changed Successfully";
        }else if(proMa.equals("unmute")){
            audioManager.adjustVolume(AudioManager.ADJUST_UNMUTE,AudioManager.FLAG_SHOW_UI);
            Work = "Profile Changed Successfully";
        }else{
            Work="Wrong Syntax";
        }
        return Work;
    }
    public String ring(String lr){
        String ringed;
        MediaPlayer mp;
        mp = MediaPlayer.create(MainActivity.this,R.raw.denofo);
        if(lr.equals("start")){
            mp.start();
            ringed="Ringing.......";
        }else if(lr.equals("stop")){
            mp.stop();
            ringed="Ringing Stop";
        }else{
            ringed="Unable to Ring";
        }
        return ringed;
    }
    public double[] getLocation(String pn){
        SmsManager sms=SmsManager.getDefault();
        final double[] Lastl = new double[2];
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(getApplication().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null ){
                            double lat = location.getLatitude();
                            double longt= location.getLongitude();
                            Lastl[0] =lat;
                            for(int i=1;i<=1;i++){
                                Lastl[i]=longt;
                            }
                            sms.sendTextMessage(pn,null,"Your Mobile Location is: "+Lastl[0]+" , "+Lastl[1],null,null);
                        }
                    }
                });
            }
        }
        return Lastl;
    }
    public void settingPin(String lpin){
        FinalPin=lpin;
    }
}