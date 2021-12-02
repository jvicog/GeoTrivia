package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class Settings extends AppCompatActivity {

	private Switch sw;
	private EditText num;
	private TextView txt_warning;
	private Spinner spinner1;

	private int[] scores = {0, 0, 0, 0};                //Stores the scores of the 4 quests.
	private int Nquestions;								//Number of questions.
	private String optionText;                      	//Store the optionText.
	private String [] options = new String[2];      	//Store the options.
	private int difficulty;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		sw = (Switch) findViewById(R.id.switch1);
		num = (EditText) findViewById(R.id.num);
		txt_warning = (TextView) findViewById(R.id.txt_Warning);
		spinner1 = (Spinner) findViewById(R.id.spinner);                   //Spinner on the view.
	}

	public void saveSettings (View view)
	{
		if (questionNumber()){
			changeDifficulty();
			changeLanguages();
			menu(view);
		}
	}

	private void changeDifficultyOptions()
	{
		if (difficulty == 0)
		{
			options[0] = "Fácil";
			options[1] = "Difícil";
		}
		else
		{
			options[1] = "Fácil";
			options[0] = "Difícil";
		}
	}

	private void changeDifficulty()
	{
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options);
		spinner1.setAdapter(adapter);
		optionText = spinner1.getSelectedItem().toString();//Save the spinner selection to a string.
		if (optionText.equals("Fácil")){
			difficulty = 0;
		}
		else{
			difficulty = 1;
		}
	}

	private void changeLanguages()
	{
			if (sw.isChecked())
			{

			}
			else
			{

			}
	}

	private boolean questionNumber ()
	{
		int num_q;
		if (num.getText().toString().equals(null))
			num_q = Nquestions;
		else
			num_q = Integer.parseInt(num.getText().toString());

		if (num_q < 5 || num_q > 50)
		{
			txt_warning.setText ("El número de preguntas debe estar entre 5 y 50");
			return (false);
		}
		Nquestions = num_q;
		return(true);
	}

	public void menu(View view)         //Finish the game and send the information to the result activity.
	{
		Intent next = new Intent(this, Menu.class);   //Mark Result as next activity.
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
		num.setHint("Número de preguntas: " + Nquestions);
		changeDifficultyOptions();
		changeDifficulty();
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