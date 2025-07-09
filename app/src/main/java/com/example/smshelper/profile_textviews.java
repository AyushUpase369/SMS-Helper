package com.example.smshelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class profile_textviews extends Fragment {
    static profile_textviews ptv;
    String usernamefire, emailidfire;
    TextView usernametxt, emailtxt, phonetxt, frame_username, frame_email_id;
    Button edit_text;
    String m;

    public profile_textviews() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile_textviews, container, false);
        // number chi value set kara logoMain.logomain.getmob chi value use karun :)
        ptv = this;

        usernametxt = v.findViewById(R.id.username_txtv);
        emailtxt = v.findViewById(R.id.email_txtv);
        phonetxt = v.findViewById(R.id.phone_number_txtv);
        frame_username = v.findViewById(R.id.username_frame);
        frame_email_id = v.findViewById(R.id.email_id_frame);
        edit_text = v.findViewById(R.id.btn_continue);

        edit_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoMain.logomain.net();
                if (logoMain.logomain.network_conn.equals("yes")) {
                    loadFragment(new Profile_screens());
                } else {
                    Intent SingupI = new Intent(getActivity(), network_error.class);
                    SingupI.putExtra("Net", "profile_edit");
                    startActivity(SingupI);
                    getActivity().finish();
                }

            }
        });

        phonetxt.setText(logoMain.logomain.getmobb);
        final String mobile_number_p = phonetxt.getText().toString();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("data-of-user");
        Query check_number = databaseReference.orderByChild("mobilenum").equalTo(mobile_number_p);
        check_number.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    usernamefire = snapshot.child(mobile_number_p).child("username").getValue(String.class);
                    emailidfire = snapshot.child(mobile_number_p).child("email").getValue(String.class);

                    usernametxt.setText(usernamefire);
                    emailtxt.setText(emailidfire);
                    frame_username.setText(usernamefire);
                    frame_email_id.setText(emailidfire);

                    // unlock m pin works fine now problem in this file while opening this file.  :)
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return v;
    }

    private void loadFragment(Fragment fragment) {

        FragmentManager fm = requireActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.container, fragment);
        ft.commit();
    }
}