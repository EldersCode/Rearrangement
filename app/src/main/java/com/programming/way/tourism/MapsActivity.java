package com.programming.way.tourism;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class MapsActivity extends HandlingMaps {

//////////////////submitBuildingInfo
private EditText priceEditText;
    private EditText ApartmentAreaEditText;
    private EditText noOfBedRoomsEditText;
    private EditText noOfBathRoomsEditText;
    private Switch parkingLotsSwitch;
    private Switch LivingRoomSwitch;
    private Switch KitchenSwitch;
    private Switch coolingSystemSwitch;
    private Switch NegotiablePriceSwitch;
    private LinearLayout petsLayout;
    private Switch petsSwitch;
    //private DatabaseReference firebaseDatabase;
    private FirebaseDatabase database;
    private ImageView cameraImg;
    private Button locateFlat;
    private int flatsNo =1;
    private DatabaseReference houses;
    ////////////////////////////////
    String buildingType;

    boolean flag = true;
    private FirebaseAuth mAuth;
    LatLng latLng;
    int count = 0;
    final static int TAKE_PHOTO_CODE = 100;
    BottomSheetBehavior bottomSheetBehavior;
    BottomSheetBehavior bottomSheetBehavior1;
    Button TheButtonInTheFirstButtonSheet;
    View FabBtn;
    FloatingActionButton logoutFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);



        onCreateHandle();
        AlertDialogCustom dialogCustom ;

/////////////////////////////
        //declearing inistances
        petsLayout = (LinearLayout) findViewById(R.id.switchOn_pets);
        petsSwitch = (Switch) findViewById(R.id.petSwitch);
        priceEditText = (EditText) findViewById(R.id.Price);
        ApartmentAreaEditText = (EditText) findViewById(R.id.area);
        noOfBedRoomsEditText = (EditText) findViewById(R.id.bedrooms);
        noOfBathRoomsEditText = (EditText) findViewById(R.id.bathrooms);
        parkingLotsSwitch=(Switch)findViewById(R.id.ParkingSwitch);
        LivingRoomSwitch=(Switch)findViewById(R.id.livingRoomSwitch);
        KitchenSwitch=(Switch)findViewById(R.id.kitchenSwitch);
        coolingSystemSwitch=(Switch)findViewById(R.id.coolingSystemSwitch);
        NegotiablePriceSwitch=(Switch)findViewById(R.id.negotiablePriceSwitch);
        locateFlat = (Button) findViewById(R.id.locateFlat);

        //buttom sheet home buttom
        TheButtonInTheFirstButtonSheet = (Button) findViewById(R.id.HomeButton);

        new SubmitBuildingInfo(buildingType,locateFlat,petsLayout,petsSwitch,priceEditText,ApartmentAreaEditText,noOfBedRoomsEditText
        ,noOfBathRoomsEditText,parkingLotsSwitch,LivingRoomSwitch,KitchenSwitch,coolingSystemSwitch,NegotiablePriceSwitch);
/////////////////////////////////

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
/////////////////////////////////////////////////////
                        /*FindLocatinDialog dialog=new FindLocatinDialog();
                        dialog.FindLocatinDialog(MapsActivity.this);*/
                    } else if (index == 3) {

                          new AlertDialogCustom(MapsActivity.this,SweetAlertDialog.CUSTOM_IMAGE_TYPE,"#7c4b94e1","Iam here",null,"ok",R.mipmap.home,"CloseAlertdialog");
                    } else if (index == 1) {
                        startActivity(new Intent(getApplicationContext(), EventsActivity.class));
                    } else if (index == 2) {
                        //new user login
                        final CFirebaseAuth cFirebaseAuth = new CFirebaseAuth();
                        cFirebaseAuth.CFirebaseAuth(MapsActivity.this);
                        checkLocationPermission();
                        if(cFirebaseAuth.currentUser != null) {

                            final FindLocatinDialog findLocatinDialog = new FindLocatinDialog(MapsActivity.this);
                            findLocatinDialog.here_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {
                                        // Call your Alert message
                                        latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                                        mMap.addMarker(new MarkerOptions().position(latLng).title("here"));
                                        flag = true;
                                        findLocatinDialog.dialog.dismiss();
                                    }catch (Exception e){
                                        findLocatinDialog.dialog.dismiss();
/*                                        final AlertDialog.Builder alert = new AlertDialog.Builder(MapsActivity.this)
                                                .setMessage("Please enable your GPS and check the internet connection..");
                                        alert.setTitle("Information");
                                        alert.setIcon(R.mipmap.alarm);
                                        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                alert.setCancelable(true);
                                            }
                                        });
                                        alert.create();
                                        alert.show();*/
                                        new AlertDialogCustom(MapsActivity.this,SweetAlertDialog.CUSTOM_IMAGE_TYPE,"#7c4b94e1","The Gps Is Closed","Please enable your GPS..","ok",R.mipmap.alarm,"CloseAlertdialog");
                                        flag = false;

                                    }
                                    if(flag){

                                        bottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);


                                        TheButtonInTheFirstButtonSheet.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                                    buildingType="home";
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

                        }
                        else if (cFirebaseAuth.currentUser == null){
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
        if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }

        else{
            finishAffinity();
        }
    }


}