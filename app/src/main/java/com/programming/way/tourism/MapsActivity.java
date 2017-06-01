package com.programming.way.tourism;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.security.spec.ECField;

public class MapsActivity extends HandlingMaps {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        onCreateHandle();
//        Countries countriesN = new Countries();

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




        // Boom Menu

        //Mibmab
        int ImagesForTheMenu[] = new int[]{R.mipmap.gift, R.mipmap.stage,R.mipmap.user};
        int TextForMenu[] = new int[]{R.string.SearchForAnApartment_Menu, R.string.CreateEvent_Menu,R.string.SetTheApartmentLocation};
        int HintTextForMenu[] = new int[]{R.string.SearchForAnApartmentHint_Menu, R.string.CreateEventHint_Menu,R.string.SetTheApartmentLocation_hint};

        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);
        bmb.setButtonEnum(ButtonEnum.Ham);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_3);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_3);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder().listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {
                    if(index == 0){
                        startActivity(new Intent(getApplicationContext() , NewApartmentInformationActivity.class));
                    }
                    else if(index == 1){
                        startActivity(new Intent(getApplicationContext() , EventsActivity.class));
                    }
                    else if (index == 2){
                        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            checkLocationPermission();
                        }


                        final AlertDialog.Builder alert = new AlertDialog.Builder(MapsActivity.this)
                                .setMessage("Is your appartment here ? (When you click yes you'll continue adding details)");

                                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        LatLng latLng = new LatLng(mLastLocation.getLatitude() , mLastLocation.getLongitude());
                                        mMap.addMarker(new MarkerOptions().position(latLng).title("apartment").icon(BitmapDescriptorFactory.defaultMarker()));
                                        
                                    }
                                })
                                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        alert.setCancelable(true);
                                    }
                                })
                                .create().show();






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

}
