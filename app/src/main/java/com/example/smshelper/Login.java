package com.example.smshelper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {
    EditText number, password;
    private Button login, forgetpassword;
    private TextInputLayout username_layout, password_layout;
    TextView textView;
    String fromSS="false";
    ProgressDialog progressDialog;
    static Login main;

    String check_if_enter_mpin;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Login.
     */
    // TODO: Rename and change types and number of parameters
    public static Login newInstance(String param1, String param2) {
        Login fragment = new Login();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Login() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = this;
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login, container, false);

        username_layout = v.findViewById(R.id.text_input_layout_username_login);
        password_layout = v.findViewById(R.id.text_input_layout_password_login);
        number = v.findViewById(R.id.edt_number_login);
        password = v.findViewById(R.id.edt_password_login);
        forgetpassword = v.findViewById(R.id.tv_forget_password_login);
        textView =v.findViewById(R.id.fromSingupText);
        login = v.findViewById(R.id.next_signup_button);
        textView.setText("");
        SharedPreferences fromS = getContext().getSharedPreferences("fromS", Context.MODE_PRIVATE);
        fromSS=fromS.getString("fromS","");
        if(Objects.equals(fromSS, "true")){
            textView.setText("Login Again The Register Account");
            SharedPreferences preferences = getContext().getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("passcode", "");
            editor.apply();
        }
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inc = new Intent(getActivity(), enter_mob_num.class);
                startActivity(inc);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoMain.logomain.net();
                if(logoMain.logomain.network_conn.equals("yes")) {
                    String numbertxt = number.getText().toString();
                    String passwordtxt = password.getText().toString();

                    if (numbertxt.isEmpty()) {
                        number.setError("Please enter Number");
                        number.requestFocus();
                        return;
                    }
                    if (numbertxt.length() < 10) {
                        number.setError("Please enter valid Number");
                        number.requestFocus();
                        return;
                    }
                    if (passwordtxt.isEmpty()) {
                        password.setError("Please enter password");
                        password.requestFocus();
                    } else {

                        final String number_data = number.getText().toString();
                        final String password_data = password.getText().toString();

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference("data-of-user");

                        Query check_number = databaseReference.orderByChild("mobilenum").equalTo(number_data);

                        check_number.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if (snapshot.exists()) {
                                    String passwordcheck = snapshot.child(number_data).child("password").getValue(String.class);

                                    if (passwordcheck.equals(password_data)) {
                                        SharedPreferences prefs_first = PreferenceManager.getDefaultSharedPreferences(getContext());
                                        if (prefs_first.getBoolean("firstTime", false)) {
                                            progressdialog();
                                            Intent intent = new Intent(getActivity(), Dashboard_screen.class);
                                            startActivity(intent);
                                            getActivity().finish();
                                        } else {
                                            SharedPreferences mobile_login = getContext().getSharedPreferences("mobile_login", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor mob_editor = mobile_login.edit();
                                            mob_editor.putString("mob", number.getText().toString());
                                            mob_editor.apply();

                                            SharedPreferences fromS = getContext().getSharedPreferences("fromS", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor e_fromS = fromS.edit();
                                            e_fromS.putString("fromS", "false");
                                            e_fromS.apply();

                                            progressdialog();
                                            Intent intent = new Intent(getActivity(), loginmpin.class);
                                            intent.putExtra("Security","true");
                                            startActivity(intent);
                                            getActivity().finish();
                                        }
                                    } else {
                                        password.setError("Wrong password");
                                        password.requestFocus();
                                    }
                                } else {
                                    number.setError("User does not exists");
                                    number.requestFocus();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                }else{
                    Intent LoginI = new Intent(getContext(),network_error.class);
                    LoginI.putExtra("Net","Login");
                    startActivity(LoginI);
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

    private boolean isConnected(Login Login) {

        ConnectivityManager connectivityManager = (ConnectivityManager) Login.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected())) {
            return true;
        } else {
            return false;
        }
    }
}