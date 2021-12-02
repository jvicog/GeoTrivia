package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

public class Result extends AppCompatActivity {

    //Create view objects.
    private ImageView[] stars = new ImageView[3];
    private TextView scoreText;

    //Create and initialize (some) variables.
    private int score;                              //Create score.
    private int lastQuest;                          //Create who sent it.
    private int[] scores = {0, 0, 0, 0};            //Stores the scores of the 4 quests.
    private int Nquestions;							//Number of questions.
    private int difficulty;                         //Number of difficulty. 0 Easy 1 Hard.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Initialize view objects.
        stars[0] = (ImageView) findViewById(R.id.star0);        //Start 1 on the view.
        stars[1] = (ImageView) findViewById(R.id.star1);        //Start 2 on the view.
        stars[2] = (ImageView) findViewById(R.id.star2);        //Start 3 on the view.
        scoreText = (TextView) findViewById(R.id.resultText);   //Score on the view.
        }

    @Override
    protected void onResume(){          //If you enter this activity and it is not the first time.
        super.onResume();
        score = getIntent().getIntExtra("questScore", 0);   //Receive the new score.
        lastQuest = getIntent().getIntExtra("lastQuest", 1);//Who sent it.
        updateScoreText();              //Show the score on the screen.
        receiveScores();                //Collect all the results so far.
        updateStars();                  //Change the starts.
        receiveNumberOfQuestions();		//Receive the number of questions from the previous activity.
        receiveDifficulty();			//Receive the number of difficulty.
    }

    public void updateScoreText(){              //Show the score on the screen.
        String s = Integer.toString(score);
        scoreText.setText(s);
    }

    public void updateStars(){                  //Update the number of stars depending on the score.
        if(score >= 100){
            setStars(3);
        }else if(score >= 80){
            setStars(2);
        }else if(score >= 50){
            setStars(1);
        }else{
            setStars(0);
        }
    }

    public void setStars(int newStars){         //Show the stars on the screen.
        if (newStars >= 3){
            stars[2].setImageResource(android.R.drawable.btn_star_big_on);
        }else{
            stars[2].setImageResource(android.R.drawable.btn_star_big_off);
        }
        if (newStars >= 2){
            stars[1].setImageResource(android.R.drawable.btn_star_big_on);
        }else{
            stars[1].setImageResource(android.R.drawable.btn_star_big_off);
        }
        if (newStars >= 1){
            stars[0].setImageResource(android.R.drawable.btn_star_big_on);
        }else{
            stars[0].setImageResource(android.R.drawable.btn_star_big_off);
        }
    }

    public void backToMenu(View view){
        Intent next = new Intent(this, Menu.class);     //Back to menu activity
        if(score >= scores[lastQuest-1]){               // ->Update the score if the new one
            scores[lastQuest-1] = score;                //   is higher than the previous one.
        }
        sendScores(next);                              //Send the new 4 results.
        sendNumberOfQuestions(next);				   //Send the number of questions.
        sendDifficulty(next);				   		   //Send the number of difficulty.
        startActivity(next);                           //Switch to the next activity.
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
        Send all new results to the next activity.
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