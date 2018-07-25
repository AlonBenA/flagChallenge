package com.example.alon.secapp;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.*;
import android.widget.*;
import android.content.Intent;


public class MainActivity extends ActionBarActivity {
    int min=0;
    int max=203;
    static int num=0;
    static String Difficulty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView theText = (TextView) findViewById(R.id.textView4);
        theText.setText("max= " + max + " min=  " + min);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void CheckTheBoxs()
    {
        //the user choose the level of Difficulty and it set the min and the max so it know what from what Range to Take from
        CheckBox easy = (CheckBox) findViewById(R.id.easyCheckBox);
        CheckBox mid = (CheckBox) findViewById(R.id.mediumCheckBox);
        CheckBox hard = (CheckBox) findViewById(R.id.hardCheckBox);



        if(easy.isChecked() && mid.isChecked() && hard.isChecked())
        {
            min=0;
            max=203;
            Difficulty = "hard";
        }
        else if(easy.isChecked() && mid.isChecked())
        {
            min=0;
            max=128;
            Difficulty = "mid";
        }
        else if(easy.isChecked() && hard.isChecked())
        {
            min=0;
            max=203;
            Difficulty = "hard";
        }
        else if(mid.isChecked() && hard.isChecked())
        {
            min=64;
            max=129;
            Difficulty = "hard";
        }
        else if(easy.isChecked())
        {
            min=0;
            max=64;
            Difficulty = "easy";
        }
        else if(mid.isChecked())
        {
            min=64;
            max=64;
            Difficulty = "mid";
        }
        else if(hard.isChecked())
        {
            min=128;
            max=65;
            Difficulty = "hard";
        }
    }


    public void StartGame(View view) {
        Intent toStart = new Intent(this,QuestionActivity.class);
    //set the max and min
        CheckTheBoxs();
    //send the min and max
        toStart.putExtra("min",min);
        toStart.putExtra("max",max);
        toStart.putExtra("Difficulty",Difficulty);
        num++;
        startActivity(toStart);
    }
}
