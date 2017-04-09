package cz.activity;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;

import com.example.chenzhang.myapplication.R;


import cz.fragment.ClassTableFragment;
import cz.fragment.HomeFragment;
import cz.fragment.MineFragment;


public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private MineFragment mfragment;
    private HomeFragment hfragment;
    private ClassTableFragment cfragment;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            FragmentManager fm = getSupportFragmentManager();
            // 开启Fragment事务
            FragmentTransaction transaction = fm.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    hfragment = new HomeFragment();
                    transaction.replace(R.id.content, hfragment);
                    transaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    cfragment = new ClassTableFragment();
                    transaction.replace(R.id.content, cfragment);
                    transaction.commit();
                    return true;
                case R.id.navigation_notifications:

                    mfragment = new MineFragment();
                    transaction.replace(R.id.content, mfragment);
                    transaction.commit();
                    return true;

            }

            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        setDefaulatFragmennt();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }


    public void setDefaulatFragmennt() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hfragment = new HomeFragment();
        transaction.replace(R.id.content, hfragment);
        transaction.commit();

    }


}
