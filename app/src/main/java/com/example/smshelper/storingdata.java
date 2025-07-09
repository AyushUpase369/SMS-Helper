package com.example.smshelper;

public class storingdata {

    String username,email,password,mobilenum,m_pin;

    public storingdata(String username_s, String email_s) {
    }

    public storingdata(String username, String email, String password, String mobilenum, String m_pin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.mobilenum = mobilenum;
        this.m_pin = m_pin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobilenum() {
        return mobilenum;
    }

    public void setMobilenum(String mobilenum) {
        this.mobilenum = mobilenum;
    }

    public String getM_pin() {return m_pin; }

    public void setM_pin(String m_pin) { this.m_pin = m_pin; }
}
