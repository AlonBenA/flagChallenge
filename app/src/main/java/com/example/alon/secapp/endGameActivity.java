package com.example.alon.secapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Alon on 24/02/2015.
 */
public class endGameActivity extends Activity {
    int numberOfQ=0;
    int SQ=0;
    int FQ=0;
    TextView textAllQ;
    TextView textSQ;
    TextView textFQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_game_layout);

        Intent allData = getIntent();
        numberOfQ = allData.getExtras().getInt("numberOfQuestion");
        SQ =  allData.getExtras().getInt("SuccessfullyAnswered");
        FQ = allData.getExtras().getInt("FailToAnswered");
        textAllQ = (TextView) findViewById(R.id.theNumberOfQuestion);
        textSQ = (TextView) findViewById(R.id.SuccessfullyAnswered);
        textFQ = (TextView) findViewById(R.id.FailToAnswered);

        textAllQ.setText("number of Question : " + numberOfQ);
        textSQ.setText("number of Question you Successfully answered : "+ SQ);
        textFQ.setText("number of Question you Fails to answered : " + FQ);


    }

    public void returnToStart(View view) {
        Intent toReturn = new Intent(this,MainActivity.class);
        startActivity(toReturn);
    }
}
