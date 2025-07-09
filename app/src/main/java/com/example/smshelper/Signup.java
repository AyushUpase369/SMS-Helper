package com.example.smshelper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.PatternMatcher;
import android.telephony.TelephonyManager;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Signup#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Signup extends Fragment {
    static Signup signupMain;
    String usernametxt;
    String emailtxt;
    String passwordtxt;
    String confirmpasswordtxt;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    public EditText email, username, password, confirm_password;
    public Button next;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Signup() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Signup.
     */
    // TODO: Rename and change types and number of parameters
    public static Signup newInstance(String param1, String param2) {
        Signup fragment = new Signup();
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
        signupMain = this;
        View v = inflater.inflate(R.layout.fragment_signup, container, false);
        auth = FirebaseAuth.getInstance();
        username = v.findViewById(R.id.edt_username_signup);
        email = v.findViewById(R.id.edt_email_signup);
        password = v.findViewById(R.id.edt_password_signup);
        confirm_password = v.findViewById(R.id.edt_confirm_password_signup);
        next = v.findViewById(R.id.next_signup_button);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernametxt = username.getText().toString();
                emailtxt = email.getText().toString();
                passwordtxt = password.getText().toString();
                confirmpasswordtxt = confirm_password.getText().toString();
//                String mobilenumber = mobile_num.getText().toString();
                logoMain.logomain.net();

                if (logoMain.logomain.network_conn.equals("yes")) {


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
                    if (passwordtxt.isEmpty()) {
                        password.setError("Password is required!");
                        password.requestFocus();
                        return;
                    }
                    if (passwordtxt.length() < 6) {
                        password.setError("Min password length should be 6 characters!");
                        password.requestFocus();
                        return;
                    }
                    if (confirmpasswordtxt.isEmpty()) {
                        confirm_password.setError("Password is required!");
                        confirm_password.requestFocus();
                        return;
                    }
                    if (!passwordtxt.equals(confirmpasswordtxt)) {
                        confirm_password.setError("Password is not matching!");
                        confirm_password.requestFocus();
                    } else {

                        emailV();

                    }
                }else{
                    Intent SingupI = new Intent(getContext(),network_error.class);
                    SingupI.putExtra("Net","Singup");
                    startActivity(SingupI);
                    getActivity().finish();
                }
            }
        });

        return v;
    }


    public void emailV() {
        if (Patterns.EMAIL_ADDRESS.matcher(emailtxt).matches()) {
            progressdialog();
            Intent intent = new Intent(getActivity(), enter_mobile_number.class);
            startActivity(intent);
            getActivity().finish();
        } else {
            email.setError("Enter valid Email");
            email.requestFocus();
        }
    }

    private void progressdialog() {

        progressDialog = new ProgressDialog(getActivity());

        progressDialog.show();

        progressDialog.setContentView(R.layout.progress_dialog);

        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        progressDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
    }
}

