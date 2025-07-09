package com.example.smshelper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link security_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class security_fragment extends Fragment {
    static security_fragment sef;
    View reset_pass, reset_m_pin;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public security_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment security_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static security_fragment newInstance(String param1, String param2) {
        security_fragment fragment = new security_fragment();
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
        sef = this;
        View v = inflater.inflate(R.layout.fragment_security_fragment, container, false);

        reset_pass = v.findViewById(R.id.reset_password_con);
        reset_m_pin = v.findViewById(R.id.reset_m_pin);

        reset_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoMain.logomain.net();
                if (logoMain.logomain.network_conn.equals("yes")) {
                    loadFragment(new reset_password_fragment());
                } else {
                    Intent SingupI = new Intent(getActivity(), network_error.class);
                    SingupI.putExtra("Net", "security_reset_pass");
                    startActivity(SingupI);
                    getActivity().finish();
                }
            }
        });


        reset_m_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences check_mpin = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor_mpin = check_mpin.edit();
                editor_mpin.putString(logoMain.C_M_PIN, "");
                editor_mpin.apply();

                SharedPreferences prefs_getin = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor_getin = prefs_getin.edit();
                editor_getin.putString(logoMain.GET_IN, "");
                editor_getin.apply();

                SharedPreferences check_m_pass = getActivity().getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor_check_m_pass = check_m_pass.edit();
                editor_check_m_pass.putString(logoMain.C_M_PASS, "");
                editor_check_m_pass.apply();

                SharedPreferences prefs_first = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor_first = prefs_first.edit();
                editor_first.putBoolean("firstTime", false);
                editor_first.apply();

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("save", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("value", false);
                editor.apply();

                Intent intent = new Intent(getActivity(), loginmpin.class);
                intent.putExtra("security","yes");
                startActivity(intent);
                getActivity().finish();

            }
        });

        return v;
    }

    private void loadFragment(Fragment fragment) {

        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.container, fragment);
        ft.commit();
    }
}