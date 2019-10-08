/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author user
 */
public class Cell extends JButton {
    private final int row;
    private final int col;
    private int value;
    
    Cell(final int row, final int col, final ActionListener actionListener){
        this.row = row;
        this.col = col;
        addActionListener(actionListener);
    }
}
