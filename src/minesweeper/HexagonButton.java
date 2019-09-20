/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author user
 */
public class HexagonButton extends JToggleButton {
    private static final long serialVersionUID = 1L;
    private static final int SIDES = 6;
    private static final int SIDE_LENGTH = 50;
    public static final int LENGTH = 95;
    public static final int WIDTH = 105;
    private int row = 0;
    private int col = 0;
    
    public HexagonButton(int row, int col){
        setContentAreaFilled(false);
        setFocusPainted(true);
        setBorderPainted(false);
        setPreferredSize(new Dimension(WIDTH, LENGTH));
        this.row = row;
        this.col = col;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Polygon hex = new Polygon();
        
    }
}
