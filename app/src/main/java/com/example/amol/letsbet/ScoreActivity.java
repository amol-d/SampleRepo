package com.example.amol.letsbet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class ScoreActivity extends AppCompatActivity implements View.OnClickListener {
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    private ViewPager viewpager;
    private PagerTitleStrip titleStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new DummyFragment(), "Match stats");
        viewPagerAdapter.addFragment(new DummyFragment(), "Team line ups");
        viewPagerAdapter.addFragment(new DummyFragment(), "Comments");

        viewpager.setAdapter(viewPagerAdapter);

        titleStrip = (PagerTitleStrip) findViewById(R.id.title);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab:
                animateFAB();

                break;
            case R.id.fab1:
                animateFAB();
                startActivity(new Intent(getApplicationContext(), PredictorActivity.class));
                break;
            case R.id.fab2:

                animateFAB();
                break;
        }
    }

    public void animateFAB() {

        if (isFabOpen) {

            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    fab.startAnimation(rotate_forward);
                }
            }, 100);
        } else {

//            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    fab.startAnimation(rotate_forward);
                }
            }, 100);
        }
    }
}
