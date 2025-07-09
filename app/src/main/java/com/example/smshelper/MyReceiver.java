package com.example.smshelper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.Objects;

public class MyReceiver extends BroadcastReceiver {
    SmsManager sms = SmsManager.getDefault();
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    String msg, phoneNO;
    String passW;
    String myserr="0";
    boolean go = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences myser = context.getSharedPreferences("myser", Context.MODE_PRIVATE);
        myserr=myser.getString("myser","");
        if(Objects.equals(myserr, "true")){
            go=true;
        }
        SharedPreferences preferences_un = context.getSharedPreferences("passcode_pref_un", Context.MODE_PRIVATE);
        passW = preferences_un.getString("passcode", "");
        if(go) {
            if (intent.getAction().equals(SMS_RECEIVED)) {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    final SmsMessage[] messages = new SmsMessage[pdus.length];
                    for (int i = 0; i < pdus.length; i++) {
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        msg = messages[i].getMessageBody();
                        phoneNO = messages[i].getOriginatingAddress();
                    }
                    int c = 0;
                    String first3char = " ";
                    String splmsg = msg.toLowerCase();
                    String newMsg = splmsg.replaceFirst(" ", "/");
                    String[] pass1 = newMsg.split("/");
                    String password = pass1[0];
                    String RData = pass1[1];
                    for (int i = 0; i < RData.length(); i++) {
                        if (RData.charAt(i) == ' ') {
                            c++;
                        }
                    }
                    if (password.equals(passW)) {
                        if (RData.length() >= 3 && c >= 1) {
                            first3char = RData.substring(0, 4);
                            System.out.println(RData.substring(0, 4));
                            if (first3char.equals("get_") || first3char.equals("sms_")) {
                                String fsplit = RData.replaceFirst(" ", "/");
                                String[] ShowN = fsplit.split("/");
                                String extr = fsplit.replaceAll(" ", "");
                                String[] Lsplit = extr.split("/");
                                String f = Lsplit[0];
                                String s = Lsplit[1];
                                String FSMS = f.toLowerCase();
                                String SSMS = s.toLowerCase();
                                System.out.println(FSMS + "" + SSMS);
                                if (FSMS.equals("sms_") && SSMS.equals("help")) {
                                    sms.sendTextMessage(phoneNO, null, "For Contact: <M-Pin> get_Contact \n<contactName>", null, null);
                                    sms.sendTextMessage(phoneNO, null, "For Location: <M-Pin> get_Location Now", null, null);
                                    sms.sendTextMessage(phoneNO, null, "For Mute/Unmute: <M-Pin> get_changeProfile Mute/Unmute", null, null);
                                    sms.sendTextMessage(phoneNO, null, "For Ring Phone: <M-Pin> get_ring Start/Stop", null, null);
                                    sms.sendTextMessage(phoneNO, null, "For Stop Helper: <M-Pin> sms_helper Stop", null, null);
                                    sms.sendTextMessage(phoneNO, null, "After Using SMS Helper Dont forget to delete Messages Or Stop SMS Helper By Stop Helper Command", null, null);
                                } else if (FSMS.equals("get_contact")) {
                                    sms.sendTextMessage(phoneNO, null, ShowN[1] + ": " + contactsget(SSMS, FSMS), null, null);
                                } else if (FSMS.equals("get_location") && SSMS.equals("now")) {
                                    Locationn(phoneNO);
                                } else if (FSMS.equals("get_changeprofile") && SSMS.equals("mute") || FSMS.equals("get_changeprofile") && SSMS.equals("unmute")) {
                                    sms.sendTextMessage(phoneNO, null, ChangeProfile(SSMS) + " To " + SSMS, null, null);
                                } else if (FSMS.equals("get_ring") && SSMS.equals("start") || FSMS.equals("get_ring") && SSMS.equals("stop")) {
                                    sms.sendTextMessage(phoneNO, null, Ring(SSMS), null, null);
                                } else if (FSMS.equals("sms_helper") && SSMS.equals("stop")) {
                                    sms.sendTextMessage(phoneNO, null, "Stop Successful \nGet Conform you deleted all Message from helper device", null, null);

                                    SharedPreferences pref_stop = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                                    SharedPreferences.Editor editor_stop = pref_stop.edit();
                                    editor_stop.putString("stop", "true");
                                    editor_stop.apply();

                                    SharedPreferences check_mpin = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                                    SharedPreferences.Editor editor_mpin = check_mpin.edit();
                                    editor_mpin.putString(logoMain.C_M_PIN, "");
                                    editor_mpin.apply();

                                    SharedPreferences prefs_getin = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                                    SharedPreferences.Editor editor_getin = prefs_getin.edit();
                                    editor_getin.putString(logoMain.GET_IN, "");
                                    editor_getin.apply();

                                    SharedPreferences check_m_pass = context.getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor_check_m_pass = check_m_pass.edit();
                                    editor_check_m_pass.putString(logoMain.C_M_PASS, "");
                                    editor_check_m_pass.apply();

                                    SharedPreferences prefs_first = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                                    SharedPreferences.Editor editor_first = prefs_first.edit();
                                    editor_first.putBoolean("firstTime", false);
                                    editor_first.apply();

                                    SharedPreferences sharedPreferences = context.getSharedPreferences("save", context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putBoolean("value", false);
                                    editor.apply();

                                    context.stopService(new Intent(context, MyService.class));
                                } else {
                                    sms.sendTextMessage(phoneNO, null, "Wrong Syntax Type 'Sms_ Help' For Correct Syntax", null, null);
                                }
                            } else {
                                // do nothing
                            }
                        }
                    }
                }
            }
        }
    }

    public String contactsget(String N, String No) {
        return Dashboard_screen.getInstance().getPhoneContacts(N, No);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public String ChangeProfile(String Pro) {
        return Dashboard_screen.getInstance().ChangeProfileMain(Pro);
    }

    public String Ring(String state) {
        return Dashboard_screen.getInstance().ring(state);
    }

    public double[] Locationn(String phoneNO) {
        return Dashboard_screen.getInstance().getLocation(phoneNO);
    }
}