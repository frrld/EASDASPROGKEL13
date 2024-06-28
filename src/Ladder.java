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

public class Ladder {
    private int fromPosition;
    private int toPosition;

    public Ladder(int x, int y){
        this.fromPosition = x;
        this.toPosition = y;
    }

    public void setFromPosition(int x){
        this.fromPosition = x;
    }

    public void setToPosition(int y){
        this.toPosition = y;
    }

    public int getFromPosition(){
        return fromPosition;
    }

    public int getToPosition(){
        return toPosition;
    }



}
