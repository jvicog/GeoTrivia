package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Menu extends AppCompatActivity {

    private int[] scores = {0, 0, 0, 0};                //Stores the scores of the 4 quests.
    private ImageView[][] stars = new ImageView[4][3];  //Stores the images of the 12 stars.
    private int Nquestions = 10;                        //Number of questions.
    private int difficulty = 0;                         //Number of difficulty. 0 Easy 1 Hard.

    public UserRoomDatabase db;
    public UserDao mUserDao;

    private ImageView userImage;
    private TextView userName;

    private User activeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Initialize view objects.
        stars[0][0] = (ImageView) findViewById(R.id.star00);
        stars[0][1] = (ImageView) findViewById(R.id.star01);
        stars[0][2] = (ImageView) findViewById(R.id.star02);
        stars[1][0] = (ImageView) findViewById(R.id.star10);
        stars[1][1] = (ImageView) findViewById(R.id.star11);
        stars[1][2] = (ImageView) findViewById(R.id.star12);
        stars[2][0] = (ImageView) findViewById(R.id.star20);
        stars[2][1] = (ImageView) findViewById(R.id.star21);
        stars[2][2] = (ImageView) findViewById(R.id.star22);
        stars[3][0] = (ImageView) findViewById(R.id.star30);
        stars[3][1] = (ImageView) findViewById(R.id.star31);
        stars[3][2] = (ImageView) findViewById(R.id.star32);

        updateStars();                  //Initialize the starts.

        db = UserRoomDatabase.getDatabase(getApplicationContext());
        mUserDao = db.userDao();

        userImage = (ImageView) findViewById(R.id.userImageMenu);
        userName = (TextView) findViewById(R.id.userNameMenu);
    }

    public void firstQuest(View view){
        if(mUserDao.getActiveUser() == null){
            Toast.makeText(this, "Debes elegir un usuario", Toast.LENGTH_SHORT).show();
        }else {
        Intent next = new Intent(this, FirstQuest.class);
        sendScores(next);                              //Send the previous 4 results.
        sendNumberOfQuestions(next);                   //Send the number of questions.
        sendDifficulty(next);				   		   //Send the number of difficulty.
        startActivity(next);                           //Switch to the next activity.
        }
    }


    public void secondQuest(View view){
        if(mUserDao.getActiveUser() == null){
            Toast.makeText(this, "Debes elegir un usuario", Toast.LENGTH_SHORT).show();
        }else {
            Intent next = new Intent(this, SecondQuest.class);
            sendScores(next);                              //Send the previous 4 results.
            sendNumberOfQuestions(next);                   //Send the number of questions.
            sendDifficulty(next);				   		   //Send the number of difficulty.
            startActivity(next);                           //Switch to the next activity.
        }
    }

    public void thirdQuest(View view){
        if(mUserDao.getActiveUser() == null){
            Toast.makeText(this, "Debes elegir un usuario", Toast.LENGTH_SHORT).show();
        }else {
        Intent next = new Intent(this, ThirdQuest.class);
        sendScores(next);                              //Send the previous 4 results.
        sendNumberOfQuestions(next);                   //Send the number of questions.
        sendDifficulty(next);				   		   //Send the number of difficulty.
        startActivity(next);                           //Switch to the next activity.
        }
    }

    public void fourthQuest(View view){
            if(mUserDao.getActiveUser() == null){
                Toast.makeText(this, "Debes elegir un usuario", Toast.LENGTH_SHORT).show();
            }else {
        Intent next = new Intent(this, FourthQuest.class);
        sendScores(next);                              //Send the previous 4 results.
        sendNumberOfQuestions(next);                   //Send the number of questions.
        sendDifficulty(next);				   		   //Send the number of difficulty.
        startActivity(next);                           //Switch to the next activity.
        }
    }

    public void settings(View view){
        Intent next = new Intent(this, Settings.class);
        sendScores(next);                              //Send the previous 4 results.
        sendNumberOfQuestions(next);                   //Send the number of questions.
        sendDifficulty(next);				   		   //Send the number of difficulty.
        startActivity(next);                           //Switch to the next activity.
    }

    public void goChangeUser(View view){

        Intent next = new Intent(this, ChangeUser.class);
        startActivity(next);
    }


    public void updateStars(){             //Update all stars.
        setStars(1, scoreToStars(scores[0]));
        setStars(2, scoreToStars(scores[1]));
        setStars(3, scoreToStars(scores[2]));
        setStars(4, scoreToStars(scores[3]));
    }

    public int scoreToStars(int sc){        //Indicate how many stars correspond to a score.
        if(sc == 100){              //With score 100, 3 stars.
            return 3;
        }else if (sc >=80){         //With score 80 or more, 2 stars.
            return 2;
        }else if (sc >=50){         //With score 50 or more, 1 star.
            return 1;
        }else{                      //With score less than 50, suspense, 0 stars.
            return 0;
        }
    }

    public void setStars(int quest, int st){        //Change the stars of a specific quest.
        switch(st){
            case 0:
                stars[quest-1][0].setImageResource(android.R.drawable.btn_star_big_off);
                stars[quest-1][1].setImageResource(android.R.drawable.btn_star_big_off);
                stars[quest-1][2].setImageResource(android.R.drawable.btn_star_big_off);
                break;
            case 1:
                stars[quest-1][0].setImageResource(android.R.drawable.btn_star_big_on);
                stars[quest-1][1].setImageResource(android.R.drawable.btn_star_big_off);
                stars[quest-1][2].setImageResource(android.R.drawable.btn_star_big_off);
                break;
            case 2:
                stars[quest-1][0].setImageResource(android.R.drawable.btn_star_big_on);
                stars[quest-1][1].setImageResource(android.R.drawable.btn_star_big_on);
                stars[quest-1][2].setImageResource(android.R.drawable.btn_star_big_off);
                break;
            case 3:
                stars[quest-1][0].setImageResource(android.R.drawable.btn_star_big_on);
                stars[quest-1][1].setImageResource(android.R.drawable.btn_star_big_on);
                stars[quest-1][2].setImageResource(android.R.drawable.btn_star_big_on);
                break;
            default:
                return;
        }
    }

    @Override
    protected void onResume(){          //If you enter this activity and it is not the first time.
        super.onResume();
        receiveScores();                //Collect all the results so far.
        updateStars();                  //Change the starts.
        receiveNumberOfQuestions();		//Receive the number of questions from the previous activity.
        receiveDifficulty();			//Receive the number of difficulty.
        if(mUserDao.getActiveUser() != null){
            activeUser = mUserDao.getActiveUser();
            userImage.setImageResource(activeUser.getAvatar());
            userName.setText(activeUser.getUsername());
        }
    }

    /*
        Collect all the results so far, in case any
        result is not yet there because it has not
        been played, it will automatically be set to
        0 by default.
     */
    public void receiveScores(){
        scores[0] = getIntent().getIntExtra("score0", 0);
        scores[1] = getIntent().getIntExtra("score1", 0);
        scores[2] = getIntent().getIntExtra("score2", 0);
        scores[3] = getIntent().getIntExtra("score3", 0);
    }

    /*
        Send all old results to the next activity.
     */
    public void sendScores(Intent activity){
        activity.putExtra("score0", scores[0]);
        activity.putExtra("score1", scores[1]);
        activity.putExtra("score2", scores[2]);
        activity.putExtra("score3", scores[3]);
    }

    //Receive the number of questions from the previous activity.
    public void receiveNumberOfQuestions(){
        Nquestions = getIntent().getIntExtra("Nquestions", 10);
    }

    //Send the number of questions to the next activity.
    public void sendNumberOfQuestions(Intent activity){
        activity.putExtra("Nquestions", Nquestions);
    }

    //Receive the number of difficulty from the previous activity.
    public void receiveDifficulty(){
        difficulty = getIntent().getIntExtra("difficulty", 0);
    }

    //Send the number of difficulty to the next activity.
    public void sendDifficulty(Intent activity){
        activity.putExtra("difficulty", difficulty);
    }

}