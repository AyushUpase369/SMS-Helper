package com.example.smshelper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link reset_password_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class reset_password_fragment extends Fragment {
    EditText old_pass, pass, confirm_pass;
    TextView continue_btn;
    DatabaseReference rootDatabaseref;
    ProgressDialog progressDialog;
    String passwordstore = "";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public reset_password_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment reset_password_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static reset_password_fragment newInstance(String param1, String param2) {
        reset_password_fragment fragment = new reset_password_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reset_password_fragment, container, false);
        rootDatabaseref = FirebaseDatabase.getInstance().getReference().child("data-of-user");

        old_pass = v.findViewById(R.id.edt_old_password_create_inside);
        pass = v.findViewById(R.id.edt_password_create_inside);
        confirm_pass = v.findViewById(R.id.edt_confirm_password_create_inside);
        continue_btn = v.findViewById(R.id.btn_continue_inside);


        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoMain.logomain.net();
                if (logoMain.logomain.network_conn.equals("yes")) {

                    String pass_str = pass.getText().toString();
                    String confirm_pass_str = confirm_pass.getText().toString();
                    final String old_pass_str = old_pass.getText().toString();
                    final String phone_number_str = logoMain.logomain.getmobb;

                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference("data-of-user");

                    Query check_number = databaseReference.orderByChild("mobilenum").equalTo(phone_number_str);

                    check_number.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.exists()) {
                                String passwordcheck = snapshot.child(phone_number_str).child("password").getValue(String.class);

                                if (old_pass_str.isEmpty()) {
                                    old_pass.setError("Password is required!");
                                    old_pass.requestFocus();
                                } else {
                                    if (passwordcheck.equals(old_pass_str)) {

                                        if (pass_str.isEmpty()) {
                                            pass.setError("Password is required!");
                                            pass.requestFocus();
                                            return;
                                        }
                                        if (pass_str.length() < 6) {
                                            pass.setError("Min password length should be 6 characters!");
                                            pass.requestFocus();
                                            return;
                                        }
                                        if (confirm_pass_str.isEmpty()) {
                                            confirm_pass.setError("Password is required!");
                                            confirm_pass.requestFocus();
                                            return;
                                        }
                                        if (!pass_str.equals(confirm_pass_str)) {
                                            confirm_pass.setError("Password is not matching!");
                                            confirm_pass.requestFocus();

                                        } else {
                                            String mobile_mob = phone_number_str;

                                            HashMap hashMap = new HashMap();
                                            hashMap.put("password", confirm_pass_str);
                                            rootDatabaseref.child(mobile_mob).updateChildren(hashMap);

                                            progressdialog();
                                            startActivity(new Intent(getActivity(), Main_Login_Signup_Activity.class));
                                            getActivity().finish();
                                        }
                                    } else {
                                        old_pass.setError("Password is not matching");
                                        old_pass.requestFocus();
                                    }
                                }

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                } else {
                    Intent SingupI = new Intent(getActivity(), network_error.class);
                    SingupI.putExtra("Net", "security_update");
                    startActivity(SingupI);
                    getActivity().finish();
                }
            }
        });
        return v;
    }

    private void progressdialog() {

        progressDialog = new ProgressDialog(getActivity());

        progressDialog.show();

        progressDialog.setContentView(R.layout.progress_dialog);

        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        progressDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
    }

    private void loadFragment(Fragment fragment) {

        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.container, fragment);
        ft.commit();
    }
}