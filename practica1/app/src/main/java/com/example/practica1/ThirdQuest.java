package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Stack;

public class ThirdQuest extends AppCompatActivity {

    //Create view objects.
    private Spinner spinner1;
    private TextView number, scoreText, questionText, txt_nquestions;
    private ImageView questionImage;


    //Create and initialize (some) variables.
    private String optionText;                      //Store the optionText.
    private String [] options = new String[4];      //Store the options.
    private int showTimer;                          //Initialize the Int Timer.
    private String timer;                           //Initialize the String Timer.
    private int score = 0;                          //Initialize the score.
    private int questionNum = 0;                    //Initialize the question number.
    private int [] randomNumbers = new int[10];     //Store the random numbers.
    private int[] questions = new int[10];          //Store the questions.
    private String[][] answers = new String[10][4]; //Store the answers.
    private int[] scores = {0, 0, 0, 0};            //Almacena las puntuaciones de las 4 quests.
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
        setContentView(R.layout.activity_third_quest);

        //Initialize view objects.
        questionText = (TextView) findViewById(R.id.txt1);                  //Question on the view.
        questionText.setText("¿Cuál es la provincia señalada?");
        spinner1 = (Spinner) findViewById(R.id.spinner);                   //Spinner on the view.
        number = (TextView) findViewById(R.id.crono);                      //Countdown on the view.
        questionImage = (ImageView) findViewById(R.id.questionImage);      //Question on the view.
        scoreText = (TextView) findViewById(R.id.txt2);                    //Score on the view.
        txt_nquestions = (TextView) findViewById(R.id.txt_nquestions); //Number of question on the view.

        //Initialize questions and answers
        questions[0] = R.mipmap.i_badajoz;
        answers[0][0] = "Murcia";
        answers[0][1] = "Huelva";
        answers[0][2] = "Badajoz";
        answers[0][3] = "Cáceres";

        questions[1] = R.mipmap.i_cordoba;
        answers[1][0] = "Sevilla";
        answers[1][1] = "Granada";
        answers[1][2] = "Ciudad Real";
        answers[1][3] = "Córdoba";

        questions[2] = R.mipmap.i_cuenca;
        answers[2][0] = "Guadalajara";
        answers[2][1] = "Cuenca";
        answers[2][2] = "Toledo";
        answers[2][3] = "Lugo";

        questions[3] = R.mipmap.i_granada;
        answers[3][0] = "Granada";
        answers[3][1] = "Jaén";
        answers[3][2] = "Almería";
        answers[3][3] = "Málaga";

        questions[4] = R.mipmap.i_huelva;
        answers[4][0] = "Sevilla";
        answers[4][1] = "Badajoz";
        answers[4][2] = "Huelva";
        answers[4][3] = "Cádiz";

        questions[5] = R.mipmap.i_la_coruna;
        answers[5][0] = "Lugo";
        answers[5][1] = "Ourense";
        answers[5][2] = "Pontevedra";
        answers[5][3] = "La Coruña";

        questions[6] = R.mipmap.i_murcia;
        answers[6][0] = "Alicante";
        answers[6][1] = "Murcia";
        answers[6][2] = "Castellón";
        answers[6][3] = "Valencia";

        questions[7] = R.mipmap.i_salamanca;
        answers[7][0] = "Salamanca";
        answers[7][1] = "Ávila";
        answers[7][2] = "León";
        answers[7][3] = "Valladolid";

        questions[8] = R.mipmap.i_teruel;
        answers[8][0] = "Zaragoza";
        answers[8][1] = "Guadalajara";
        answers[8][2] = "Teruel";
        answers[8][3] = "Soria";

        questions[9] = R.mipmap.i_vizcaya;
        answers[9][0] = "Guipúzcoa";
        answers[9][1] = "Navarra";
        answers[9][2] = "Cantabria";
        answers[9][3] = "Vizcaya";

        randomNumbers = randomQuest();      //Create random order in questions.
        updateQuestion();                   //Throw the first question.
        crono.start();                      //Timer starts.
    }

    public void sendBotton(View view)                     //User clicks submit button.
    {
        sendAnswer();
    }

    private void sendAnswer()
    {
        optionText = spinner1.getSelectedItem().toString();//Save the spinner selection to a string.
        switch(randomNumbers[questionNum]){                //Depends on the question.
            case 0:
                if (optionText.equals("Badajoz")){
                    score += Math.round(100/Nquestions);
                    Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show();
                }
                break;
            case 1:
                if (optionText.equals("Córdoba")){
                    score += Math.round(100/Nquestions);
                    Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if (optionText.equals("Cuenca")){
                    score += Math.round(100/Nquestions);
                    Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                if (optionText.equals("Granada")){
                    score += Math.round(100/Nquestions);
                    Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show();
                }
                break;
            case 4:
                if (optionText.equals("Huelva")){
                    score += Math.round(100/Nquestions);
                    Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show();
                }
                break;
            case 5:
                if (optionText.equals("La Coruña")){
                    score += Math.round(100/Nquestions);
                    Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show();
                }
                break;
            case 6:
                if (optionText.equals("Murcia")){
                    score += Math.round(100/Nquestions);
                    Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show();
                }
                break;
            case 7:
                if (optionText.equals("Salamanca")){
                    score += Math.round(100/Nquestions);
                    Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show();
                }
                break;
            case 8:
                if (optionText.equals("Teruel")){
                    score += Math.round(100/Nquestions);
                    Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show();
                }
                break;
            case 9:
                if (optionText.equals("Vizcaya")){
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
            ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options);
            questionImage.setImageResource(questions[randomNumbers[questionNum]]);
            options[0] = answers[randomNumbers[questionNum]][0];
            options[1] = answers[randomNumbers[questionNum]][1];
            options[2] = answers[randomNumbers[questionNum]][2];
            options[3] = answers[randomNumbers[questionNum]][3];
            scoreText.setText(score + " Puntos");
            spinner1.setAdapter(adapter);
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
        next.putExtra("lastQuest", 3);      //Says it's the first game on the menu.
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