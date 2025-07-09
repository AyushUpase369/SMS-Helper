package com.example.smshelper;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Profile_screens extends Fragment {

    EditText username, email;
    Button save_changes;
    DatabaseReference rootDatabaseref;

    public Profile_screens() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile_screens, container, false);
        rootDatabaseref = FirebaseDatabase.getInstance().getReference().child("data-of-user");
        username = v.findViewById(R.id.username_l);
        email = v.findViewById(R.id.email_l);
        save_changes = v.findViewById(R.id.save_change);

        username.setText(profile_textviews.ptv.usernamefire);
        email.setText(profile_textviews.ptv.emailidfire);


//        String mobnotxtinmob= Login.main.number.getText().toString();
//        String m_pintxt = passCode.toString();


        save_changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoMain.logomain.net();
                if (logoMain.logomain.network_conn.equals("yes")) {
                    String usernametxt = username.getText().toString();
                    String emailtxt = username.getText().toString();

                    if (usernametxt.isEmpty()) {
                        username.setError("User name is required!");
                        username.requestFocus();
                        return;
                    }
                    if (usernametxt.length() < 4) {
                        username.setError("Min username length should be 4 characters!");
                        username.requestFocus();
                        return;
                    }
                    if (emailtxt.isEmpty()) {
                        email.setError("Email id is required!");
                        email.requestFocus();
                        return;
                    }
                    if (Patterns.EMAIL_ADDRESS.matcher(emailtxt).matches()) {
                        email.setError("Enter valid Email");
                        email.requestFocus();
                        return;
                    } else {
                        String usernaam = username.getText().toString();
                        String email_ied = email.getText().toString();
                        String mobile_mob = logoMain.logomain.getmobb;

                        HashMap hashMap = new HashMap();
                        hashMap.put("username", usernaam);
                        hashMap.put("email", email_ied);
                        rootDatabaseref.child(mobile_mob).updateChildren(hashMap);

                        showDialog();

                    }
                } else {
                    Intent SingupI = new Intent(getActivity(), network_error.class);
                    SingupI.putExtra("Net", "profile_save");
                    startActivity(SingupI);
                    getActivity().finish();
                }
            }
        });

        return v;
    }

    private void showDialog() {

        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.layout_update_dialog);
        dialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                loadFragment(new profile_textviews());

            }
        }, 2000);
    }

    private void loadFragment(Fragment fragment) {

        FragmentManager fm = requireActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.container, fragment);
        ft.commit();
    }
}