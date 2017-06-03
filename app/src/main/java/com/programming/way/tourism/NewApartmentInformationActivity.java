package com.programming.way.tourism;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewApartmentInformationActivity extends AppCompatActivity implements LocationListener {



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

    private Button locateFlat;
    private int flatsNo =1;
    private DatabaseReference houses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_apartment_information);
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



        petsLayout.setVisibility(View.GONE);

        petsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    petsLayout.setVisibility(View.VISIBLE);
                } else {
                    petsLayout.setVisibility(View.GONE);

                }

            }
        });
        database = FirebaseDatabase.getInstance();

        houses = database.getReference("houses");
//
        houses.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                flatsNo= (int) dataSnapshot.getChildrenCount()+1;
                Log.e("nnnnnnn", String.valueOf(flatsNo));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        locateFlat = (Button) findViewById(R.id.locateFlat);
        locateFlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext() , MapsActivity.class);

                DatabaseReference users = database.getReference("users");
                DatabaseReference regions = database.getReference("regions");

                users.child("userId/" + "houses/" + "owened/" + "houseId/"+flatsNo).setValue("");

                houses.child(flatsNo + "/bedRoomsNo/").setValue(noOfBedRoomsEditText.getText().toString());
                houses.child(flatsNo + "/bathNo/").setValue(noOfBathRoomsEditText.getText().toString());
                houses.child(flatsNo + "/price/").setValue(priceEditText.getText().toString());
                houses.child(flatsNo + "/parking/").setValue(String.valueOf(parkingLotsSwitch.isChecked()));
                houses.child(flatsNo + "/negotiablePrice/").setValue(String.valueOf(NegotiablePriceSwitch.isChecked()));
                houses.child(flatsNo + "/livingRoom/").setValue(String.valueOf(LivingRoomSwitch.isChecked()));
                houses.child(flatsNo + "/pets/").setValue("boolean");
                houses.child(flatsNo + "/kitchen/").setValue(String.valueOf(KitchenSwitch.isChecked()));
                houses.child(flatsNo + "/coolingSystem/").setValue(String.valueOf(coolingSystemSwitch.isChecked()));
                houses.child(flatsNo + "/area/").setValue(ApartmentAreaEditText.getText().toString());
                houses.child(flatsNo + "/houseIdNo/" + "location/").setValue("");
                Toast.makeText(getApplicationContext(),"sending",Toast.LENGTH_LONG).show();
                regions.child("contry/" + "city/" + flatsNo).setValue("location");
                Toast.makeText(getApplicationContext() , "Data Sent Successfully .." , Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MapsActivity.class));

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
