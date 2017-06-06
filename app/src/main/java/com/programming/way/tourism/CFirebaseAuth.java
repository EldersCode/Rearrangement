package com.programming.way.tourism;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by heshamsalama on 6/5/2017.
 */

public class CFirebaseAuth {
    private View view;
    private FirebaseAuth mAuth;

    private AlertDialog.Builder alertBuilder;

    public void CFirebaseAuth(Context context){

        view = LayoutInflater.from(context).inflate(R.layout.activity_login, null, true);

        alertBuilder = new AlertDialog.Builder(context);

        alertBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (view != null) {

                    ViewGroup parentViewGroup = (ViewGroup) view.getParent();

                    if (parentViewGroup != null) {
                        parentViewGroup.removeAllViews();
                    }
                }
                dialog.dismiss();
            }
        });
        alertBuilder.setView(view);
        AlertDialog dialog = alertBuilder.create();

        dialog.show();

    }

}
