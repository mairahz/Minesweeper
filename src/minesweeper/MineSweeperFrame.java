/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import javax.swing.JToggleButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author mairah
 */
public class MineSweeperFrame extends javax.swing.JFrame {
    
    public int [][] board; // Board contains the tiles
    JToggleButton[][] tile; // Tile is the square that users can click on
    
    /**
     * Creates new form MineSweeperFrame
     */
    public MineSweeperFrame() {
        initComponents();
        board = new int[9][9];
        tile = new JToggleButton[9][9];
        for (int i = 0; i<9; i++) {
            for (int j = 0; j<9; j++) {
                tile[i][j]= new JToggleButton();
                tile[i][j].setSize(jPanel1.getWidth()/9, jPanel1.getHeight()/9);
                jPanel1.add(tile[i][j]);
                tile[i][j].setLocation(j*jPanel1.getWidth()/9, i*jPanel1.getHeight()/9);
//                tiles[i][j].addActionListener(listen);
            }
        }          
    }
    
    /**
     * Function that resizes the board to fit the panel
     */
    private void resize() {
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                tile[i][j].setSize(jPanel1.getWidth()/9, jPanel1.getHeight()/9);
                jPanel1.add(tile[i][j]);
                tile[i][j].setLocation(j*jPanel1.getWidth()/9, i*jPanel1.getHeight()/9);
            }
        }
    }
    
     /**
     * Function that revalidate the board.
     * 0: Tile is not opened
     * 1 - 8: Number of bombs around it
     * -1: Tile has a bomb
     * -2: Tile is opened but does not have a bomb
     */
    private void reval(){
        for(int i = 0; i<9; i++) {
            for(int j = 0; j<9; j++) {
                // Tile is not opened
               if(board[i][j] == 0) {  
                   tile[i][j].setText("");
                   tile[i][j].setSelected(false); // Leave unopened
               } 
               // Tile is opened, no bomb
               if(board[i][j] == -2) {
                   tile[i][j].setText("");
                   tile[i][j].setSelected(true); // Leave opened and cannot be unclicked
               }
               // Bomb is nearby
               if(board[i][j] > 0){
                   tile[i][j].setText(""+board[i][j]); // Shows number of bomb neaby
                   tile[i][j].setSelected(true);
               }
//               if(!canPlay && block[i][j] == -1) tiles[i][j].setSelected(true);
            }
        }
        jPanel1.repaint();
    }
    
    ActionListener listen = new ActionListener(){
      public void actionPerformed(ActionEvent e) {
          reval();
      }  
    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentResized(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 384, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 284, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentResized
        resize();
    }//GEN-LAST:event_jPanel1ComponentResized

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MineSweeperFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MineSweeperFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MineSweeperFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MineSweeperFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MineSweeperFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
