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
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.tapadoo.alerter.Alerter;

import java.io.File;
import java.io.IOException;

public class MapsActivity extends HandlingMaps {


    private FirebaseAuth mAuth;
    int count =0;
    final static int TAKE_PHOTO_CODE = 100;
    BottomSheetBehavior bottomSheetBehavior;
    BottomSheetBehavior bottomSheetBehavior1;
    Button TheButtonInTheFirstButtonSheet;
    View FabBtn;
    FloatingActionButton logoutFab;
    private ImageView cameraImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        onCreateHandle();
//        Countries countriesN = new Countries();

        cameraImg = (ImageView) findViewById(R.id.camImg);
        cameraImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                startActivity(new Intent( MediaStore.ACTION_IMAGE_CAPTURE));

            }
        });

// ...
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
            Toast.makeText(MapsActivity.this, "You have signed out successfully ..", Toast.LENGTH_SHORT).show();

        }
        else if (currentUser == null){
            Toast.makeText(MapsActivity.this, "You haven't logged in ..", Toast.LENGTH_SHORT).show();
        }

            }
        });




        TheButtonInTheFirstButtonSheet = (Button)findViewById(R.id.HomeButton) ;
        // Buttom Sheet
        View bottomSheet1 = findViewById(R.id.bottom_sheet1);
        View bottomSheet = findViewById(R.id.bottom_sheet);

        //FabBtn = findViewById(R.id.fab);


        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior1 = BottomSheetBehavior.from(bottomSheet1);

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior1.setState(BottomSheetBehavior.STATE_HIDDEN);
       // Boom Menu
        //Mibmab
        int ImagesForTheMenu[] = new int[]{R.mipmap.gift, R.mipmap.stage,R.mipmap.user,R.mipmap.user};
        int TextForMenu[] = new int[]{R.string.SearchForAnApartment_Menu, R.string.CreateEvent_Menu,R.string.SetTheApartmentLocation,R.string.SetTheApartmentLocation};
        int HintTextForMenu[] = new int[]{R.string.SearchForAnApartmentHint_Menu, R.string.CreateEventHint_Menu,R.string.SetTheApartmentLocation_hint,R.string.SetTheApartmentLocation_hint};

        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);

        bmb.setButtonEnum(ButtonEnum.Ham);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_4);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_4);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            final HamButton.Builder builder = new HamButton.Builder().listener(new OnBMClickListener() {


                @Override
                public void onBoomButtonClick(int index) {
                    if(index == 0){
/////////////////////////////////////////////////////
                        FindLocatinDialog dialog=new FindLocatinDialog(MapsActivity.this);
                        //dialog.FindLocatinDialog(MapsActivity.this);
                    }
                    else if (index == 3)
                    {
                        bottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);


                        TheButtonInTheFirstButtonSheet.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                bottomSheetBehavior1.setState(BottomSheetBehavior.STATE_HIDDEN);

                                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                            }
                        });

                    }
                    else if(index == 1){
                        Alerter.create(MapsActivity.this)
                                .setTitle("Alert Title")
                                .setText("Alert text...")
                                .setIcon(R.drawable.alerter_ic_notifications)
                                .setBackgroundColor(R.color.md_green_900)
                                .show();
                    }else if(index == 2){
                        //new user login
                        CFirebaseAuth cFirebaseAuth=new CFirebaseAuth();
                        cFirebaseAuth.CFirebaseAuth(MapsActivity.this);
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



        //


    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//         FirebaseAuth mAuth;
//// ...
//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//
//        if (currentUser != null){
//
//        }
//
//    }






}
