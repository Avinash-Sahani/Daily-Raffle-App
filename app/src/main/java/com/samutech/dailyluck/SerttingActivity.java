package com.samutech.dailyluck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SerttingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sertting);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    public void rules(View view){

        Intent intent = new Intent(this,GameRules.class);
        startActivity(intent);

    }


    public void howtoplay(View view){

        String url = "https://www.youtube.com/channel/UCNNjNQ-0_aMdrrtusU3FnSA";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);;

    }

    public void mail(View view){

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"dailyluckapp@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
        i.putExtra(Intent.EXTRA_TEXT   , "body of email");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(SerttingActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }

    }

    public void rate(View view){

       Intent i = new Intent(Intent.ACTION_VIEW , Uri.parse("market://details?id=com.bet.compny"));
        startActivity(i);

    }


    public void privacypolicy(View view){

        String url = "https://www.dailyluck.win/p/privacy-policy.html";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);;

    }



    public void term(View view){

        String url = "https://www.dailyluck.win/p/terms-of-service.html";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);;

    }



    public void insta(View view){

        String url = "http://www.instagram.com";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }

    public void facebook(View view){

        String url = "https://www.facebook.com/dailyluckapp";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }


}
