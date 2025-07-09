package com.example.smshelper;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class home_screen extends Fragment {

    View all_syntax,contact, location, ring, mute_unmute;

    public home_screen() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_screen, container, false);

        all_syntax = v.findViewById(R.id.con_layout_All_Syntax);
        contact = v.findViewById(R.id.con_layout_access_contact);
        location = v.findViewById(R.id.con_layout_access_location);
        ring = v.findViewById(R.id.con_layout_ring_mobile);
        mute_unmute = v.findViewById(R.id.con_layout_mute_unmute_mobile);

        all_syntax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogallsyntax();
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogContact();
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogLocation();
            }
        });

        ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogRing();
            }
        });

        mute_unmute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogMuteorUnmute();
            }
        });

        return v;
    }

    private void showDialogallsyntax() {

        Dialog dialog = new Dialog(getContext(), R.style.DialogStyle);
        dialog.setContentView(R.layout.fragment_all_syntax);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        ImageView btnClose = dialog.findViewById(R.id.contact_access_up);
        TextView syntax_contact = dialog.findViewById(R.id.click_here_contact);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        syntax_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogall_syntax();
            }
        });

        dialog.show();
    }

    private void showDialogall_syntax() {

        Dialog dialog = new Dialog(getContext(), R.style.DialogStyle);
        dialog.setContentView(R.layout.fragment_all_syntax_syntax);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        ImageView btnClose = dialog.findViewById(R.id.contact_access_up);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showDialogContact() {

        Dialog dialog = new Dialog(getContext(), R.style.DialogStyle);
        dialog.setContentView(R.layout.fragment_setps_for_accessing_contact);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        ImageView btnClose = dialog.findViewById(R.id.contact_access_up);
        TextView syntax_contact = dialog.findViewById(R.id.click_here_contact);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        syntax_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogContact_syntax();
            }
        });

        dialog.show();
    }

    private void showDialogContact_syntax() {

        Dialog dialog = new Dialog(getContext(), R.style.DialogStyle);
        dialog.setContentView(R.layout.fragment_contact_syntax);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        ImageView btnClose = dialog.findViewById(R.id.contact_access_up);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showDialogLocation() {

        Dialog dialog = new Dialog(getContext(), R.style.DialogStyle);
        dialog.setContentView(R.layout.fragment_setps_for_accessing_location);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        ImageView btnClose = dialog.findViewById(R.id.contact_access_up);
        TextView syntax_location = dialog.findViewById(R.id.click_here_location);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        syntax_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogLocation_syntax();
            }
        });

        dialog.show();
    }

    private void showDialogLocation_syntax() {

        Dialog dialog = new Dialog(getContext(), R.style.DialogStyle);
        dialog.setContentView(R.layout.fragment_location_syntax);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        ImageView btnClose = dialog.findViewById(R.id.contact_access_up);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showDialogRing() {

        Dialog dialog = new Dialog(getContext(), R.style.DialogStyle);
        dialog.setContentView(R.layout.fragment_setps_for_ringing_mobile);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        ImageView btnClose = dialog.findViewById(R.id.contact_access_up);
        TextView syntax_ring = dialog.findViewById(R.id.text_steps_ring_mobile);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        syntax_ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogRing_syntax();
            }
        });

        dialog.show();
    }

    private void showDialogRing_syntax() {

        Dialog dialog = new Dialog(getContext(), R.style.DialogStyle);
        dialog.setContentView(R.layout.fragment_ring_syntax);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        ImageView btnClose = dialog.findViewById(R.id.contact_access_up);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showDialogMuteorUnmute() {

        Dialog dialog = new Dialog(getContext(), R.style.DialogStyle);
        dialog.setContentView(R.layout.fragment_setps_for_mute_unmute_mobile);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        ImageView btnClose = dialog.findViewById(R.id.contact_access_up);
        TextView syntax_mute_or_unmute = dialog.findViewById(R.id.text_steps_mute_or_unmute);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        syntax_mute_or_unmute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogMuteorUnmute_syntax();
            }
        });

        dialog.show();
    }

    private void showDialogMuteorUnmute_syntax() {

        Dialog dialog = new Dialog(getContext(), R.style.DialogStyle);
        dialog.setContentView(R.layout.fragment_mute_or_unmute_syntax);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        ImageView btnClose = dialog.findViewById(R.id.contact_access_up);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}