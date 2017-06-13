package com.programming.way.tourism;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.Random;

/**
 * Created by heshamsalama on 6/13/2017.
 */

public class buttomSheetsManeger  extends HandlingMaps {
    private Random random = new Random();

    private FirebaseAuth mAuth;
    int count =0;
    final static int TAKE_PHOTO_CODE = 100;
    BottomSheetBehavior bottomSheetBehavior;
    BottomSheetBehavior bottomSheetBehavior1;
    Button TheButtonInTheFirstButtonSheet;
    View FabBtn;
    FloatingActionButton logoutFab;
    public ImageView cameraImg;

    public buttomSheetsManeger(final Context context, View bottomSheet1, View bottomSheet, final Button ButtonSheet,BoomMenuButton bmb ) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior1 = BottomSheetBehavior.from(bottomSheet1);

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior1.setState(BottomSheetBehavior.STATE_HIDDEN);
        // Boom Menu
        //Mibmab
        int ImagesForTheMenu[] = new int[]{R.mipmap.gift, R.mipmap.stage,R.mipmap.user,R.mipmap.user};
        int TextForMenu[] = new int[]{R.string.SearchForAnApartment_Menu, R.string.CreateEvent_Menu,R.string.SetTheApartmentLocation,R.string.SetTheApartmentLocation};
        int HintTextForMenu[] = new int[]{R.string.SearchForAnApartmentHint_Menu, R.string.CreateEventHint_Menu,R.string.SetTheApartmentLocation_hint,R.string.SetTheApartmentLocation_hint};

//        bmb = (BoomMenuButton) findViewById(R.id.bmb);

        bmb.setButtonEnum(ButtonEnum.Ham);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_4);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_4);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            final HamButton.Builder builder = new HamButton.Builder().listener(new OnBMClickListener() {


                @Override
                public void onBoomButtonClick(int index) {
                    if(index == 0){
/////////////////////////////////////////////////////
                        FindLocatinDialog dialog=new FindLocatinDialog(context);
                        //dialog.FindLocatinDialog(MapsActivity.this);
                    }
                    else if (index == 3)
                    {
                        bottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);


                        ButtonSheet.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                bottomSheetBehavior1.setState(BottomSheetBehavior.STATE_HIDDEN);

                                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                            }
                        });

                    }
                    else if(index == 1){

                        new MorsyToast(context,"events","set or search for events",random.nextInt(6));


                    }else if(index == 2){
                        new MorsyToast(context,"m4 3arf lesa","faydet om el buttom da a allah a3lam ",random.nextInt(6));

                        //new user login
                        CFirebaseAuth cFirebaseAuth=new CFirebaseAuth();
                        cFirebaseAuth.CFirebaseAuth(context);
                    }
//                    else if (index == 2){
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                            checkLocationPermission();
//                        }
//
//
//                        final AlertDialog.Builder alert = new AlertDialog.Builder(MapsActivity.this)
//                                .setMessage("Is your appartment here ? (When you click yes you'll continue adding details)");
//
//                                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        LatLng latLng = new LatLng(mLastLocation.getLatitude() , mLastLocation.getLongitude());
//                                        mMap.addMarker(new MarkerOptions().position(latLng).title("apartment").icon(BitmapDescriptorFactory.defaultMarker()));
//
//                                    }
//                                })
//                                .setNegativeButton("no", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        alert.setCancelable(true);
//                                    }
//                                })
//                                .create().show();
//
//
//
//
//
//
//                    }
                }
            })

                    .normalImageRes(ImagesForTheMenu[i])
                    .normalTextRes(TextForMenu[i])
                    .subNormalTextRes(HintTextForMenu[i]);


            bmb.addBuilder(builder);
        }
    }

    public buttomSheetsManeger(final Context context , ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                startActivity(new Intent( MediaStore.ACTION_IMAGE_CAPTURE));

            }
        });


    }

}
