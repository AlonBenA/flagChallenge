package com.example.alon.secapp;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alon on 26/02/2015.
 */
public class QuestionPullParser {
    //all the the tag
    private static final String QuestionId = "QuestionId";
    private static final String ImageSrc = "ImageSrc";
    private static final String realAnswer = "realAnswer";
    private static final String Option1 = "Option1";
    private static final String Option2 = "Option2";
    private static final String Option3 = "Option3";
    private static final String Option4 = "Option4";

    private Question CurrentQuestion = null;
    private String CurrentTag = null;
    List<Question> Questions = new ArrayList<Question>();

    public List<Question> parserXml(Context context) {
        try {
            //get to the data xnl file
            XmlPullParserFactory Factory = XmlPullParserFactory.newInstance();
            Factory.setNamespaceAware(true);
            XmlPullParser xpp = Factory.newPullParser();

            InputStream stream = context.getResources().openRawResource(R.raw.data);
            xpp.setInput(stream,null);
            //Check what type of event and Continues until the end of the file
            int eventType = xpp.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT)
            {
                //if it is a START_TAG so it will create new Question
                if(eventType == XmlPullParser.START_TAG){
                    handleStartTag(xpp.getName());
                }
                else if(eventType == XmlPullParser.END_TAG){
                    //if it is end so the tag is null
                    CurrentTag = null;
                }
                else if(eventType == XmlPullParser.TEXT){
                    //if it is the text part it send to handleText and save to the right place
                    handleText(xpp.getText());
                }
                //go to the next event
                eventType = xpp.next();
            }


        } catch (Exception e) {

        }


        return Questions;
    }

    private void handleText(String text)
    {
        String xmlText = text;
        if(CurrentQuestion != null && CurrentTag != null)
        {
          if(CurrentTag.equalsIgnoreCase(QuestionId))
          {
              Integer id = Integer.parseInt(xmlText);
          }
          else if(CurrentTag.equalsIgnoreCase(ImageSrc))
          {
              Integer ImageSrc = Integer.parseInt(xmlText);
              CurrentQuestion.setImageSrc(ImageSrc);
          }
          else if(CurrentTag.equalsIgnoreCase(realAnswer))
          {
              CurrentQuestion.setRealAnswer(xmlText);
          }
          else if(CurrentTag.equalsIgnoreCase(Option1))
          {
              CurrentQuestion.setAnswer1(xmlText);
          }
          else if(CurrentTag.equalsIgnoreCase(Option2))
          {
              CurrentQuestion.setAnswer2(xmlText);
          }
          else if(CurrentTag.equalsIgnoreCase(Option3))
          {
              CurrentQuestion.setAnswer3(xmlText);
          }
          else if(CurrentTag.equalsIgnoreCase(Option4))
          {
              CurrentQuestion.setAnswer4(xmlText);
          }

        }
    } //end of handleText

    private void handleStartTag(String name)
    {
      if(name.equalsIgnoreCase("Question"))
      {
          CurrentQuestion = new Question();
          Questions.add(CurrentQuestion);
      }
      else
      {
          CurrentTag = name;
      }


    }

}
