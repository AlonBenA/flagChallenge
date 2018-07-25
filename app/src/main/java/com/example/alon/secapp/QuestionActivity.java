package com.example.alon.secapp;

import android.app.Activity;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.view.*;
import android.widget.*;
import android.content.Intent;

import java.util.List;
import java.util.Random;


/**
 * Created by Alon on 15/02/2015.
 */
public class QuestionActivity extends Activity {
    static Question[] QuestionData;
    static int index=0,rightAnswers=0,min=0,max=0,numOfQuestion=10;
    static String Difficulty = "";
    static Button Answer1;
    static Button Answer2;
    static Button Answer3;
    static Button Answer4;
    static TextView Score;
    static TextView timer;
    static ImageView theImage;
    static List<Question> DataXml;
    static CountDownTimer theTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_layout);
        //get max and min
        getMinAndMax();
        //set the array that will hold the Question
        QuestionData = new Question[11];
        //set all the Questions in the array
        setArrayOfQuestions();

       //Sets the Button and Score and Image;
        Answer1 = (Button) findViewById(R.id.Answer1);
        Answer2 = (Button) findViewById(R.id.Answer2);
        Answer3 = (Button) findViewById(R.id.Answer3);
        Answer4 = (Button) findViewById(R.id.Answer4);
        Score = (TextView) findViewById(R.id.Score);
        timer = (TextView) findViewById(R.id.timer);
        theImage= (ImageView) findViewById(R.id.theFlag);


        rightAnswers=0;
        index=0;
        Score.setText("Score = " + rightAnswers + " index = " + index);

        setQuestion();

    }

    public void getMinAndMax()
    {
    //get the max and the min from the other activity
    Intent fromStart = getIntent();
    min = fromStart.getExtras().getInt("min");
    max = fromStart.getExtras().getInt("max");
   Difficulty = fromStart.getExtras().getString("Difficulty");
    }

    public void setArrayOfQuestions()
    {
    //Get all the questions from data XNL file
    QuestionPullParser Parser = new QuestionPullParser();
    DataXml = Parser.parserXml(this);
    int i,theFlag=0,number=0;
    String[] dadaInfo = new String[5];
     Random rnd = new Random();


        //set all Random Questions in the array
        for(i=0;i<10;i++) {
            number= (rnd.nextInt(max)+min);
            theFlag = DataXml.get(number).getImageSrc();
            dadaInfo[0] = DataXml.get(number).getRealAnswer();
            dadaInfo[1] = DataXml.get(number).getAnswer1();
            dadaInfo[2] = DataXml.get(number).getAnswer2();
            dadaInfo[3] = DataXml.get(number).getAnswer3();
            dadaInfo[4] = DataXml.get(number).getAnswer4();

            QuestionData[i] = new Question(theFlag,dadaInfo[0],dadaInfo[1],dadaInfo[2],dadaInfo[3],dadaInfo[4]);
        }




        QuestionData[10] = new Question(0,"","","","","");

    }


    public void playerChoose1(View view) {
        //cancel the timer and Check if the Answer is Correct
        theTimer.cancel();
        if(Answer1.getText().toString().equalsIgnoreCase(QuestionData[index].getRealAnswer()) )
        {
            //if it is true it Increases the number of right answer and go to the next Question
            rightAnswers++;
            Toast.makeText(QuestionActivity.this,"you are correct", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(QuestionActivity.this,"you are wrong", Toast.LENGTH_SHORT).show();
        }

        Score.setText("Score = " + rightAnswers + " index = " + index);
        //so it will be load the next Question
        index++;
        CheckingEnd();
        setQuestion();
    }

    public void playerChoose2(View view) {
        //cancel the timer and Check if the Answer is Correct
        theTimer.cancel();
        if(Answer2.getText().toString().equalsIgnoreCase(QuestionData[index].getRealAnswer().toLowerCase()))
        {
            //if it is true it Increases the number of right answer and go to the next Question
            rightAnswers++;
            Toast.makeText(QuestionActivity.this,"you are correct", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(QuestionActivity.this,"you are wrong", Toast.LENGTH_SHORT).show();
        }


        Score.setText("Score = " + rightAnswers + " index = " + index);
        //so it will be load the next Question
        index++;
        CheckingEnd();
        setQuestion();
    }

    public void playerChoose3(View view) {
        //cancel the timer and Check if the Answer is Correct
        theTimer.cancel();
        if(Answer3.getText().toString().equalsIgnoreCase(QuestionData[index].getRealAnswer().toLowerCase()))
        {
            //if it is true it Increases the number of right answer and go to the next Question
            rightAnswers++;
            Toast.makeText(QuestionActivity.this,"you are correct", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(QuestionActivity.this,"you are wrong", Toast.LENGTH_SHORT).show();
        }

        Score.setText("Score = " + rightAnswers + " index = " + index);
        //so it will be load the next Question
        index++;

        CheckingEnd();
        setQuestion();
    }

    public void playerChoose4(View view) {
        //cancel the timer and Check if the Answer is Correct
        theTimer.cancel();
        if(Answer4.getText().toString().equalsIgnoreCase(QuestionData[index].getRealAnswer().toLowerCase()))
        {
            //if it is true it Increases the number of right answer and go to the next Question
            rightAnswers++;
            Toast.makeText(QuestionActivity.this,"you are correct", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(QuestionActivity.this,"you are wrong", Toast.LENGTH_SHORT).show();
        }


        Score.setText("Score = " + rightAnswers + " index = " + index);
        //so it will be load the next Question
        index++;
        CheckingEnd();
        setQuestion();
    }


    public void setQuestion()
    {

        //did we reached the end?
        CheckingEnd();

        //set the Image to the next one
        theImage.setImageResource(QuestionData[index].getImageSrc());
        //update what is the right Answer Depending on the Question


        // get the 4 Answer
        String[] allQ = new String[4];
        allQ[0] = QuestionData[index].getAnswer1();
        allQ[1] = QuestionData[index].getAnswer2();
        allQ[2] = QuestionData[index].getAnswer3();
        allQ[3] = QuestionData[index].getAnswer4();
        //make the Answer to be Random
        setOptionsRandom(allQ);

        setTimer();

    } //end of setQuestion

    public void setOptionsRandom(String[] allQ)
    {
        //make the Answer to be Random
        int i;
        boolean[] flags = new boolean[4];
        boolean flag = true;
        Random ran = new Random();


        for(i=0;i<4;i++)
        {
            flags[i]=true;
        }

        while(flag==true) {
            i=ran.nextInt(4);
            Answer1.setText(allQ[i]);
            flags[i]=false;
            flag=false;
        }

        flag = true;

        while(flag==true) {
            i=ran.nextInt(4);
            if(flags[i]==true) {
                Answer2.setText(allQ[i]);
                flags[i] = false;
                flag = false;
            }

        }

        flag = true;

        while(flag==true) {
            i=ran.nextInt(4);
            if(flags[i]==true) {
                Answer3.setText(allQ[i]);
                flags[i] = false;
                flag = false;
            }

        }

        for (i=0;i<4;i++) {
            if(flags[i]==true) {
                Answer4.setText(allQ[i]);
            }
        }
    }




    public void CheckingEnd()
    {
        //Check if we reached the end of the quiz of we do it return to the start
        if(index==10)
        {
            Intent backToStart = new Intent(this,endGameActivity.class);
            backToStart.putExtra("numberOfQuestion",10);
            backToStart.putExtra("SuccessfullyAnswered",rightAnswers);
            backToStart.putExtra("FailToAnswered",10-rightAnswers);
            startActivity(backToStart);
        }
    }


    public void setTimer()
    {

        if(Difficulty.equals("easy")) {
            theTimer = new CountDownTimer(16000, 1000) {

                public void onTick(long millisUntilFinished) {
                    timer.setText("timer: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    timer.setText("done!");
                }
            }.start();
        }
        else if(Difficulty.equals("mid")) {
            theTimer = new CountDownTimer(11000, 1000) {

                public void onTick(long millisUntilFinished) {
                    timer.setText("timer: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    timer.setText("done!");
                }
            }.start();
        }
        else if(Difficulty.equals("hard")) {
            theTimer = new CountDownTimer(6000, 1000) {

                public void onTick(long millisUntilFinished) {
                    timer.setText("timer: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    timer.setText("done!");
                }
            }.start();
        }
    }
}
