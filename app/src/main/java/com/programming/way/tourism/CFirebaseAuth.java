package com.programming.way.tourism;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



/**
 * Created by heshamsalama on 6/5/2017.
 */

public class CFirebaseAuth extends Activity {
    private View view , loginLayout , registerLayout;
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();

    private AlertDialog.Builder alertBuilder;

    public void CFirebaseAuth(final Context context){

        view = LayoutInflater.from(context).inflate(R.layout.activity_login, null, true);



        loginLayout = (LinearLayout)view.findViewById(R.id.login_id);
        registerLayout = (LinearLayout) view.findViewById(R.id.register_id);
        final Button login =(Button)view.findViewById(R.id.button);
        Button register_btn = (Button) view.findViewById(R.id.Register);
        Button registerLoginBtn = (Button)view.findViewById(R.id.RegisterR);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginLayout.setVisibility(View.GONE);
                registerLayout.setVisibility(View.VISIBLE);
//                registerLayout.animate().y(0).setDuration(10000);


            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText emailLogin = (EditText)view.findViewById(R.id.Username);
                EditText passwordLogin = (EditText)view.findViewById(R.id.Password);
                String email = null;
                String password = null;
                if(emailLogin.getText().length() > 0 && passwordLogin.getText().length()>0){
                    email =  emailLogin.getText().toString();
                    password = passwordLogin.getText().toString();
                    Login(context , email , password);
               }else{
                    Toast.makeText(context, "Please enter your full data !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        registerLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText userName=(EditText) view.findViewById(R.id.UsernameR);
                EditText password=(EditText)view.findViewById(R.id.PasswordR);
                EditText ConfPass = (EditText)view.findViewById(R.id.confirmPassR);

                String email = null;
                String pass = null;
                if(userName.getText().length()>0 && password.getText().length()>0 && ConfPass.getText().length()>0 ) {
                    email = userName.getText().toString();
                    pass = password.getText().toString();
                    String confirm = ConfPass.getText().toString();
                    if (pass.equals(confirm)) {
                        CFirebaseAuth auth = new CFirebaseAuth();
                        auth.signUpEmail(context, email, pass);
                    } else {
                        Toast.makeText(context, "password and confirmation not matched !", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "Please enter your full data !", Toast.LENGTH_SHORT).show();
                }
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
    
    public void Login(final Context context , String email , String password){

        try{

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(context, "Login Succeeded", Toast.LENGTH_SHORT).show();


                            } else {
//                                    // If sign in fails, display a message to the user.
//                                    Toast.makeText(context, "Login Authentication failed.",
//                                            Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });

        }catch (Exception e){
//            Toast.makeText(context, "Login failed !", Toast.LENGTH_SHORT).show();
        }



    }
    
    public void signUpEmail(final Context context, String email , String password){



            try {
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
                        });
            } catch (Exception e) {
                Log.e("eeeeeeeeee", String.valueOf(e));
            }

            



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
//        if (currentUser != null){
//            alertBuilder.setCancelable(true);
//        }
    }

}
