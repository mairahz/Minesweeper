/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *
 * @author Mairah
 */
public class MineSweeperController {
    private static int bombs = 10; // Number of bombs on the board
    public int [][] board; // Board contains the tiles
    JToggleButton[][] tile; // Tile is the square that users can click on
    HexagonButton[][] hexTile; // Hexagon tiles
    boolean firstMove, canPlay; // firstMove checks if the first move has been made. canPlay checks if the game can be played.
    java.util.Timer timer = new java.util.Timer(); // Timer to keep score
    
    /**
     * Function that spawns the bombs randomly.
     * It ensures that the bombs are not spawned on the same tile.
     * Make sure bomb is not spawned on the first tile that user clicked on.
     */
    public static void spawn(int y, int x, int[][] board){
        for(int k = 1; k<=bombs; k++){
            int i, j;
            do {
                i = (int)(Math.random()*(9-.01));
                j = (int)(Math.random()*(9-.01));
            }
            while(board[i][j] == -1 || (i == y && j == x)); 
                board[i][j] = -1; 
        }
    }
    
    /**
     * Function that sets board when player wins.
     * Message pops out saying that player wins.
     */
    public static boolean checkWin(int[][] board){
        boolean win = true;
        for(int i = 0; i < 9; i ++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == 0){
                    win = false;
                    break;
                }
            }
            if(!win) break;
        }
        return win;
    }
}
