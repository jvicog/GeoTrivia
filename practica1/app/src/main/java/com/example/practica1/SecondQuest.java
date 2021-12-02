package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Stack;

//BANDERAS
public class SecondQuest extends AppCompatActivity {

    //Create view objects.
    private ImageButton ib1, ib2, ib3, ib4;
    private TextView number, questionText, scoreText, txt_nquestions;

    //Create and initialize (some) variables.
    private int showTimer;                          //Initialize the Int Timer.
    private String timer;                           //Initialize the String Timer.
    private int score = 0;                          //Initialize the score.
    private int questionNum = 0;                    //Initialize the question number.
    private int [] randomNumbers = new int[10];     //Store the random numbers.
    private String[] questions = new String[10];    //Store the questions.
    private int[][] flags = new int[10][4];         //Stores the flags.
    private int[] scores = {0, 0, 0, 0};            //Stores the scores of the 4 quests
    private int Nquestions;							//Number of questions.
    private int difficulty;                         //Number of difficulty. 0 Easy 1 Hard.

    /*
        Stopwatch to control the
        time there is per question.

    */
    CountDownTimer crono = new CountDownTimer(31 * 1000, 1000)
    {
        @Override
        public void onTick(long l)                 //What it will do at each tick.
        {
            showTimer = (int)l/1000;
            timer = String.valueOf(showTimer);
            number.setText(timer);                 //It is updated by screen.
        }
        @Override
        public void onFinish()                     //What will do when it ends.
        {
            endGame();
            questionNum = 0;                          //The answer is sent automatically.
        }
    };


    /*
        When this activity is turned on it will
        do whatever is inside the onCreate method.

    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_quest);

        //Initialize view objects.
        ib1 = (ImageButton) findViewById(R.id.ib1);             //First button on the view.
        ib2 = (ImageButton) findViewById(R.id.ib2);             //Second button on the view.
        ib3 = (ImageButton) findViewById(R.id.ib3);             //Third button on the view.
        ib4 = (ImageButton) findViewById(R.id.ib4);             //Fourth button on the view.
        number = (TextView) findViewById(R.id.crono);        //Countdown on the view.
        questionText = (TextView) findViewById(R.id.txt1);   //Question on the view.
        scoreText = (TextView) findViewById(R.id.txt2);      //Score on the view.
        txt_nquestions = (TextView) findViewById(R.id.txt_nquestions); //Number of question on the view.

        //Initialize questions and answers
        questions[0] = "¿Cuál es la bandera de Francia?";
        flags[0][0] = R.mipmap.flag1a;
        flags[0][1] = R.mipmap.flag1b;
        flags[0][2] = R.mipmap.flag1c;//CORRECTA
        flags[0][3] = R.mipmap.flag1d;

        questions[1] = "¿Cuál es la bandera de Cuba?";
        flags[1][0] = R.mipmap.flag2a;
        flags[1][1] = R.mipmap.flag2b;
        flags[1][2] = R.mipmap.flag2c;//CORRECTA
        flags[1][3] = R.mipmap.flag2d;

        questions[2] = "¿Cuál es la bandera de Finlandia?";
        flags[2][0] = R.mipmap.flag3a;//CORRECTA
        flags[2][1] = R.mipmap.flag3b;
        flags[2][2] = R.mipmap.flag3c;
        flags[2][3] = R.mipmap.flag3d;

        questions[3] = "¿Cuál es la bandera de Turquía?";
        flags[3][0] = R.mipmap.flag4a;
        flags[3][1] = R.mipmap.flag4b;
        flags[3][2] = R.mipmap.flag4c;//CORRECTA
        flags[3][3] = R.mipmap.flag4d;

        questions[4] = "¿Cuál es la bandera de Libia?";
        flags[4][0] = R.mipmap.flag5a;
        flags[4][1] = R.mipmap.flag5b;
        flags[4][2] = R.mipmap.flag5c;//CORRECTA
        flags[4][3] = R.mipmap.flag5d;

        questions[5] = "¿Cuál es la bandera de Malta?";
        flags[5][0] = R.mipmap.flag6a;
        flags[5][1] = R.mipmap.flag6b;
        flags[5][2] = R.mipmap.flag6c;
        flags[5][3] = R.mipmap.flag6d;//CORRECTA

        questions[6] = "¿Cuál es la bandera de Perú?";
        flags[6][0] = R.mipmap.flag7a;//CORRECTA
        flags[6][1] = R.mipmap.flag7b;
        flags[6][2] = R.mipmap.flag7c;
        flags[6][3] = R.mipmap.flag7d;

        questions[7] = "¿Cuál es la bandera de Argelia?";
        flags[7][0] = R.mipmap.flag8a;
        flags[7][1] = R.mipmap.flag8b;
        flags[7][2] = R.mipmap.flag8c;
        flags[7][3] = R.mipmap.flag8d;//CORRECTA

        questions[8] = "¿Cuál es la bandera de Países Bajos?";
        flags[8][0] = R.mipmap.flag9a;
        flags[8][1] = R.mipmap.flag9b;//CORRECTA
        flags[8][2] = R.mipmap.flag9c;
        flags[8][3] = R.mipmap.flag9d;

        questions[9] = "¿Cuál es la bandera de Taiguán?";
        flags[9][0] = R.mipmap.flag10a;
        flags[9][1] = R.mipmap.flag10b;
        flags[9][2] = R.mipmap.flag10c;//CORRECTA
        flags[9][3] = R.mipmap.flag10d;

        randomNumbers = randomQuest();      //Create random order in questions.
        updateQuestion();                   //Throw the first question.
        crono.start();                      //Timer starts.
    }

    //Send the clicked image to "sendAnswer (int ans)".
    public void selectFlag1(View view)
    {
        sendAnswer(1);
    }
    public void selectFlag2(View view)
    {
        sendAnswer(2);
    }
    public void selectFlag3(View view)
    {
        sendAnswer(3);
    }
    public void selectFlag4(View view)
    {
        sendAnswer(4);
    }

    private void sendAnswer(int ans)
    {
        switch(randomNumbers[questionNum]){             //Depends on the question.
            case 2:
            case 6:
                if (ans == 1){
                    score += Math.round(100/Nquestions);
                    Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show();
                }
                break;
            case 8:
                if (ans == 2){
                    score += Math.round(100/Nquestions);
                    Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show();
                }
                break;
            case 0:
            case 1:
            case 3:
            case 4:
            case 9:
                if (ans == 3){
                    score += Math.round(100/Nquestions);
                    Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show();
                }
                break;
            case 5:
            case 7:
                if (ans == 4){
                    score += Math.round(100/Nquestions);
                    Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                return;
            }
        crono.cancel();                             //Restart the chronometer.
        crono.start();
        questionNum++;                              //Next question.
        updateQuestion();                           //Change question and answers.
    }

    private void updateQuestion()
    {
        if (score > 100)
            score = 100;
        if (questionNum >= Nquestions && questionNum != 0) //If you pass the last question, game over.
            endGame();
        else                            //If not, change the question, the answer and the score.
        {
            txt_nquestions.setText(questionNum + "/" + Nquestions);
            questionText.setText(questions[randomNumbers[questionNum]]);
            ib1.setImageResource(flags[randomNumbers[questionNum]][0]);
            ib2.setImageResource(flags[randomNumbers[questionNum]][1]);
            ib3.setImageResource(flags[randomNumbers[questionNum]][2]);
            ib4.setImageResource(flags[randomNumbers[questionNum]][3]);
            scoreText.setText(score + " Puntos");
        }
    }

    /*
        Generates the numbers from 0 to 9 randomly
        and returns them in an array of type int.
     */

    private int [] randomQuest()
    {
        int pos;
        int nQuests = 10;
        int [] randomNumbers = new int[10];
        Stack< Integer > pQuests = new Stack < Integer > ();
        for (int i = 0; i < nQuests ; i++)
        {
            pos = (int) Math.floor(Math.random() * nQuests );
            while (pQuests.contains(pos)) {
                pos = (int) Math.floor(Math.random() * nQuests );
            }
            pQuests.push(pos);
            randomNumbers[i] = pos;
        }
        return (randomNumbers);
    }

    private void endGame()      //Finish the game and send the information to the result activity.
    {
        crono.cancel();                                              //Stop chronometer.
        Intent next = new Intent(this, Result.class);   //Mark Result as next activity.
        next.putExtra("questScore", score);       //Send the new score to the result activity.
        next.putExtra("lastQuest", 2);      //Says it's the first game on the menu.
        sendScores(next);                              //Send the previous 4 results.
        sendNumberOfQuestions(next);				   //Send the number of questions.
        sendDifficulty(next);				   		   //Send the number of difficulty.
        startActivity(next);                           //Switch to the next activity (Result).
    }

    @Override
    protected void onResume(){           //If you enter this activity and it is not the first time.
        super.onResume();
        receiveScores();                //Collect all the results so far.
        receiveNumberOfQuestions();		//Receive the number of questions from the previous activity.
        receiveDifficulty();			//Receive the number of difficulty.
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
        Send all old results to the next activity,
        the new result of this activity has been
        sent separately in "void endGame ()".

        The Result activity will take care of
        superimposing the new score in this
        array if it is higher.
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