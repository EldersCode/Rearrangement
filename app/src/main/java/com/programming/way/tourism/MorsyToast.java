package com.programming.way.tourism;

import android.app.Activity;
import android.content.Context;

import com.tapadoo.alerter.Alerter;

/**
 * Created by heshamsalama on 6/13/2017.
 */

public class MorsyToast {

    public MorsyToast(Context context,String title,String text,int color) {

    if(color== 0) {
        Alerter.create((Activity) context)
                .setTitle(title)
                .setText(text)

//                                .setIcon(R.drawable.common_google_signin_btn_icon_light_normal_background)
                .setBackgroundColor(R.color.md_yellow_900)
                .show();
    }if (color==1){
            Alerter.create((Activity) context)
                    .setTitle(title)
                    .setText(text)

//                                .setIcon(R.drawable.common_google_signin_btn_icon_light_normal_background)
                    .setBackgroundColor(R.color.md_blue_500)
                    .show();
        }if (color==2){
            Alerter.create((Activity) context)
                    .setTitle(title)
                    .setText(text)

//                                .setIcon(R.drawable.common_google_signin_btn_icon_light_normal_background)
                    .setBackgroundColor(R.color.md_red_700)
                    .show();
        }if (color==3){
            Alerter.create((Activity) context)
                    .setTitle(title)
                    .setText(text)

//                                .setIcon(R.drawable.common_google_signin_btn_icon_light_normal_background)
                    .setBackgroundColor(R.color.md_red_200)
                    .show();
        }if (color==4){
            Alerter.create((Activity) context)
                    .setTitle(title)
                    .setText(text)

//                                .setIcon(R.drawable.common_google_signin_btn_icon_light_normal_background)
                    .setBackgroundColor(R.color.md_green_500)
                    .show();
        }if (color==5){
            Alerter.create((Activity) context)
                    .setTitle(title)
                    .setText(text)

//                                .setIcon(R.drawable.common_google_signin_btn_icon_light_normal_background)
                    .setBackgroundColor(R.color.accent)
                    .show();
        }if (color==6){
            Alerter.create((Activity) context)
                    .setTitle(title)
                    .setText(text)

//                                .setIcon(R.drawable.common_google_signin_btn_icon_light_normal_background)
                    .setBackgroundColor(R.color.colorPrimary)
                    .show();
        }
    }
}
