package com.example.hamza.braintraning;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button Go_btn;
    TextView question;
    TextView CorrectLabel ;
    TextView resultLabel ;
    TextView timer;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button PlayAgainbtn;
    ArrayList<Integer> answers = new ArrayList<>();
    int locationOFtheCorrectAnswer;
     int WriteAnswer;
     int NumberOfQuestions=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        question = findViewById(R.id.question);
        CorrectLabel=findViewById(R.id.Correct);
        resultLabel=findViewById(R.id.result);
        timer=findViewById(R.id.timer);
         btn1 = findViewById(R.id.button1);
         btn2 = findViewById(R.id.button2);
         btn3 = findViewById(R.id.button3);
         btn4 = findViewById(R.id.button4);
         PlayAgainbtn=findViewById(R.id.button);
generateQuestion();
playAgain(PlayAgainbtn);



    }

    public void start(View view) {
        Go_btn.setVisibility(View.INVISIBLE);
    }

    public void chosseTheAnswer(View view) {
        if(view.getTag().toString().equals(Integer.toString(locationOFtheCorrectAnswer))){

       CorrectLabel.setText("Correct!!!");
            WriteAnswer++;

        }
        else{
            CorrectLabel.setText("Wrong!!");


        }
        NumberOfQuestions++;
        resultLabel.setText(WriteAnswer+"/"+NumberOfQuestions);
        generateQuestion();

    }
    public void generateQuestion()
    {
        Random randNum = new Random();
        int a = randNum.nextInt(21);
        int b = randNum.nextInt(21);
        int result = a + b;
        answers.clear();

        question.setText(Integer.toString(a) + "+" + Integer.toString(b));
        locationOFtheCorrectAnswer = randNum.nextInt(4);
        int inCorrectAnswer;

        for (int i = 0; i < 4; i++) {
            if (locationOFtheCorrectAnswer == i) {
                answers.add(result);
            } else {
                inCorrectAnswer = randNum.nextInt(41);
                while (inCorrectAnswer == result) {
                    inCorrectAnswer = randNum.nextInt(41);
                }
                answers.add(inCorrectAnswer);
            }
        }
        btn1.setText(Integer.toString(answers.get(0)));
        btn2.setText(Integer.toString(answers.get(1)));
        btn3.setText(Integer.toString(answers.get(2)));
        btn4.setText(Integer.toString(answers.get(3)));


    }
    public void playAgain(View view)
    {
        WriteAnswer=0;
        NumberOfQuestions=0;
        timer.setText("30 S");
        resultLabel.setText("0/0");
        CorrectLabel.setText("");

        new CountDownTimer(10100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf (millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                timer.setText("time finished");
                CorrectLabel.setText(resultLabel.getText());
                PlayAgainbtn.setVisibility(View.VISIBLE);

            }
        }.start();



    }
}
