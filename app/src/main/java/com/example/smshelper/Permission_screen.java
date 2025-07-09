package com.example.smshelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class Permission_screen extends Fragment {
    ImageView sms_img, contact_img, location_img;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_permissions, container, false);
        SharedPreferences pref_RWS_SMS = getContext().getSharedPreferences("pref_RWS_SMS", Context.MODE_PRIVATE);
        SharedPreferences pref_RW_CON = getContext().getSharedPreferences("pref_RW_CON", Context.MODE_PRIVATE);
        SharedPreferences pref_A_LOC = getContext().getSharedPreferences("pref_A_LOC", Context.MODE_PRIVATE);

//        Toast.makeText(getContext(), pref_RWS_SMS.getString("SMS", "")+" "+pref_RW_CON.getString("CONTACTS", "")+" "+
//                pref_A_LOC.getString("LOCATION", ""), Toast.LENGTH_SHORT).show();

        boolean sms1 = true;
        boolean contact1 = true;
        boolean location1 = true;

        sms_img = v.findViewById(R.id.sms_send_receiver);
        contact_img = v.findViewById(R.id.contact_access);
        location_img = v.findViewById(R.id.location_access);


        // SMS code started
        if (pref_RWS_SMS.getString("SMS", "").matches(String.valueOf(sms1))) {
            sms_img.setImageResource(R.drawable.ic_check);
        } else {
            sms_img.setImageResource(R.drawable.ic_cross);
        }



        // Contact code started
        if (pref_RW_CON.getString("CONTACTS", "").matches(String.valueOf(contact1))) {
            contact_img.setImageResource(R.drawable.ic_check);
        } else {
            contact_img.setImageResource(R.drawable.ic_cross);
        }



        // Location code started
        if (pref_A_LOC.getString("LOCATION", "").matches(String.valueOf(location1))) {
            location_img.setImageResource(R.drawable.ic_check);
        } else {
            location_img.setImageResource(R.drawable.ic_cross);
        }

        return v;
    }
}
