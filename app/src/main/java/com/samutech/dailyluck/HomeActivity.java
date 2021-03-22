package com.samutech.dailyluck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.samutech.dailyluck.fragment.Home;
import com.samutech.dailyluck.fragment.Referral;
import com.samutech.dailyluck.fragment.Results;
import com.samutech.dailyluck.fragment.Tickets;
import com.samutech.dailyluck.fragment.Winners;

public class HomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loadFragment(new Home());


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:

                        loadFragment(new Home());

                        break;
                    case R.id.tickets:

                        loadFragment(new Tickets());

                        break;
                    case R.id.result:

                        loadFragment(new Results());

                        break;

                    case R.id.cup:

                        loadFragment(new Winners());

                        break;

                    case R.id.refers:

                        loadFragment(new Referral());

                        break;
                }
                return true;
            }
        });
    }


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
