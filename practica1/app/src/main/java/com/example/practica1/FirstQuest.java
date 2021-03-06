package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;

//HISTORIA.
public class FirstQuest extends AppCompatActivity {

    //Create view objects.
    private RadioButton rb1, rb2, rb3, rb4;
    private RadioGroup rg;
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
            number.setText(timer + "\"");                 //It is updated by screen.
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
        setContentView(R.layout.activity_first_quest);

        //Initialize view objects.
        rb1 = (RadioButton) findViewById(R.id.rb1);             //First botton on the view.
        rb2 = (RadioButton) findViewById(R.id.rb2);             //Second botton on the view.
        rb3 = (RadioButton) findViewById(R.id.rb3);             //Third botton on the view.
        rb4 = (RadioButton) findViewById(R.id.rb4);             //Fourth botton on the view.
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        number = (TextView) findViewById(R.id.crono);           //Countdown on the view.
        questionText = (TextView) findViewById(R.id.txt1);      //Question on the view.
        scoreText = (TextView) findViewById(R.id.txt2);         //Score on the view.
        txt_nquestions = (TextView) findViewById(R.id.txt_nquestions); //Number of question on the view.

        //Initialize questions and answers
        questions[0] = "??Qui??n fue el primer presidente de la democracia espa??ola tras el franquismo?";
        answers[0][0] = "Juan Carlos I";
        answers[0][1] = "Jos?? Mar??a Aznar";
        answers[0][2] = "Adolfo Su??rez";
        answers[0][3] = "Jos?? Luis Rodr??guez Zapatero";

        questions[1] = "??En qu?? a??o el ser humano pis?? la Luna por primera vez?";
        answers[1][0] = "1958";
        answers[1][1] = "1987";
        answers[1][2] = "1946";
        answers[1][3] = "1969";

        questions[2] = "??En qu?? a??o comenz?? la revoluci??n francesa?";
        answers[2][0] = "1798";
        answers[2][1] = "1789";
        answers[2][2] = "1879";
        answers[2][3] = "1897";

        questions[3] = "??Cu??ntos a??os dur?? la primera guerra mundial?";
        answers[3][0] = "4";
        answers[3][1] = "5";
        answers[3][2] = "6";
        answers[3][3] = "7";

        questions[4] = "??En qu?? ciudad fue promulgada la Constituci??n de 1812?";
        answers[4][0] = "Barcelona";
        answers[4][1] = "Madrid";
        answers[4][2] = "C??diz";
        answers[4][3] = "Toledo";

        questions[5] = "??Qui??n gobiern?? Rusia tras la Revoluci??n Rusa?";
        answers[5][0] = "Zar Nicol??s II";
        answers[5][1] = "Karl Marx";
        answers[5][2] = "I??sif Stalin";
        answers[5][3] = "Vladimir Lenin";

        questions[6] = "??Qu?? rey fue el sucesor de Alfonso X?";
        answers[6][0] = "Alfonso XI";
        answers[6][1] = "Sancho IV";
        answers[6][2] = "Felipe V";
        answers[6][3] = "Emiliano XXV";

        questions[7] = "??En qu?? a??o termin?? la Guerra Civil Espa??ola?";
        answers[7][0] = "1939";
        answers[7][1] = "1936";
        answers[7][2] = "1945";
        answers[7][3] = "1932";

        questions[8] = "??Qu?? d??a de 1808 comenz?? la Guerra de Independencia Espa??ola?";
        answers[8][0] = "1 de enero";
        answers[8][1] = "17 de marzo";
        answers[8][2] = "2 de mayo";
        answers[8][3] = "6 de agosto";

        questions[9] = "??En qu?? pa??s se invent?? la imprenta?";
        answers[9][0] = "Holanda";
        answers[9][1] = "Francia";
        answers[9][2] = "Estados Unidos";
        answers[9][3] = "Alemania";

        randomNumbers = randomQuest();      //Create random order in questions.
        crono.start();                      //Timer starts.
    }



    public void sendBotton(View view)                     //User clicks submit button.
    {
        //If nothing is pressed, it launches an error message, else it send the answer
        if (!rb1.isChecked() && !rb2.isChecked() && !rb3.isChecked() && !rb4.isChecked())
            Toast.makeText(this, "Presiona una opci??n", Toast.LENGTH_SHORT).show();
        else
            sendAnswer();
    }

    private void sendAnswer()
    {
        switch(randomNumbers[questionNum]){             //Depends on the question.
            case 0:
            case 4:
            case 8:
                if (rb3.isChecked()) {
                    score += Math.round(100/Nquestions);
                    Toast.makeText(this, "??Correcto!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show();
                }
                break;
            case 1:
            case 5:
            case 9:
                if (rb4.isChecked()){
                    score += Math.round(100/Nquestions);
                    Toast.makeText(this, "??Correcto!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
            case 6:
                if (rb2.isChecked()){
                    score += Math.round(100/Nquestions);
                    Toast.makeText(this, "??Correcto!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
            case 7:
                if (rb1.isChecked()){
                    score += Math.round(100/Nquestions);
                    Toast.makeText(this, "??Correcto!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                return;
        }
        rg.clearCheck();                            //Reset the click on all RadioButtons.
        crono.cancel();                             //Restart the chronometer.
        crono.start();
        questionNum++;                              //Next question.
        updateQuestion();                           //Change question and answers.
    }

    private void updateQuestion()                   //Change the questions and answers.
    {
        if (score > 100)
            score = 100;
        if (questionNum >= Nquestions && questionNum != 0) //If you pass the last question, game over.
            endGame();
        else                            //If not, change the question, the answer and the score.
        {
            txt_nquestions.setText(questionNum + "/" + Nquestions);
            questionText.setText(questions[randomNumbers[questionNum]]);
            rb1.setText(answers[randomNumbers[questionNum]][0]);
            rb2.setText(answers[randomNumbers[questionNum]][1]);
            rb3.setText(answers[randomNumbers[questionNum]][2]);
            rb4.setText(answers[randomNumbers[questionNum]][3]);
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
        Stack < Integer > pQuests = new Stack < Integer > ();
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
        next.putExtra("lastQuest", 1);      //Says it's the first game on the menu.
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
        updateQuestion();                   //Throw the first question.
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