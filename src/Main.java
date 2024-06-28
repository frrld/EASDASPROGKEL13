//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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
public class Main {

    public static void main(String[] args) {
        Audio player = new Audio();
        String soundFileName = "C:/Users/Farrel MSI/IdeaProjects/EAS DASPROG/src/mixkit-bonus-extra-in-a-video-game-2064.wav";
        player.playSound(soundFileName);
        new SnakeAndLadderGUI();
    }
}