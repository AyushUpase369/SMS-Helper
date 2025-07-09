package com.example.smshelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class forget_pass_verify_otp extends AppCompatActivity {
    PinView pinView;
    ProgressDialog progressDialog;
    String get_otp_backend;
    View number_liner;
    TextView otp_var, mob_num_previous;
    float v = 0;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();// Hides action bar

        setContentView(R.layout.activity_forget_pass_verify_otp);

        final Button verify_otp = findViewById(R.id.btn_verify_otp);
        otp_var = findViewById(R.id.otp_varification);
        mob_num_previous = findViewById(R.id.text_mobile_show_number);
        number_liner = findViewById(R.id.linear_layout_numbers);
        pinView = findViewById(R.id.otp_entered_box);
        ImageView backIm = (ImageView) findViewById(R.id.btn_back_otp);


        TextView textView = findViewById(R.id.text_mobile_show_number);
        textView.setText(String.format(
                "+91-%s", getIntent().getStringExtra("mobilefor")
        ));

        get_otp_backend = getIntent().getStringExtra("backendotpfor");

        backIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),enter_mobile_number.class);
                startActivity(intent);
                finish();
            }
        });

        verify_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!pinView.getText().toString().trim().isEmpty()){

                    String entercodeotp = pinView.getText().toString();

                    if (get_otp_backend != null) {

                        progressdialog();

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                get_otp_backend, entercodeotp
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        progressDialog.dismiss();

                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(getApplicationContext(), create_new_password.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(forget_pass_verify_otp.this, "Enter the correct otp", Toast.LENGTH_LONG).show();

                                        }
                                    }
                                });

                    } else {
                        Toast.makeText(forget_pass_verify_otp.this, "Please check your internet connection", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(forget_pass_verify_otp.this, "Please enter all numbers", Toast.LENGTH_LONG).show();
                }

            }
        });

//        number_otp_move();

        findViewById(R.id.text_resend_otp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        forget_pass_verify_otp.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                Intent intent = new Intent(getApplicationContext(), m_pin.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(forget_pass_verify_otp.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                get_otp_backend = newbackendotp;
                                Toast.makeText(forget_pass_verify_otp.this, "Otp sended succussfully", Toast.LENGTH_LONG).show();


                            }
                        }
                );
            }
        });


        otp_var.setTranslationX(-300);
        pinView.setTranslationX(600);
        verify_otp.setTranslationY(700);

        otp_var.setAlpha(v);
        pinView.setAlpha(v);
        verify_otp.setAlpha(v);

        otp_var.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pinView.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(400).start();
        verify_otp.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(1200).start();


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
//        super.onBackPressed();
    }

}

