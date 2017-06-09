package com.programming.way.tourism;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by heshamsalama on 6/10/2017.
 */

public class FindLocatinDialog extends AppCompatActivity {
    AlertDialog dialog;
    private AlertDialog.Builder alertBuilder;
    private View view ;
    public void FindLocatinDialog(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.location_find, null, true);
        alertBuilder= new AlertDialog.Builder(context);
        alertBuilder.setView(view);
        dialog = alertBuilder.create();
        dialog.show();
    }
}
