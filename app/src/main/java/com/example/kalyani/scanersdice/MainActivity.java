package com.example.kalyani.scanersdice;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
static Integer UserScore=0;
    static Integer UserTotalScore=0;
    static Integer ComputerScore=0;
    static Integer ComputerTotalScore=0;
    static boolean turn=false;
    Button btRoll,btHold;
    Random r=new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView ev = findViewById(R.id.editText);
        ev.setText("Your Score: " + UserTotalScore + " Computer Score: " + ComputerTotalScore+"\nTurn Score "+0);
        btRoll = findViewById(R.id.button);
        btHold = findViewById(R.id.button2);

    }

    public int rollDice(){
        int num;
        num=r.nextInt(6)+1;
        ImageView iv=findViewById(R.id.imageView);
        switch(num) {
            case 1:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.dice1));
                break;
            case 2:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.dice2));
                break;
            case 3:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.dice3));
                break;
            case 4:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.dice4));
                break;
            case 5:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.dice5));
                break;
            case 6:iv.setImageDrawable(getResources().getDrawable(R.drawable.dice6));
            break;
        }
        return num;

    }

    public void rollClick(View view){
        int num;
        num=rollDice();
        if(num==1){

            Toast.makeText(getApplicationContext(), "You rolled 1 !!", Toast.LENGTH_SHORT).show();
            UserScore=0;
            btHold.setEnabled(false);
            btRoll.setEnabled(false);
            displayScore(UserTotalScore,ComputerTotalScore,UserScore);
            computerTurn();
        }
        else{
            UserScore=UserScore+num;
            int temp=UserTotalScore+UserScore;
            if(temp>=100){
                UserTotalScore=temp;
            }
            displayScore(UserTotalScore,ComputerTotalScore,UserScore);
            check();
        }


    }
    public void holdClick(View view){
        Toast.makeText(getApplicationContext(),"You Hold !!",Toast.LENGTH_SHORT).show();

        btHold.setEnabled(false);
        btRoll.setEnabled(false);
        UserTotalScore=UserTotalScore+UserScore;
        UserScore=0;
        computerTurn();
    }


    public void resetClick(View view){
        UserScore=0;
        UserTotalScore=0;
        ComputerScore=0;
        ComputerTotalScore=0;
        btHold.setEnabled(true);
        btRoll.setEnabled(true);
        displayScore(0,0,0);
    }


    public void computerTurn(){
        Handler hnd=new Handler();
        hnd.postDelayed(new Runnable() {
            @Override
            public void run() {
                int num=rollDice();
                if (num == 1 || ComputerScore>20) {
                    btHold.setEnabled(true);
                    btRoll.setEnabled(true);
                    if (num == 1) {
                        Toast.makeText(getApplicationContext(),"Computer rolled 1 !!",Toast.LENGTH_SHORT).show();

                        ComputerScore=0;

                    }
                    else{
                        ComputerTotalScore=ComputerTotalScore+ComputerScore;
                        Toast.makeText(getApplicationContext(),"Computer Hold !!",Toast.LENGTH_SHORT).show();
                        ComputerScore=0;
                    }
                    displayScore(UserTotalScore,ComputerTotalScore,0);
                    ComputerScore=0;
                }
                else{
                    ComputerScore=ComputerScore+num;
                    int temp=ComputerScore+ComputerTotalScore;
                    if(temp>=100){
                        ComputerTotalScore=temp;
                        displayScore(UserTotalScore,ComputerTotalScore,0);
                        check();
                    }

                    displayScore(UserTotalScore,ComputerTotalScore,ComputerScore);
                    computerTurn();
                }
            }
        },1000);



    }






    public void displayScore(int uscore,int cscore,int turnScore){
        TextView ev=findViewById(R.id.editText);
        ev.setText("Your Score: "+uscore+" Computer Score: "+cscore+"\nTurn Score"+turnScore);
    }

    public void resetScore(){
        UserScore=0;
        UserTotalScore=0;
        ComputerScore=0;
        ComputerTotalScore=0;
        btHold.setEnabled(true);
        btRoll.setEnabled(true);
        displayScore(0,0,0);
    }

    public void check(){
        if(UserTotalScore>=100){
            Toast.makeText(getApplicationContext(),"You Won!!",Toast.LENGTH_SHORT).show();
            resetScore();
        }
        if(ComputerTotalScore>=100) {
            Toast.makeText(getApplicationContext(), "Computer Won!!", Toast.LENGTH_SHORT).show();
            resetScore();
        }
    }


}


