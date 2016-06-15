package com.example.amol.letsbet;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PredictorActivity extends AppCompatActivity {

    ExpandableListView expandableListView;

    ExpandableListAdapter listAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predictor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        expandableListView= (ExpandableListView) findViewById(R.id.expandableListView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expandableListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Match Winner");
        listDataHeader.add("Man of the match");
        listDataHeader.add("Top Scorer");

        // Adding child data
        List<String> winnnerTeam = new ArrayList<String>();
        winnnerTeam.add("The Shawshank Redemption");
        winnnerTeam.add("The Godfather");


        List<String> manOfMatch = new ArrayList<String>();
        manOfMatch.add("The Conjuring");
        manOfMatch.add("Despicable Me 2");

        List<String> TopScorer = new ArrayList<String>();
        TopScorer.add("2 Guns");
        TopScorer.add("The Smurfs 2");
        TopScorer.add("The Spectacular Now");
        TopScorer.add("The Canyons");
        TopScorer.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), winnnerTeam); // Header, Child data
        listDataChild.put(listDataHeader.get(1), manOfMatch);
        listDataChild.put(listDataHeader.get(2), TopScorer);
    }
}