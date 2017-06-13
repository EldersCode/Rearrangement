package com.programming.way.tourism;

import android.animation.Animator;
import android.app.Activity;
import android.app.ProgressDialog;
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

    AlertDialog dialog;
    private AlertDialog.Builder alertBuilder;
    ProgressDialog progressD ;
    String email = null;
    String pass = null;

    // Check if user is signed in (non-null) and update UI accordingly.

        private FirebaseAuth mAuth= FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();



    public void CFirebaseAuth(final Context context){

        view = LayoutInflater.from(context).inflate(R.layout.activity_login, null, true);
        alertBuilder= new AlertDialog.Builder(context);



        if(currentUser == null ) {

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

                mAuth= FirebaseAuth.getInstance();
                currentUser = mAuth.getCurrentUser();


                    EditText emailLogin = (EditText) view.findViewById(R.id.Username);
                    EditText passwordLogin = (EditText) view.findViewById(R.id.Password);
                    email = null;
                    pass = null;
                    if (emailLogin.getText().length() > 0 && passwordLogin.getText().length() > 0) {
                        email = emailLogin.getText().toString();
                        pass = passwordLogin.getText().toString();
                        Login(context, email, pass);
                    } else {
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

                 email = null;
                 pass = null;
                if(userName.getText().length()>0 && password.getText().length()>0 && ConfPass.getText().length()>0 ) {
                    email = userName.getText().toString();
                    pass = password.getText().toString();
                    String confirm = ConfPass.getText().toString();
                    if (pass.equals(confirm)) {
                        signUpEmail(context, email, pass);
                    } else {
                        Toast.makeText(context, "password and confirmation not matched !", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "Please enter your full data !", Toast.LENGTH_SHORT).show();
                }
            }
        });


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
         dialog = alertBuilder.create();

        dialog.show();

        }else if(currentUser != null){

            mAuth= FirebaseAuth.getInstance();
            currentUser = mAuth.getCurrentUser();
              if(currentUser.isEmailVerified()){

                AfterLogin(context);

            }else if (!currentUser.isEmailVerified()){
                Toast.makeText(context, "Please verify your email first !", Toast.LENGTH_SHORT).show();
            }

        }

    }
    
    public void Login(final Context context , String email , String password){

            try {

                progressD = new ProgressDialog(context);
                progressD.setMessage("Logging in ...");
                progressD.show();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    mAuth= FirebaseAuth.getInstance();
                                    currentUser = mAuth.getCurrentUser();
                                    if(currentUser.isEmailVerified()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        progressD.dismiss();
                                        dialog.dismiss();
                                        Toast.makeText(context, "Login Succeeded", Toast.LENGTH_SHORT).show();
                                        //here we go to the next action
                                        AfterLogin(context);
                                    }else{
                                        progressD.dismiss();
                                        Toast.makeText(context, "Please Verify your email first !", Toast.LENGTH_SHORT).show();
                                    }



                                } else {
//                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(context, "Login Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    progressD.dismiss();
                                }

                                // ...
                            }
                        });

            } catch (Exception e) {
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
                                    Toast.makeText(context, "Authentication Success",
                                            Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    currentUser = mAuth.getCurrentUser();


                                    VerifyEmail(currentUser , context);
                                    mAuth.signOut();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(context, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            } catch (Exception e) {
                Log.e("eeeeeeeeee", String.valueOf(e));
            }

            



    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null){
//            alertBuilder.setCancelable(true);
//        }
    }

    public void AfterLogin(Context context){

        Toast.makeText(context, "a7la mesa 3leek enta tmam", Toast.LENGTH_SHORT).show();
    }

    public void VerifyEmail(FirebaseUser user , final Context context){
        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(context, "Verification sent", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

}
