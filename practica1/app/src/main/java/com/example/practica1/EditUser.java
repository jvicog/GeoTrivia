package com.example.practica1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditUser extends AppCompatActivity {

    public UserRoomDatabase db;
    public UserDao mUserDao;

    private TextView nameText;
    private User user;

    private ImageView userImage;
    private ImageButton ib1;
    private ImageButton ib2;
    private ImageButton ib3;
    private ImageButton ib4;
    private int userImageID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        db = UserRoomDatabase.getDatabase(getApplicationContext());
        mUserDao = db.userDao();
        userImage = (ImageView) findViewById(R.id.userImage);
        ib1 = (ImageButton) findViewById(R.id.imageButton1);
        ib2 = (ImageButton) findViewById(R.id.imageButton2);
        ib3 = (ImageButton) findViewById(R.id.imageButton3);
        ib4 = (ImageButton) findViewById(R.id.imageButton4);
        String targetUsername = getIntent().getStringExtra("username");
        user = mUserDao.getSpecificUser(targetUsername);
        userImageID = user.getAvatar();
        nameText = (TextView) findViewById(R.id.nameText);
        nameText.setText(targetUsername.toString());
    }

    public void editUser(View view) {
        mUserDao.changeImage(userImageID, user.getUsername());
        goBack(view);
    }

    public void goBack(View view){
        Intent next = new Intent(this, ChangeUser.class);
        startActivity(next);
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