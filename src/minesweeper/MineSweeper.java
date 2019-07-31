/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

/**
 *
 * @author mairah
 */
public class MineSweeper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MineSweeperFrame MSF = new MineSweeperFrame();
        MSF.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH); // Sets the application to full screen
        MSF.show();
    }
    
}
