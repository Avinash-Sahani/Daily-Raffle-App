package com.samutech.dailyluck.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.samutech.dailyluck.Config;
import com.samutech.dailyluck.FairDraw;
import com.samutech.dailyluck.GameRules;
import com.samutech.dailyluck.GetTicket;
import com.samutech.dailyluck.R;
import com.samutech.dailyluck.SerttingActivity;
import com.samutech.dailyluck.model.TicketsModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.view.MaterialIntroView;

import static io.opencensus.tags.TagKey.MAX_LENGTH;

public class Home extends Fragment {

    View view;

    TextView balance, tickets, drawname, time, price, fairdraw;
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    long milliseconds, startTime, diff;
    ProgressBar progressBar;
    String current_draw=null;
    String final_balance="0";

    Button addticket;
    String drawnamestring, luckynumber,price1,price2,price3;
    ImageView hints;
    ArrayList<TicketsModel> list = new ArrayList<>();
    int balanceval;
    private AdView adView;
    private InterstitialAd interstitialAd;
    String TAG = "NN";
    int ticlets;
    private RewardedAd rewardedAd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.home, container, false);

        AudienceNetworkAds.initialize(view.getContext());

        balance = view.findViewById(R.id.balance);
        tickets = view.findViewById(R.id.tickets);
        drawname = view.findViewById(R.id.drawname);
        time = view.findViewById(R.id.time);
        price = view.findViewById(R.id.price);
        progressBar = view.findViewById(R.id.progress);
        getdata();
       // Toast.makeText(getActivity(),random(),Toast.LENGTH_SHORT).show();

        rewardedAd = new RewardedAd(getActivity(),
                "ca-app-pub-7282278288207848/8849735616");


        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
            }

            @Override
            public void onRewardedAdFailedToLoad(int errorCode) {
                // Ad failed to load.
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);

        addticket = view.findViewById(R.id.getticket);
        addticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (list.size() == 50){


                    Toast.makeText(v.getContext(), "No Tickets Found", Toast.LENGTH_SHORT).show();

                }else {




                if (interstitialAd.isAdLoaded()) {

                    interstitialAd.show();
                } else {

                    if (rewardedAd.isLoaded()){

                        RewardedAdCallback adCallback = new RewardedAdCallback() {
                            @Override
                            public void onRewardedAdOpened() {
                                // Ad opened.
                            }

                            @Override
                            public void onRewardedAdClosed() {
                                // Ad closed.

                                Intent intent = new Intent(view.getContext(), GetTicket.class);
                                intent.putExtra("name", drawnamestring);
                                intent.putExtra("lucky", luckynumber);
                                intent.putExtra("1stprice",price1);
                                intent.putExtra("2ndprice",price2);
                                intent.putExtra("3rdprice",price3);
                               // Toast.makeText(getActivity(),price3,Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }

                            @Override
                            public void onUserEarnedReward(@NonNull RewardItem reward) {
                                // User earned reward.
                            }

                            @Override
                            public void onRewardedAdFailedToShow(int errorCode) {
                                // Ad failed to display.
                            }
                        };

                        rewardedAd.show(getActivity(),adCallback);

                    }else {


                        Intent intent = new Intent(view.getContext(), GetTicket.class);
                        intent.putExtra("name", drawnamestring);
                        intent.putExtra("lucky", luckynumber);
                        intent.putExtra("1stprice",price1);
                        intent.putExtra("2ndprice",price2);
                        intent.putExtra("3rdprice",price3);
                        startActivity(intent);

                    }

                }
                }

            }
        });


        adView = new AdView(view.getContext(), "579387059568822_579480736226121", AdSize.BANNER_HEIGHT_50);

        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) view.findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(adView);

        // Request an ad
        adView.loadAd();


        interstitialAd = new InterstitialAd(view.getContext(), "579387059568822_579482366225958");
        // Set listeners for the Interstitial Ad
        interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.");


                Intent intent = new Intent(view.getContext(), GetTicket.class);
                intent.putExtra("name", drawnamestring);
                intent.putExtra("lucky", luckynumber);
                intent.putExtra("1stprice",price1);
                intent.putExtra("2ndprice",price2);
                intent.putExtra("3rdprice",price3);
                startActivity(intent);

            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d(TAG, "Interstitial ad impression logged!");
            }
        });

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd();


        hints = view.findViewById(R.id.hints);
        hints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new MaterialIntroView.Builder(getActivity())
                        .enableDotAnimation(true)
                        .enableIcon(false)
                        .setFocusGravity(FocusGravity.CENTER)
                        .setFocusType(Focus.MINIMUM)
                        .setDelayMillis(500)
                        .enableFadeAnimation(true)
                        .performClick(true)
                        .setInfoText("Hint : One of the Number is " + luckynumber.substring(0, 1))
                        .dismissOnTouch(true)

                        .setTarget(hints)
                        .setUsageId(random()) //THIS SHOULD BE UNIQUE ID
                        .show();
            }
        });

        fairdraw = view.findViewById(R.id.fairdraw);
        fairdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(view.getContext(), FairDraw.class);
                startActivity(intent);


            }
        });

        ImageView rulesbutton = view.findViewById(R.id.rulesbutton);

        final Animation myAnim = AnimationUtils.loadAnimation(view.getContext(), R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 15);
        myAnim.setInterpolator(interpolator);

        rulesbutton.startAnimation(myAnim);

        rulesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(view.getContext(), GameRules.class);
                startActivity(intent);

            }
        });

        ImageView setting = view.findViewById(R.id.setting);

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(view.getContext(), SerttingActivity.class);
                startActivity(intent);

            }
        });


        balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (balanceval < 100) {

                    new MaterialIntroView.Builder(getActivity())
                            .enableDotAnimation(true)
                            .enableIcon(false)
                            .setFocusGravity(FocusGravity.CENTER)
                            .setFocusType(Focus.MINIMUM)
                            .setDelayMillis(500)
                            .enableFadeAnimation(true)
                            .performClick(true)
                            .setInfoText("You should have a minimum of 100$ balance to cash out")
                            .dismissOnTouch(true)

                            .setTarget(balance)
                            .setUsageId(random()) //THIS SHOULD BE UNIQUE ID
                            .show();
                }

            }
        });

        return view;
    }

    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(MAX_LENGTH);
        char tempChar;
        for (int i = 0; i < randomLength; i++) {
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }

    private void getdata() {

        db.collection("Draws").whereEqualTo("status", "Active").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot snapshot : task.getResult()) {


                       String name = snapshot.getString("name");
                       Config.draw = name;
                       gettickets(name);

                        current_draw=name;
                       drawname.setText(snapshot.getString("name"));
                       price.setText("Prize : " + snapshot.getString("1stprice") + "$");
                       drawnamestring = snapshot.getString("name");
                      countDownStart(snapshot.getString("end_time"));
                       luckynumber = snapshot.getString("luckynumber");
                       price1=snapshot.getString("1stprice");
                    price2=snapshot.getString("2ndprice");
                    price3=snapshot.getString("3rdprice");



                }

//                if (Config.draw == null){
//
//                    AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
//                    alert.setCancelable(false);
//                    alert.setMessage("Current Draw is being finalized to find winners. Please wait");
//                    alert.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                            ((Activity) view.getContext()).finish();
//
//
//                        }
//                    });
//                    alert.show();
//

                update_balance(current_draw);


//                }
//




            }
        });
    }

    private void update_balance(final String current_draw) {










   db.collection("Tickets").whereEqualTo("uid",uid).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
       @Override
       public void onComplete(@NonNull Task<QuerySnapshot> task) {


           for (DocumentSnapshot snapshot : task.getResult()) {


               String dname=snapshot.getString("draw");

               if(!(current_draw.equals(dname)))

               {


                   final_balance=String.valueOf(Integer.valueOf(final_balance)+Integer.valueOf(snapshot.getString("amount")));



               }







           }

           addbalancetouser(final_balance);



       }
   });



















    }

    private void addbalancetouser(String final_balance) {





        db.collection("Users").document(uid).update("balance",final_balance);
        balance.setText("Balance\n" + final_balance+ "$");
    }

    private void gettickets(String name) {


        db.collection("Tickets").whereEqualTo("draw", name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.getResult().size() == 0) {

                    tickets.setText("Tickets\n0");
                } else {

                    for (DocumentSnapshot snapshot : task.getResult()) {


                        String ui = snapshot.getString("uid");


                        if (ui.equals(uid)) {

                            TicketsModel model = new TicketsModel();
                            model.setLuckynumber(snapshot.getString("uid"));
                            list.add(model);
                        }

                        tickets.setText("Tickets\n" + list.size());


                    }

                }

            }
        });

    }

    public RewardedAd createAndLoadRewardedAd() {

        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
            }

            @Override
            public void onRewardedAdFailedToLoad(int errorCode) {
                // Ad failed to load.
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
        return rewardedAd;
    }


    public void countDownStart(final String datte) {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd");
                    // Please here set your event date//YYYY-MM-DD
                    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

                    Date futureDate = dateFormat.parse(datte);

                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;

                        String get = String.format("%02d", days) + "d: " + String.format("%02d", hours) + "h: " + String.format("%02d", minutes) + "m: " + String.format("%02d", seconds) + "s ";
                        time.setText(get);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);

        progressBar.setVisibility(View.GONE);

    }


    class MyBounceInterpolator implements android.view.animation.Interpolator {
        private double mAmplitude = 1;
        private double mFrequency = 10;

        MyBounceInterpolator(double amplitude, double frequency) {
            mAmplitude = amplitude;
            mFrequency = frequency;
        }

        public float getInterpolation(float time) {
            return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) *
                    Math.cos(mFrequency * time) + 1);
        }
    }

}
