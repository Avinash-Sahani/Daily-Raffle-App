package com.samutech.dailyluck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.skyfishjy.library.RippleBackground;

public class SplashScreen extends AppCompatActivity {


    String drawnamestring;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RippleBackground rippleBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


       getdata();





















        rippleBackground = findViewById(R.id.content);
        rippleBackground.startRippleAnimation();


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    private void getdata() {

        db.collection("Draws").whereEqualTo("status", "Active").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot snapshot : task.getResult()) {




                    drawnamestring = snapshot.getString("name");
                    String number=snapshot.getString("number");
  //               Bundle bundle = new Bundle();
//bundle.putString("number",number);

                }


            }


        });




    }


    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null){


            db.collection("Users").document(mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                    if (task.getResult().exists()){


                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Intent intent = new Intent(SplashScreen.this,HomeActivity.class);
                                startActivity(intent);
                                finish();


                            }
                        },4000);

                    }else {



                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Intent intent = new Intent(SplashScreen.this,Username.class);
                                startActivity(intent);
                                finish();


                            }
                        },4000);

                    }


                }
            });

        }else {




            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {


                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                    finish();


                }
            },4000);


        }

    }
}
