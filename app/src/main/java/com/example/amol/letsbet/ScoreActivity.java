package com.example.amol.letsbet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class ScoreActivity extends AppCompatActivity implements View.OnClickListener {
    private Boolean isFabOpen = false;

    private FloatingActionMenu menuRed;
    private FloatingActionButton fabBet;
    private FloatingActionButton fabComment;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    private ViewPager viewpager;
    private PagerTitleStrip titleStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        menuRed = (FloatingActionMenu) findViewById(R.id.menu);
        fabBet = (FloatingActionButton) findViewById(R.id.fabBet);
        fabComment = (FloatingActionButton) findViewById(R.id.fabComment);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
        fabBet.setOnClickListener(this);
        fabComment.setOnClickListener(this);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new DummyFragment(), "Match stats");
        viewPagerAdapter.addFragment(new DummyFragment(), "Team line ups");
        viewPagerAdapter.addFragment(new DummyFragment(), "Comments");
        menuRed.setClosedOnTouchOutside(true);
        viewpager.setAdapter(viewPagerAdapter);

        titleStrip = (PagerTitleStrip) findViewById(R.id.title);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
//            case R.id.fab:
//                animateFAB();
//
//                break;
            case R.id.fabBet:
//                animateFAB();
                menuRed.close(true);
                new PredictorDialog(this, PredictorDialog.PredictionType.TYPE_MOM).show();
               // startActivity(new Intent(getApplicationContext(), PredictorActivity.class));
                break;
            case R.id.fabComment:
                menuRed.close(true);
                startActivity(new Intent(getApplicationContext(), ChatActivity.class));

//                animateFAB();
                break;
        }
    }

    /*public void animateFAB() {

        if (isFabOpen) {

            fabBet.startAnimation(fab_close);
            fabComment.startAnimation(fab_close);
            fabBet.setClickable(false);
            fabComment.setClickable(false);
            isFabOpen = false;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    fab.startAnimation(rotate_forward);
                }
            }, 100);
        } else {

//            fab.startAnimation(rotate_forward);
            fabBet.startAnimation(fab_open);
            fabComment.startAnimation(fab_open);
            fabBet.setClickable(true);
            fabComment.setClickable(true);
            isFabOpen = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    fab.startAnimation(rotate_forward);
                }
            }, 100);
        }
    }*/
}
