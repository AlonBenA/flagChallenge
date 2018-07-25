package com.example.alon.secapp;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Alon on 15/02/2015.
 */
public class Question {
    private int ImageSrc;
    private String realAnswer;
    private String Answer1;
    private String Answer2;
    private String Answer3;
    private String Answer4;

    public Question(int ImageSrc,String realAnswer,String Answer1,String Answer2,String Answer3,String Answer4) {
        this.ImageSrc = ImageSrc;
        this.realAnswer = realAnswer;
        this.Answer1 = Answer1;
        this.Answer2 = Answer2;
        this.Answer3 = Answer3;
        this.Answer4 = Answer4;
    }

    public Question()
    {

    }

    public String getRealAnswer() {
        return realAnswer;
    }

    public void setRealAnswer(String realAnswer) {
        this.realAnswer = realAnswer;
    }

    public String getAnswer1() {
        return Answer1;
    }

    public void setAnswer1(String answer1) {
        this.Answer1 = answer1;
    }

    public String getAnswer2() {
        return Answer2;
    }

    public void setAnswer2(String answer2) {
        this.Answer2 = answer2;
    }

    public String getAnswer3() {
        return Answer3;
    }

    public void setAnswer3(String answer3) {
        this.Answer3 = answer3;
    }

    public String getAnswer4() {
        return Answer4;
    }

    public void setAnswer4(String answer4) {
        this.Answer4 = answer4;
    }

    public int getImageSrc() {
        return ImageSrc;
    }

    public void setImageSrc(int ImageSrc){ this.ImageSrc=ImageSrc;}

    public boolean createFile()
    {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("allQuestion");
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();

        }


        return false;
    }



}
