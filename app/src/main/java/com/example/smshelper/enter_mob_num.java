package com.example.smshelper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class enter_mob_num extends AppCompatActivity {
    static enter_mob_num emnn;
    String no;
    ProgressDialog progressDialog;
    EditText enternumber;
    Button get_otp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_enter_mob_num);
        emnn = this;
        enternumber = findViewById(R.id.input_phone_num);
        get_otp = findViewById(R.id.btn_get_mob_otp);
        ImageView BackI = (ImageView) findViewById(R.id.btn_back_num);

        BackI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIn = new Intent(getApplicationContext(), Main_Login_Signup_Activity.class);
                startActivity(backIn);
                finish();
            }
        });
        get_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressdialog();
                if (enternumber == null) {
                    enternumber.setError("Please enter Number");
                    enternumber.requestFocus();
                    return;
                } else {

                    final String number_data = enternumber.getText().toString();

                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference("data-of-user");

                    Query check_number = databaseReference.orderByChild("mobilenum").equalTo(number_data);

                    check_number.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.exists()) {
                                no = enternumber.getText().toString().trim();

                                    if ((enternumber.getText().toString().trim()).length() == 10) {


                                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                                "+91" + enternumber.getText().toString(),
                                                60,
                                                TimeUnit.SECONDS,
                                                enter_mob_num.this,
                                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                                    @Override
                                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                                        progressDialog.dismiss();
                                                    }

                                                    @Override
                                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                                        progressDialog.dismiss();
                                                        Toast.makeText(enter_mob_num.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                                    }

                                                    @Override
                                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                                        progressDialog.dismiss();
                                                        Intent intent = new Intent(getApplicationContext(), forget_pass_verify_otp.class);
                                                        intent.putExtra("mobilefor", enternumber.getText().toString());
                                                        intent.putExtra("backendotpfor", backendotp);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                }
                                        );

                                    } else {
                                        progressDialog.dismiss();
                                        enternumber.setError("Please enter correct number");
                                        enternumber.requestFocus();
                                    }

                            } else {
                                progressDialog.dismiss();
                                enternumber.setError("User does not exists");
                                enternumber.requestFocus();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }
        });

    }

    private void progressdialog() {

        progressDialog = new ProgressDialog(this);

        progressDialog.show();

        progressDialog.setContentView(R.layout.progress_dialog);

        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        progressDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
    }

    @Override
    public void onBackPressed() {
    }

}

