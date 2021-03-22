package com.samutech.dailyluck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.samutech.dailyluck.model.ModelPrice;
import com.samutech.dailyluck.model.PriceModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GetTicket extends AppCompatActivity {

    String finalprice;
    Toolbar toolbar;
    TextView onetext,twotext,threetext,fourtext,fivetext,sixtext;
    Button one,two,three,four,five,six,seven,eight,nine;
    Button addticket;
    String name,lucky,price1,price2,price3;
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String status;
    TextView hint;
    ArrayList<PriceModel> list = new ArrayList<>();
    String is_lose="Win";
    String current_user_amout=null;
    ArrayList<ModelPrice> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ModelPrice sample=new ModelPrice();



        arrayList.add(sample);
        name = getIntent().getExtras().getString("name");
        lucky = getIntent().getExtras().getString("lucky");
        price1 = getIntent().getExtras().getString("1stprice");
        price2 = getIntent().getExtras().getString("2ndprice");
        price3 = getIntent().getExtras().getString("3rdprice");
//Toast.makeText(GetTicket.this,price3,Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_get_ticket);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        onetext = findViewById(R.id.onetext);
        twotext = findViewById(R.id.twotext);
        threetext = findViewById(R.id.threetext);
        fourtext = findViewById(R.id.fourtext);
        fivetext = findViewById(R.id.fivetext);
        sixtext = findViewById(R.id.sixtext);

        hint = findViewById(R.id.hint);
        hint.setText("Hint : One of the number is " + lucky.substring(0,1));
        addticket = findViewById(R.id.addticket);




        addticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              if (!TextUtils.isEmpty(onetext.getText().toString()) && !TextUtils.isEmpty(twotext.getText().toString()) && !TextUtils.isEmpty(threetext.getText().toString()) && !TextUtils.isEmpty(fourtext.getText().toString()) && !TextUtils.isEmpty(fivetext.getText().toString()) && !TextUtils.isEmpty(sixtext.getText().toString())){

                  String val = onetext.getText().toString()+twotext.getText().toString()+threetext.getText().toString()+fourtext.getText().toString()+fivetext.getText().toString()+sixtext.getText().toString();

                    getWinningPrice(val,lucky);
                    String winning_status=getWinningStats(val,lucky);

                    final ProgressDialog progressDialog = new ProgressDialog(GetTicket.this);
                    progressDialog.setMessage("Adding Ticket");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    HashMap<String,Object> hashMap = new HashMap<>();
                    hashMap.put("uid",uid);
                    hashMap.put("luckynumber",val);
                    hashMap.put("draw",name);
                    hashMap.put("time", Timestamp.now());
                    hashMap.put("status",is_lose);
                    hashMap.put("amount",finalprice);
                    hashMap.put("rank",winning_status);
                    String key = db.collection("Tickets").document().getId().toString();



                    db.collection("Users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {




                            current_user_amout=task.getResult().getString("balance");



                        }

                    });








                    db.collection("Tickets").document(key).set(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){

                                progressDialog.dismiss();
                                Intent intent = new Intent(GetTicket.this,HomeActivity.class);
                             //   update_amount(current_user_amout,finalprice);
                                startActivity(intent);
                                finish();


                            }

                        }
                    });


              }


            }
        });

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(onetext.getText().toString()) ){

                    onetext.setText("1");
                }

                else {

                    if (TextUtils.isEmpty(twotext.getText().toString())){

                        twotext.setText("1");
                    }else {


                        if (TextUtils.isEmpty(threetext.getText().toString())){

                            threetext.setText("1");
                        }else {


                            if (TextUtils.isEmpty(fourtext.getText().toString())){

                                fourtext.setText("1");
                            }else {


                                if (TextUtils.isEmpty(fivetext.getText().toString())){

                                    fivetext.setText("1");
                                }else {


                                    if (TextUtils.isEmpty(sixtext.getText().toString())){

                                        sixtext.setText("1");
                                    }
                                }

                            }


                        }

                    }

                }

            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(onetext.getText().toString())){

                    onetext.setText("2");
                }else {

                    if (TextUtils.isEmpty(twotext.getText().toString())){

                        twotext.setText("2");
                    }else {


                        if (TextUtils.isEmpty(threetext.getText().toString())){

                            threetext.setText("2");
                        }else {


                            if (TextUtils.isEmpty(fourtext.getText().toString())){

                                fourtext.setText("2");
                            }else {


                                if (TextUtils.isEmpty(fivetext.getText().toString())){

                                    fivetext.setText("2");
                                }else {


                                    if (TextUtils.isEmpty(sixtext.getText().toString())){

                                        sixtext.setText("2");
                                    }
                                }

                            }


                        }

                    }

                }

            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(onetext.getText().toString())){

                    onetext.setText("3");
                }else {

                    if (TextUtils.isEmpty(twotext.getText().toString())){

                        twotext.setText("3");
                    }else {


                        if (TextUtils.isEmpty(threetext.getText().toString())){

                            threetext.setText("3");
                        }else {


                            if (TextUtils.isEmpty(fourtext.getText().toString())){

                                fourtext.setText("3");
                            }else {


                                if (TextUtils.isEmpty(fivetext.getText().toString())){

                                    fivetext.setText("3");
                                }else {


                                    if (TextUtils.isEmpty(sixtext.getText().toString())){

                                        sixtext.setText("3");
                                    }
                                }

                            }


                        }

                    }

                }
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(onetext.getText().toString())){

                    onetext.setText("4");
                }else {

                    if (TextUtils.isEmpty(twotext.getText().toString())){

                        twotext.setText("4");
                    }else {


                        if (TextUtils.isEmpty(threetext.getText().toString())){

                            threetext.setText("4");
                        }else {


                            if (TextUtils.isEmpty(fourtext.getText().toString())){

                                fourtext.setText("4");
                            }else {


                                if (TextUtils.isEmpty(fivetext.getText().toString())){

                                    fivetext.setText("4");
                                }else {


                                    if (TextUtils.isEmpty(sixtext.getText().toString())){

                                        sixtext.setText("4");
                                    }
                                }

                            }


                        }

                    }

                }
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(onetext.getText().toString())){

                    onetext.setText("5");
                }else {

                    if (TextUtils.isEmpty(twotext.getText().toString())){

                        twotext.setText("5");
                    }else {


                        if (TextUtils.isEmpty(threetext.getText().toString())){

                            threetext.setText("5");
                        }else {


                            if (TextUtils.isEmpty(fourtext.getText().toString())){

                                fourtext.setText("5");
                            }else {


                                if (TextUtils.isEmpty(fivetext.getText().toString())){

                                    fivetext.setText("5");
                                }else {


                                    if (TextUtils.isEmpty(sixtext.getText().toString())){

                                        sixtext.setText("5");
                                    }
                                }

                            }


                        }

                    }

                }
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(onetext.getText().toString())){

                    onetext.setText("6");
                }else {

                    if (TextUtils.isEmpty(twotext.getText().toString())){

                        twotext.setText("6");
                    }else {


                        if (TextUtils.isEmpty(threetext.getText().toString())){

                            threetext.setText("6");
                        }else {


                            if (TextUtils.isEmpty(fourtext.getText().toString())){

                                fourtext.setText("6");
                            }else {


                                if (TextUtils.isEmpty(fivetext.getText().toString())){

                                    fivetext.setText("6");
                                }else {


                                    if (TextUtils.isEmpty(sixtext.getText().toString())){

                                        sixtext.setText("6");
                                    }
                                }

                            }


                        }

                    }

                }
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(onetext.getText().toString())){

                    onetext.setText("7");
                }else {

                    if (TextUtils.isEmpty(twotext.getText().toString())){

                        twotext.setText("7");
                    }else {


                        if (TextUtils.isEmpty(threetext.getText().toString())){

                            threetext.setText("7");
                        }else {


                            if (TextUtils.isEmpty(fourtext.getText().toString())){

                                fourtext.setText("7");
                            }else {


                                if (TextUtils.isEmpty(fivetext.getText().toString())){

                                    fivetext.setText("7");
                                }else {


                                    if (TextUtils.isEmpty(sixtext.getText().toString())){

                                        sixtext.setText("7");
                                    }
                                }

                            }


                        }

                    }

                }
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(onetext.getText().toString())){

                    onetext.setText("8");
                }else {

                    if (TextUtils.isEmpty(twotext.getText().toString())){

                        twotext.setText("8");
                    }else {


                        if (TextUtils.isEmpty(threetext.getText().toString())){

                            threetext.setText("8");
                        }else {


                            if (TextUtils.isEmpty(fourtext.getText().toString())){

                                fourtext.setText("8");
                            }else {


                                if (TextUtils.isEmpty(fivetext.getText().toString())){

                                    fivetext.setText("8");
                                }else {


                                    if (TextUtils.isEmpty(sixtext.getText().toString())){

                                        sixtext.setText("8");
                                    }
                                }

                            }


                        }

                    }

                }
            }
        });


        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(onetext.getText().toString())){

                    onetext.setText("9");
                }else {

                    if (TextUtils.isEmpty(twotext.getText().toString())){

                        twotext.setText("9");
                    }else {


                        if (TextUtils.isEmpty(threetext.getText().toString())){

                            threetext.setText("9");
                        }else {


                            if (TextUtils.isEmpty(fourtext.getText().toString())){

                                fourtext.setText("9");
                            }else {


                                if (TextUtils.isEmpty(fivetext.getText().toString())){

                                    fivetext.setText("9");
                                }else {


                                    if (TextUtils.isEmpty(sixtext.getText().toString())){

                                        sixtext.setText("9");
                                    }
                                }

                            }


                        }

                    }

                }
            }
        });

    }

    private void update_amount(String current_user_amout, String finalprice) {

        String final_amount=String.valueOf(Integer.valueOf(current_user_amout)+Integer.valueOf(finalprice));



//        db.collection("Users").document(uid).update("balance",final_amount);


    }

    public void getWinningPrice(String val, String lucky) {





        if(val.equals(lucky))
        {
          finalprice= price1;

        }
        else if(val.substring(0,val.length()-1).equals(lucky.substring(0,lucky.length()-1)))
        {
            finalprice= price2;


        }
        else if(val.substring(0,val.length()-2).equals(lucky.substring(0,lucky.length()-2)))
        {
            finalprice = price3;

        }
        else
        {
         finalprice= "0";
        }
    }


    public String getWinningStats(String val, String lucky) {


        String status=null;
        if(val.equals(lucky))
        {
            status="1st Prize";

        }
        else if(val.substring(0,val.length()-1).equals(lucky.substring(0,lucky.length()-1)))
        {
            status="2nd Pirze";


        }
          else if(val.substring(0,val.length()-2).equals(lucky.substring(0,lucky.length()-2)))
        {
            status="3rd Pirze";


        }
          else
        {
            status="No Prize";
            is_lose="Lose";
        }






//db.collection("Draws").document()

        return status;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.quickpick:

                String val = getAlphaNumericString(1);
                if (val.equals("1")){

                    onetext.setText(lucky.substring(0,1));
                    twotext.setText(getAlphaNumericString(1));
                    threetext.setText(getAlphaNumericString(1));
                    fourtext.setText(getAlphaNumericString(1));
                    fivetext.setText(getAlphaNumericString(1));
                    sixtext.setText(getAlphaNumericString(1));

                }else {

                    if (val.equals("2")){

                        twotext.setText(lucky.substring(0,1));
                        onetext.setText(getAlphaNumericString(1));
                        threetext.setText(getAlphaNumericString(1));
                        fourtext.setText(getAlphaNumericString(1));
                        fivetext.setText(getAlphaNumericString(1));
                        sixtext.setText(getAlphaNumericString(1));
                    }else {

                        if (val.equals("3")){

                            threetext.setText(lucky.substring(0,1));
                            onetext.setText(getAlphaNumericString(1));
                            twotext.setText(getAlphaNumericString(1));
                            fourtext.setText(getAlphaNumericString(1));
                            fivetext.setText(getAlphaNumericString(1));
                            sixtext.setText(getAlphaNumericString(1));
                        }else {

                            if (val.equals("4")){

                                fourtext.setText(lucky.substring(0,1));
                                onetext.setText(getAlphaNumericString(1));
                                twotext.setText(getAlphaNumericString(1));
                                threetext.setText(getAlphaNumericString(1));
                                fivetext.setText(getAlphaNumericString(1));
                                sixtext.setText(getAlphaNumericString(1));
                            }else {

                                if (val.equals("5")){

                                    fivetext.setText(lucky.substring(0,1));
                                    onetext.setText(getAlphaNumericString(1));
                                    threetext.setText(getAlphaNumericString(1));
                                    fourtext.setText(getAlphaNumericString(1));
                                    twotext.setText(getAlphaNumericString(1));
                                    sixtext.setText(getAlphaNumericString(1));
                                }else {

                                    if (val.equals("6")){

                                        sixtext.setText(lucky.substring(0,1));
                                        onetext.setText(getAlphaNumericString(1));
                                        threetext.setText(getAlphaNumericString(1));
                                        fourtext.setText(getAlphaNumericString(1));
                                        twotext.setText(getAlphaNumericString(1));
                                        fivetext.setText(getAlphaNumericString(1));
                                    }else {

                                        if (val.equals("7")){

                                            onetext.setText(lucky.substring(0,1));
                                            fivetext.setText(getAlphaNumericString(1));
                                            threetext.setText(getAlphaNumericString(1));
                                            fourtext.setText(getAlphaNumericString(1));
                                            twotext.setText(getAlphaNumericString(1));
                                            sixtext.setText(getAlphaNumericString(1));
                                        }else {

                                            if (val.equals("8")){

                                                twotext.setText(lucky.substring(0,1));
                                                onetext.setText(getAlphaNumericString(1));
                                                threetext.setText(getAlphaNumericString(1));
                                                fourtext.setText(getAlphaNumericString(1));
                                                fivetext.setText(getAlphaNumericString(1));
                                                sixtext.setText(getAlphaNumericString(1));
                                            }else {

                                                if (val.equals("5")){

                                                    fivetext.setText(lucky.substring(0,1));
                                                    onetext.setText(getAlphaNumericString(1));
                                                    threetext.setText(getAlphaNumericString(1));
                                                    fourtext.setText(getAlphaNumericString(1));
                                                    twotext.setText(getAlphaNumericString(1));
                                                    sixtext.setText(getAlphaNumericString(1));
                                                }


                                            }


                                        }


                                    }


                                }


                            }


                        }

                    }
                }

               // pinView.setText(lucky.substring(0,1)+getAlphaNumericString(5));

                break;

        }

        return super.onOptionsItemSelected(item);
    }


    public String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString =
                "123456789";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
