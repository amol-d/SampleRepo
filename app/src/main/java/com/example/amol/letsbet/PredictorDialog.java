package com.example.amol.letsbet;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by inspeero on 5/5/2015.
 */
public class PredictorDialog extends Dialog {
    private TextView emptyText;
    private ProgressBar progressBar;
    private Button Cancel;
    private Button OK;
    private Activity context;
    private PredictionType predictionType;

    public enum PredictionType {
        TYPE_WINNER, //winner
        TYPE_MOM, //man of the match
        TYPE_RED_CARD // red card
    }

    public PredictorDialog(Activity context, PredictionType predictionType) {
        super(context);
        this.context = context;
        this.predictionType = predictionType;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*emptyText = new TextView(activity);
        progressBar = new ProgressBar(activity);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(2, 10, 2, 10);
        emptyText.setMinimumWidth(activity.getWindow().getDecorView().getWidth() / 2);
        emptyText.setLayoutParams(params);
        emptyText.setGravity(Gravity.CENTER);
        emptyText.setTextSize(18);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        progressBar.setLayoutParams(params);*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.predictor_dialog);


        TextView txtName = (TextView) findViewById(R.id.prediction_title);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        txtName.setGravity(Gravity.CENTER);
        txtName.setTextSize(18);
        params.width = getWindowWidth() - 100;
        txtName.setLayoutParams(params);
        txtName.setText("Predict your result!");


        Cancel = (Button) findViewById(R.id.cancel);
        OK = (Button) findViewById(R.id.ok);

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private int getWindowWidth() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        return width;
    }

    private int getHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        return height;
    }
}
