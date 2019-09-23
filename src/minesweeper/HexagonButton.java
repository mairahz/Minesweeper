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
 * @author Mairah
 */
public class HexagonButton extends JToggleButton {
    public static boolean XYVertex=true;	//true: x,y are the co-ords of the first vertex.
    private static int BORDERS=50;	//default number of pixels for the border
    private static int s=0;	// length of one side
    private static int t=0;	// short side of 30o triangle outside of each hex
    private static int r=0;	// radius of inscribed circle (centre to middle of each side). r= h/2
    private static int h=0;	// height. Distance between centres of two adjacent hexes. Distance between two opposite sides in a hex.
    
    public static void setSide(int side) {
        s=side;
        t =  (int) (s / 2);			//t = s sin(30) = (int) CalculateH(s);
        r =  (int) (s * 0.8660254037844);	//r = s cos(30) = (int) CalculateR(s); 
        h=2*r;
    }
    public static void setHeight(int height) {
        h = height;			// h = basic dimension: height (distance between two adj centresr aka size)
        r = h/2;			// r = radius of inscribed circle
        s = (int) (h / 1.73205);	// s = (h/2)/cos(30)= (h/2) / (sqrt(3)/2) = h / sqrt(3)
        t = (int) (r / 1.73205);	// t = (h/2) tan30 = (h/2) 1/sqrt(3) = h / (2 sqrt(3)) = r / sqrt(3)
    }
    
    public static Polygon hex(int x0, int y0){
        int y = y0 + BORDERS;
        int x = x0 + BORDERS;
        
        if (s == 0  || h == 0) {
            System.out.println("ERROR: size of hex has not been set");
            return new Polygon();
        }
        
        int[] cx,cy;
        if (XYVertex) { 
            cx = new int[] {x,x+s,x+s+t,x+s,x,x-t};  //this is for the top left vertex being at x,y. Which means that some of the hex is cutoff.
        } else {
            cx = new int[] {x+t,x+s+t,x+s+t+t,x+s+t,x+t,x};	//this is for the whole hexagon to be below and to the right of this point
        }
        cy = new int[] {y,y,y+r,y+r+r,y+r+r,y+r};
        return new Polygon(cx,cy,6);
    }
    
    public static void drawHex(int i, int j, Graphics2D g2){
        int x = i*(s+t);
        int y = j*h+(i%2) * h/2;
        Polygon poly = hex(x,y);
        g2.fillPolygon(poly);
        g2.drawPolygon(poly);
    }
    
    public static void fillHex(int i, int j, int n, Graphics2D g2){
        int x = i*(s+t);
        int y = j*h+(i%2)*h/2;
        if(n<0) {
            g2.fillPolygon(hex(x,y));
        }
        
        if (n>0){
            g2.fillPolygon(hex(x,y));
        }
    }
    
//    private static final long serialVersionUID = 1L;
//    private static final int SIDES = 6;
//    private static final int SIDE_LENGTH = 50;
//    public static final int LENGTH = 95;
//    public static final int WIDTH = 105;
//    private int row = 0;
//    private int col = 0;
//    
//    public HexagonButton(int row, int col){
//        setContentAreaFilled(false);
//        setFocusPainted(true);
//        setBorderPainted(false);
//        setPreferredSize(new Dimension(WIDTH, LENGTH));
//        this.row = row;
//        this.col = col;
//    }
//    
//    @Override
//    public void paintComponent(Graphics g){
//        super.paintComponent(g);
//        Polygon hex = new Polygon();
//        for (int i = 0; i < SIDES; i++){
//            hex.addPoint((int) (50 + SIDE_LENGTH*Math.cos(i * 2 * Math.PI/SIDES)),
//                    (int) (50 + SIDE_LENGTH * Math.sin(i*2*Math.PI/SIDES)));
//        }
//        g.drawPolygon(hex);
//    }
//    
//    public int getRow(){
//        return row;
//        
//    }
//    
//    public int getCol(){
//        return col;
//    }
}
