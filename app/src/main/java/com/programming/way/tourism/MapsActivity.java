package com.programming.way.tourism;


import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
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

import cn.pedant.SweetAlert.SweetAlertDialog;


public class MapsActivity extends HandlingMaps {


    private FirebaseAuth mAuth;
    LatLng latLng;
    int count = 0;
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
            if (bundle != null) {
                String markerTitle = bundle.getString("price");
                Double lat = bundle.getDouble("lat");
                Double lng = bundle.getDouble("lng");
                mMap.addMarker(new MarkerOptions().title(markerTitle).position(new LatLng(lat, lng)).icon(BitmapDescriptorFactory
                        .defaultMarker()));
                Log.i("price in maps", markerTitle);
            }
        } catch (Exception e) {

        }

        logoutFab = (FloatingActionButton) findViewById(R.id.fabLogout);
        logoutFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentUser = mAuth.getCurrentUser();

                if (currentUser != null) {
                    mAuth.signOut();
                    Toast.makeText(MapsActivity.this, "You have signed out successfully ..", Toast.LENGTH_SHORT).show();

                } else if (currentUser == null) {
                    Toast.makeText(MapsActivity.this, "You haven't logged in ..", Toast.LENGTH_SHORT).show();
                }

            }
        });


        TheButtonInTheFirstButtonSheet = (Button) findViewById(R.id.HomeButton);
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
        int ImagesForTheMenu[] = new int[]{R.mipmap.gift, R.mipmap.stage, R.mipmap.user, R.mipmap.user};
        int TextForMenu[] = new int[]{R.string.SearchForAnApartment_Menu, R.string.CreateEvent_Menu, R.string.SetTheApartmentLocation, R.string.SetTheApartmentLocation};
        int HintTextForMenu[] = new int[]{R.string.SearchForAnApartmentHint_Menu, R.string.CreateEventHint_Menu, R.string.SetTheApartmentLocation_hint, R.string.SetTheApartmentLocation_hint};

        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);

        bmb.setButtonEnum(ButtonEnum.Ham);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_4);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_4);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            final HamButton.Builder builder = new HamButton.Builder().listener(new OnBMClickListener() {


                @Override
                public void onBoomButtonClick(int index) {
                    if (index == 0) {
                        new SweetAlertDialog(MapsActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                                .setTitleText("Chose How u wana Place Ur Home On The Map")
                                .setContentText("We Strictly Advice U The Be In ur home While U make this Step")
                                .setCancelText("Search For It !")
                                .setCustomImage(R.mipmap.home)
                                .setConfirmText("I AM IN THE LOCATION !")
                                .showCancelButton(true)
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        Alerter.create(MapsActivity.this)
                                                .setTitle("Alert Title")
                                                .setText("Alert text...")
                                                .setBackgroundColor(R.color.colorAccent)
                                                .show();
                                        sweetAlertDialog.cancel();
                                    }
                                })
                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.cancel();
                                    }
                                })
                                .show();
                    } else if (index == 1) {
                        startActivity(new Intent(getApplicationContext(), EventsActivity.class));
                    } else if (index == 3) {


                    } else if (index == 2) {
                        //new user login
                        final CFirebaseAuth cFirebaseAuth = new CFirebaseAuth();
                        cFirebaseAuth.CFirebaseAuth(MapsActivity.this);
                        checkLocationPermission();
                        if (cFirebaseAuth.currentUser != null) {

                            final FindLocatinDialog findLocatinDialog = new FindLocatinDialog(MapsActivity.this);
                            findLocatinDialog.here_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {
                                        // Call your Alert message
                                        latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                                        mMap.addMarker(new MarkerOptions().position(latLng).title("here"));
                                        findLocatinDialog.dialog.dismiss();
                                    } catch (Exception e) {
                                        Toast.makeText(getApplicationContext(), "Please open your GPS to get Location ..", Toast.LENGTH_SHORT).show();

                                    }
                                    if (mMap != null) {
                                        bottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);


                                        TheButtonInTheFirstButtonSheet.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                bottomSheetBehavior1.setState(BottomSheetBehavior.STATE_HIDDEN);

                                                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                                                mMap.clear();
                                                mMap.addMarker(new MarkerOptions().position(latLng).title("here").icon(
                                                        BitmapDescriptorFactory.fromResource(R.mipmap.house5)
                                                ));

                                            }
                                        });
                                    }
                                }
                            });

                        } else if (cFirebaseAuth.currentUser == null) {
                            Toast.makeText(getApplicationContext(), "please login first ..", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            })

                    .normalImageRes(ImagesForTheMenu[i])
                    .normalTextRes(TextForMenu[i])
                    .subNormalTextRes(HintTextForMenu[i]);


            bmb.addBuilder(builder);
        }


        //


    }

    @Override
    public void onBackPressed() {
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        } else {
            finishAffinity();
        }
    }


}