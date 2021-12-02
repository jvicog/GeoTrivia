package com.example.practica1;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "question_table")
public class Question {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "ID")
    public int mID;

    @NonNull
    @ColumnInfo(name = "Type")
    public int mType;

    public Question(int mID, int mType){
        this.mID = mID;
        this.mType = mType;
    }

    public int getmID(){return this.mID;}

    public void setmID(int ID){this.mID = ID;}

    @ColumnInfo(name = "Quest")
    public String mQuest;

    @ColumnInfo(name = "Answer1")
    public String mAnswer1;
    @ColumnInfo(name = "Answer2")
    public String mAnswer2;
    @ColumnInfo(name = "Answer3")
    public String mAnswer3;
    @ColumnInfo(name = "Answer4")
    public String mAnswer4;

    @ColumnInfo(name = "Image1")
    public String mImage1;
    @ColumnInfo(name = "Image2")
    public String mImage2;
    @ColumnInfo(name = "Image3")
    public String mImage3;
    @ColumnInfo(name = "Image4")
    public String mImage4;

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }

    public String getmQuest() {
        return mQuest;
    }

    public void setmQuest(String mQuest) {
        this.mQuest = mQuest;
    }

    public String getmAnswer1() {
        return mAnswer1;
    }

    public void setmAnswer1(String mAnswer1) {
        this.mAnswer1 = mAnswer1;
    }

    public String getmAnswer2() {
        return mAnswer2;
    }

    public void setmAnswer2(String mAnswer2) {
        this.mAnswer2 = mAnswer2;
    }

    public String getmAnswer3() {
        return mAnswer3;
    }

    public void setmAnswer3(String mAnswer3) {
        this.mAnswer3 = mAnswer3;
    }

    public String getmAnswer4() {
        return mAnswer4;
    }

    public void setmAnswer4(String mAnswer4) {
        this.mAnswer4 = mAnswer4;
    }

    public String getmImage1() {
        return mImage1;
    }

    public void setmImage1(String mImage1) {
        this.mImage1 = mImage1;
    }

    public String getmImage2() {
        return mImage2;
    }

    public void setmImage2(String mImage2) {
        this.mImage2 = mImage2;
    }

    public String getmImage3() {
        return mImage3;
    }

    public void setmImage3(String mImage3) {
        this.mImage3 = mImage3;
    }

    public String getmImage4() {
        return mImage4;
    }

    public void setmImage4(String mImage4) {
        this.mImage4 = mImage4;
    }
}

