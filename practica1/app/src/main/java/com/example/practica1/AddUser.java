package com.example.practica1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class AddUser extends AppCompatActivity {

    public UserRoomDatabase db;
    public UserDao mUserDao;

    private EditText input;
    private TextView errorText;

    private ImageView userImage;
    private ImageButton ib1;
    private ImageButton ib2;
    private ImageButton ib3;
    private ImageButton ib4;
    private int userImageID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        db = UserRoomDatabase.getDatabase(getApplicationContext());
        mUserDao = db.userDao();
        input = (EditText) findViewById(R.id.inputText);
        errorText = (TextView) findViewById(R.id.errorText);
        userImage = (ImageView) findViewById(R.id.userImage);
        ib1 = (ImageButton) findViewById(R.id.imageButton1);
        ib2 = (ImageButton) findViewById(R.id.imageButton2);
        ib3 = (ImageButton) findViewById(R.id.imageButton3);
        ib4 = (ImageButton) findViewById(R.id.imageButton4);
        userImageID = R.mipmap.profile_pic_3;
    }

    public void addUser(View view){
        String username = input.getText().toString();
        if(isEmpty(username)){
            showFeedback("Debes introducir un nombre", 1);
        }else if(isUnique(username)){
            //Toast.makeText(this, mUserDao.getAllUsernames().toString(), Toast.LENGTH_SHORT).show();
            User temp = new User(username);
            temp.setAvatar(userImageID);
            if(mUserDao.getActiveUser() != null){
                //mUserDao.getActiveUser().setActive(false);
                mUserDao.setAllInactive(false);
            }
            temp.setActive(true);
            mUserDao.insert(temp);
            input.setText("");
            showFeedback("Usuario añadido", 2);
        }else{
            showFeedback("El usuario " + username + " ya existe", 1);
        }
    }

    public void goBack(View view){
        Intent next = new Intent(this, ChangeUser.class);
        startActivity(next);
    }

    public boolean isUnique(String username){
        return !mUserDao.getAllUsernames().contains(username);
    }

    public boolean isEmpty(String username){
        if(username.compareTo("") == 0){
            return true;
        }else return false;
    }

    public void showFeedback(String message, int color){ //COLOR 1: RED; COLOR 2: GREEN
        switch(color){
            case 1:
                errorText.setTextColor(Color.RED);
                break;
            case 2:
                errorText.setTextColor(Color.GREEN);
                break;
            default:
                errorText.setTextColor(Color.BLACK);
                break;
        }
        errorText.setText(message);
    }

    public void chooseImage1(View view){
        userImage.setImageResource(R.mipmap.profile_pic_1);
        userImageID = R.mipmap.profile_pic_1;
    }

    public void chooseImage2(View view){
        userImage.setImageResource(R.mipmap.profile_pic_2);
        userImageID = R.mipmap.profile_pic_2;
    }

    public void chooseImage3(View view){
        userImage.setImageResource(R.mipmap.profile_pic_3);
        userImageID = R.mipmap.profile_pic_3;
    }

    public void loadImage(View view){

        Toast.makeText(this, "NOT IMPLEMENTED YET", Toast.LENGTH_SHORT).show();
    }

}