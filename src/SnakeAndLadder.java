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

import java.util.ArrayList;
import java.util.Scanner;

public class SnakeAndLadder {
    // states
    private ArrayList<Player> players;
    private ArrayList<Snake> snakes;
    private ArrayList<Ladder> ladders;
    private int boardSize;
    // 0 = the game isn't started yet
    // 1 = the game is started
    // 2 = the game is over
    private int status;
    private int playerInTurn;

    public SnakeAndLadder(int boardSize) {
        this.boardSize = boardSize;
        this.players = new ArrayList<Player>();
        this.snakes = new ArrayList<Snake>();
        this.ladders = new ArrayList<Ladder>();
        this.status = 0;
    }

    public void initiateGame() {
        // set the ladders
        int[][] ladders = {
                {2, 23}, {8, 34}, {20, 77}, {32, 68}, {41, 79}, {74, 88}, {82, 96}, {85, 95}
        };
        addLadders(ladders);
        // set the snakes
        int[][] snakes = {
                {29, 9}, {38, 15}, {47, 5}, {53, 33}, {62, 37}, {86, 54}, {92, 70}, {97, 25}
        };
        addSnakes(snakes);
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public void setPlayerInTurn(int p) {
        this.playerInTurn = p;
    }
    public void addPlayer(Player p) {
        this.players.add(p);
    }
    public void addSnake(Snake s) {
        this.snakes.add(s);
    }
    public void addSnakes(int[][] s) {
        for (int i = 0; i < s.length; i++) {
            Snake snake = new Snake(s[i][0], s[i][1]);
            this.snakes.add(snake);
        }
    }
    public void addLadder(Ladder l) {
        this.ladders.add(l);
    }
    public void addLadders(int[][] l) {
        for (int m = 0; m < l.length; m++) {
            Ladder ladder = new Ladder(l[m][0], l[m][1]);
            this.ladders.add(ladder);
        }
    }

    public int getBoardSize() {
        return boardSize;
    }
    public int getStatus() {
        return status;
    }

    public int getPlayerInTurn() {
        return playerInTurn;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Snake> getSnakes() {
        return snakes;
    }

    public ArrayList<Ladder> getLadders() {
        return ladders;
    }
    public int getTurn() {
        if (this.status == 0) {
            double r = Math.random();
            if (r < 0.5) return 0;
            else return 1;
        } else {
            if (playerInTurn == 0) {
                return 1;
            } else return 0;
        }
    }

    public void movePlayer(Player p, int x) {
        p.moveAround(x, this.boardSize);

        // checking the ladder
        for (int i = 0; i < this.ladders.size(); i++) {
            Ladder l = this.ladders.get(i);
            if (p.getPosition() == l.getFromPosition()) {
                p.setPosition(l.getToPosition());
                System.out.println(p.getUserName() + " got ladder from " + l.getFromPosition() + " climb to " + l.getToPosition());
            }
        }
        // checking the snake
        for (int i = 0; i < this.snakes.size(); i++) {
            Snake s = this.snakes.get(i);
            if (p.getPosition() == s.getFromPosition()) {
                p.setPosition(s.getToPosition());
                System.out.println(p.getUserName() + " got snake from " + s.getFromPosition() + " slide down to " + s.getToPosition());
            }
        }
        // checking for DoubleDice
        if (p.getPosition() == 13 || p.getPosition() == 30 || p.getPosition() == 51 || p.getPosition() == 90) {
            System.out.println(p.getUserName() + " landed on a DoubleDice position! Rolling again...");
            int x2 = p.rollDice();
            System.out.println("Second dice number: " + x2);
            p.moveAround(x2, this.boardSize);
            System.out.println(p.getUserName() + " new position after DoubleDice is " + p.getPosition());
        }
        // checking for Bomb
        if (p.getPosition() == 21 || p.getPosition() == 35 || p.getPosition() == 60 || p.getPosition() == 99) {
            System.out.println(p.getUserName() + " landed on a Bomb position! Losing one life...");
            p.reduceLife();
            System.out.println(p.getUserName() + " now has " + p.getLives() + " lives and is at position " + p.getPosition());
        }

        System.out.println(p.getUserName() + " new position is " + p.getPosition());
        if (p.getPosition() == this.boardSize) {
            this.status = 2;
            System.out.println("The winner is: " + p.getUserName());
        }
    }

    public void play() {
        System.out.println("enter first player name:");
        Scanner sc = new Scanner(System.in);
        String firstPlayer = sc.nextLine();
        System.out.println("enter second player name:");
        String secondPlayer = sc.nextLine();

        Player p1 = new Player(firstPlayer);
        Player p2 = new Player(secondPlayer);

        initiateGame();

        addPlayer(p1);
        addPlayer(p2);

        do {
            // Determine the first turn
            int t1 = getTurn();

            setStatus(1);
            setPlayerInTurn(t1);

            Player playerInTurn = getPlayers().get(t1);
            System.out.println("---------------------------------");
            System.out.println("Player in turn is " + playerInTurn.getUserName());

            // player in turn roll dice
            System.out.println(playerInTurn.getUserName() + " it's your turn, please press enter to roll dice");
            sc.nextLine();  // Wait for Enter key press
            int x = playerInTurn.rollDice();

            System.out.println("Dice number: " + x);
            movePlayer(playerInTurn, x);

        } while (getStatus() != 2);
    }
}
