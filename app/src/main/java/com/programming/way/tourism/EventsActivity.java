package com.programming.way.tourism;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.ImageHolder;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondarySwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class EventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        // Building the Test Drawer
        new DrawerBuilder().withActivity(this).build();

        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.drawer_item_home);
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName(R.string.drawer_item_settings);
        SecondaryDrawerItem items3 = new SecondaryDrawerItem().withIdentifier(3).withName("hi");


        ProfileDrawerItem userInformation = new ProfileDrawerItem();
        ProfileDrawerItem RentingCompanyOfTheUser = new ProfileDrawerItem();

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.mipmap.banner)
                .addProfiles(
                        userInformation.withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon(R.mipmap.user),
                        RentingCompanyOfTheUser.withName("Morsy mohamed morsy").withEmail("morsysoker@hotmail.com").withIcon(R.mipmap.man)
                )

                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {

                        // close the drawar after header selection (return false to close)
                        return true;
                    }
                })

                .build();

//Now create your drawer and pass the AccountHeader.Result
        new DrawerBuilder().withAccountHeader(headerResult).addDrawerItems(item2).withActivity(this)
                //additional Drawer setup as shown above
                .build();
       userInformation.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
           @Override
           public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

               return false;
           }
       });

    }

}
