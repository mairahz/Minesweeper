/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

/**
 *
 * @author mairah
 */
public class Score {
    public static void startScore(JLabel label, boolean canPlay, boolean firstMove){
        
        Timer timer = new Timer(); // new Timer
        TimerTask task = new TimerTask() {
            private int counter;
            public void run(){
                if(!canPlay){
                    timer.cancel();
                } else if (canPlay && !firstMove) {
                    label.setText(Integer.toString(counter));
                    counter ++;
                }
            }
            
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
    
}
