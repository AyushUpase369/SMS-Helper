package com.example.smshelper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class create_new_password extends AppCompatActivity {

    EditText pass, confirm_pass;
    Button continue_btn;
    DatabaseReference rootDatabaseref;
    ProgressDialog progressDialog;
    String passwordstore = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();// Hides action bar

        setContentView(R.layout.activity_create_new_password);
        rootDatabaseref= FirebaseDatabase.getInstance().getReference().child("data-of-user");
        ImageView backIm = (ImageView) findViewById(R.id.btn_back_otp);

        backIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),enter_mobile_number.class);
                startActivity(intent);
                finish();
            }
        });

        pass = findViewById(R.id.edt_password_create);
        confirm_pass = findViewById(R.id.edt_confirm_password_create);
        continue_btn = findViewById(R.id.btn_continue);

        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoMain.logomain.net();
                if(logoMain.logomain.network_conn.equals("yes")) {
                    String pass_str = pass.getText().toString();
                    String confirm_pass_str = confirm_pass.getText().toString();

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
                        String mobnotxtinmob = enter_mob_num.emnn.enternumber.getText().toString();
                        String password_text = confirm_pass.getText().toString();
                        HashMap hashMap = new HashMap();
                        hashMap.put("password", password_text);
                        rootDatabaseref.child(mobnotxtinmob).updateChildren(hashMap);
                        progressdialog();
                        startActivity(new Intent(getApplicationContext(), Main_Login_Signup_Activity.class));
                        finish();
                    }
                }
                else{
                    Intent LoginI = new Intent(getApplicationContext(),network_error.class);
                    LoginI.putExtra("Net","CreateNewPass");
                    startActivity(LoginI);
                    finish();
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
}