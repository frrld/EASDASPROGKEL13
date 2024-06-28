/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : C
 * Group    : 13
 * Members  :
 * 1. 5026231175 - Muhammad Farrel Danendra
 * 2. 5026231191 - Dzakiyyah Nur Aini Hendryna
 * 3. 5026231228 - Annisa Nur Fauzi
 * ------------------------------------------------------
 */

import java.util.Scanner;

public class Player{
    // states
    private String userName;
    private int position;
    private int lives;

    // methods
    // constructor
    public Player(String userName){
        this.userName = userName;
        this.position = 0;
        this.lives = 3;
    }

    // setter
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setPosition(int position){
        this.position = position;
    }
    public void setLives(int lives){
        this.lives = lives;
    }

    // getter
    public String getUserName(){
        return userName;
    }

    public int getPosition(){
        return position;
    }

    public int getLives(){
        return lives;
    }

    // rollDice method
    public int rollDice(){
        return (int) (Math.random() * 6) + 1;
    }

    // moveAround method
    public void moveAround(int x, int boardSize){
        if(this.position + x > boardSize)
            this.position = boardSize - ((this.position + x) % boardSize);
        else
            this.position += x;
    }

    // reduceLives method
    public void reduceLife(){
        this.lives--;
        if(this.lives <= 0){
            this.lives = 3;
            this.position = 1;
        }
    }
}
