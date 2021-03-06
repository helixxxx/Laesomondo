package com.example.cille_000.laesomondo.entities;


import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadingTest {

    private String text = "";
    private List<String> listInfo;
    private int textID;
    private Context context;
    private String category;

    public ReadingTest(Context context){
        this.context = context;
    }

    public void setTextID(int id, String categoryID){
        textID = id;
        this.category = categoryID;
        String info;
        try {
            InputStream is = context.getAssets().open("text" + textID + category +  ".txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        }catch (IOException e){
            e.printStackTrace();
        }
        try {
            InputStream is = context.getAssets().open("text" + textID + category + "Info.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            info = new String(buffer);
            listInfo = new ArrayList<>(Arrays.asList(info.split(" / ")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getText() {
        return text;
    }

    public String getWriter(){ return listInfo.get(1);}

    public String getName(){ return  listInfo.get(0);}

    public String getInfo() { return listInfo.get(2);}

    public int getLix(){ return Integer.valueOf(listInfo.get(3));}

    public int getWordCount (){return Integer.valueOf(listInfo.get(4));}

    public List<String> getQuestion1(){return listInfo.subList(5,10);}
    public List<String> getQuestion2(){return listInfo.subList(10,15);}
    public List<String> getQuestion3(){return listInfo.subList(15,20);}

    public int getCorrectAnswer(int question){
        if (question == 1){
        return Integer.valueOf(listInfo.get(20));
        }
        else if (question == 2){
            return Integer.valueOf(listInfo.get(21));
        }
        else {
            return Integer.valueOf(listInfo.get(22));
        }

    }



    }
