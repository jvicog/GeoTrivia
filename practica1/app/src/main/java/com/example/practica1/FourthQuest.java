package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Stack;

public class FourthQuest extends AppCompatActivity {

    //Create view objects.
    private CheckBox cb1, cb2, cb3, cb4;
    private TextView number, questionText, scoreText, txt_nquestions;

    //Create and initialize (some) variables.
    private int showTimer;                          //Initialize the Int Timer.
    private String timer;                           //Initialize the String Timer.
    private int score = 0;                          //Initialize the score.
    private int questionNum = 0;                    //Initialize the question number.
    private int [] randomNumbers = new int[10];     //Store the random numbers.
    private String[] questions = new String[10];    //Store the questions.
    private String[][] answers = new String[10][4]; //Store the answers.
    private int[] scores = {0, 0, 0, 0};            //Stores the scores of the 4 quests.
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
            sendAnswer();                          //The answer is sent automatically.
        }
    };


    /*
        When this activity is turned on it will
        do whatever is inside the onCreate method.

    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_quest);
        //Initialize view objects.
        cb1 = (CheckBox) findViewById(R.id.checkBox1);             //First botton on the view.
        cb2 = (CheckBox) findViewById(R.id.checkBox2);             //Second botton on the view.
        cb3 = (CheckBox) findViewById(R.id.checkBox3);             //Third botton on the view.
        cb4 = (CheckBox) findViewById(R.id.checkBox4);             //Fourth botton on the view.
        number = (TextView) findViewById(R.id.crono);           //Countdown on the view.
        questionText = (TextView) findViewById(R.id.txt1);      //Question on the view.
        scoreText = (TextView) findViewById(R.id.txt2);         //Score on the view.
        txt_nquestions = (TextView) findViewById(R.id.txt_nquestions); //Number of question on the view.

        //Initialize questions and answers
        questions[0] = "¿Qué ciudad(es) pertenece(n) a la comunidad de Andalucía?";
        answers[0][0] = "Castellón";
        answers[0][1] = "Jerez de la Frontera";
        answers[0][2] = "Algeciras";
        answers[0][3] = "Orihuela";

        questions[1] = "¿Qué ciudad(es) pertenece(n) a la comunidad de Cataluña?";
        answers[1][0] = "Vinaroz";
        answers[1][1] = "Torrent";
        answers[1][2] = "Baza";
        answers[1][3] = "Manresa";

        questions[2] = "¿Qué ciudad(es) pertenece(n) a la comunidad de Extremadura?";
        answers[2][0] = "Mérida";
        answers[2][1] = "Puertollano";
        answers[2][2] = "Palma del Río";
        answers[2][3] = "Guadalupe";

        questions[3] = "¿Qué ciudad(es) pertenece(n) a la comunidad de Madrid?";
        answers[3][0] = "Fuensalida";
        answers[3][1] = "Alcalá de Henares";
        answers[3][2] = "Huete";
        answers[3][3] = "Ocaña";

        questions[4] = "¿Qué ciudad(es) pertenece(n) a la comunidad de Galicia?";
        answers[4][0] = "Navia";
        answers[4][1] = "Carballo";
        answers[4][2] = "Mellid";
        answers[4][3] = "Truchas";

        questions[5] = "¿Qué ciudad(es) pertenece(n) a la comunidad de Aragón?";
        answers[5][0] = "Andújar";
        answers[5][1] = "Cambrils";
        answers[5][2] = "Moraleja";
        answers[5][3] = "Calatayud";

        questions[6] = "¿Qué ciudad(es) pertenece(n) a la comunidad de Asturias?";
        answers[6][0] = "Oviedo";
        answers[6][1] = "Ferrol";
        answers[6][2] = "Santander";
        answers[6][3] = "Gijón";

        questions[7] = "¿Qué ciudad(es) pertenece(n) a la comunidad de Murcia?";
        answers[7][0] = "Elche";
        answers[7][1] = "Cartagena";
        answers[7][2] = "Benidorm";
        answers[7][3] = "Gandía";

        questions[8] = "¿Qué ciudad(es) pertenece(n) a la comunidad de Castilla y León?";
        answers[8][0] = "Trancoso";
        answers[8][1] = "Aranda del Duero";
        answers[8][2] = "Ciudad Rodrigo";
        answers[8][3] = "Guarda";

        questions[9] = "¿Qué ciudad(es) pertenece(n) a la comunidad de Castilla la Mancha?";
        answers[9][0] = "Chiclana";
        answers[9][1] = "Motril";
        answers[9][2] = "Benalmádena";
        answers[9][3] = "Valdepeñas";

        randomNumbers = randomQuest();      //Create random order in questions.
        updateQuestion();                   //Throw the first question.
        crono.start();                      //Timer starts.
    }
    public void sendBotton(View view)                     //User clicks submit button.
    {
        //If nothing is pressed, it launches an error message, else it send the answer
        if (!cb1.isChecked() && !cb2.isChecked() && !cb3.isChecked() && !cb4.isChecked())
            Toast.makeText(this, "Presiona una opción", Toast.LENGTH_LONG).show();
        else
            sendAnswer();
    }

    private void sendAnswer()
    {
        switch(randomNumbers[questionNum]){             //Depends on the question.
            case 0:
            case 4:
            case 8:
                if (cb3.isChecked() && cb2.isChecked() && !cb1.isChecked() && !cb4.isChecked()){
                    score += Math.round(100/Nquestions);
                    Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show();
                }
                break;
            case 1:
            case 5:
            case 9:
                if (cb4.isChecked() && !cb1.isChecked() && !cb2.isChecked() && !cb3.isChecked()){
                    score += Math.round(100/Nquestions);
                    Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
            case 6:
                if (cb1.isChecked() && cb4.isChecked() && !cb2.isChecked() && !cb3.isChecked()){
                    score += Math.round(100/Nquestions);
                    Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
            case 7:
                if (cb2.isChecked() && !cb1.isChecked() && !cb3.isChecked() && !cb4.isChecked()){
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
        cb1.setChecked(false);                      //Set all checkboxes to false.
        cb2.setChecked(false);
        cb3.setChecked(false);
        cb4.setChecked(false);

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
            cb1.setText(answers[randomNumbers[questionNum]][0]);
            cb2.setText(answers[randomNumbers[questionNum]][1]);
            cb3.setText(answers[randomNumbers[questionNum]][2]);
            cb4.setText(answers[randomNumbers[questionNum]][3]);
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
        next.putExtra("lastQuest", 4);      //Says it's the first game on the menu.
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