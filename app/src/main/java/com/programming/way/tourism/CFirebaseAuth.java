package com.programming.way.tourism;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



/**
 * Created by heshamsalama on 6/5/2017.
 */

public class CFirebaseAuth extends Activity {
    private View view;
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();

    private AlertDialog.Builder alertBuilder;

    public void CFirebaseAuth(final Context context){

        view = LayoutInflater.from(context).inflate(R.layout.activity_login, null, true);



        Button signUp =(Button)view.findViewById(R.id.button);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userName=(EditText) view.findViewById(R.id.Username);
                EditText password=(EditText)view.findViewById(R.id.Password);
CFirebaseAuth auth=new CFirebaseAuth();
               auth.signUpEmail(context,userName.getText().toString(),password.getText().toString());

            }
        });
        alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (view != null) {

                    ViewGroup parentViewGroup = (ViewGroup) view.getParent();

                    if (parentViewGroup != null) {
                        parentViewGroup.removeAllViews();
                    }
                }
                dialog.dismiss();
            }
        });
        alertBuilder.setView(view);
        AlertDialog dialog = alertBuilder.create();

        dialog.show();

    }
    public void signUpEmail(final Context context, String email , String password){


        try{
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
////                            updateUI(user);
                            Toast.makeText(context, "Authentication Success",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });}catch (Exception e){Log.e("eeeeeeeeee", String.valueOf(e));}
//        EmailPasswordActivity.java
    }

//    public void AuthAnonymously(){
//    mAuth = FirebaseAuth.getInstance();
//
//    mAuth.signInAnonymously()
//            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if (task.isSuccessful()) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d(TAG, "signInAnonymously:success");
//                        FirebaseUser user = mAuth.getCurrentUser();
////                        updateUI(user);
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w(TAG, "signInAnonymously:failure", task.getException());
//                        Toast.makeText(getApplicationContext(), "Authentication failed.",
//                                Toast.LENGTH_SHORT).show();
////                        updateUI(null);
//                    }

                    // ...
//                }
//            });
//}
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

}