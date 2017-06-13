package com.programming.way.tourism;

import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;

import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.nightonke.boommenu.BoomMenuButton;

import java.util.Random;

public class MapsActivity extends HandlingMaps {

private Random random = new Random();

    private FirebaseAuth mAuth;

    Button TheButtonInTheFirstButtonSheet;
    FloatingActionButton logoutFab;
    private ImageView cameraImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        onCreateHandle();

        cameraImg = (ImageView) findViewById(R.id.camImg);
        new buttomSheetsManeger(MapsActivity.this,cameraImg);


        mAuth = FirebaseAuth.getInstance();


        try {
            Bundle bundle = getIntent().getExtras();
            if(bundle != null) {
                String markerTitle = bundle.getString("price");
                Double lat = bundle.getDouble("lat");
                Double lng = bundle.getDouble("lng");
                mMap.addMarker(new MarkerOptions().title(markerTitle).position(new LatLng(lat , lng)).icon(BitmapDescriptorFactory
                        .defaultMarker()));
                Log.i("price in maps", markerTitle);
            }
        }catch (Exception e){

        }

        logoutFab = (FloatingActionButton)findViewById(R.id.fabLogout);
        logoutFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null){
            mAuth.signOut();
            new MorsyToast(MapsActivity.this,"","You have signed out successfully ..",random.nextInt(6));
//            Toast.makeText(MapsActivity.this, "You have signed out successfully ..", Toast.LENGTH_SHORT).show();

        }
        else if (currentUser == null){
            new MorsyToast(MapsActivity.this,"","You haven't logged in ..",random.nextInt(6));

//            Toast.makeText(MapsActivity.this, "You haven't logged in ..", Toast.LENGTH_SHORT).show();
        }

            }
        });




        TheButtonInTheFirstButtonSheet = (Button)findViewById(R.id.HomeButton) ;
        // Buttom Sheet
        View bottomSheet1 = findViewById(R.id.bottom_sheet1);
        View bottomSheet = findViewById(R.id.bottom_sheet);
        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);

            new buttomSheetsManeger(MapsActivity.this,bottomSheet1,bottomSheet,TheButtonInTheFirstButtonSheet ,bmb );
        //FabBtn = findViewById(R.id.fab);






        //


    }





}
