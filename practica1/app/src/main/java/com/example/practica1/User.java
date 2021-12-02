package com.example.practica1;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "username")
    public String mUsername;

    public User(String mUsername){this.mUsername = mUsername;}

    public String getUsername(){return this.mUsername;}

    public void setUsername(String nUsername){this.mUsername = nUsername;}

    @ColumnInfo(name = "avatar")
    public int mAvatar;

    public int getAvatar(){return this.mAvatar;}

    public void setAvatar(int nAvatar){this.mAvatar = nAvatar;}

    @ColumnInfo(name = "isActive")
    public boolean mIsActive;

    public boolean isActive(){return this.mIsActive;}

    public void setActive(boolean nIsActive){this.mIsActive = nIsActive;}
}

