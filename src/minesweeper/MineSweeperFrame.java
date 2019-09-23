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
    String ver = "square"; // Version of the game
    JToggleButton[][] tile; // Tile is the square that users can click on
//    HexagonButton[][] tile;
    private final int bombs = 10; // Number of bombs on the board
    boolean firstMove, canPlay; // firstMove checks if the first move has been made. canPlay checks if the game can be played.
    java.util.Timer timer = new java.util.Timer(); // Timer to keep score
    
    /**
     * Creates new form MineSweeperFrame
     */
    public MineSweeperFrame() {
        initComponents();
//        int offsetX = -10;
//        int offsetY = 0;
//        
        board = new int[9][9];
//        tile = new HexagonButton[9][9];
        tile = new JToggleButton[9][9];
        for (int i = 0; i<9; i++) {
            for (int j = 0; j<9; j++) {
//                tile[i][j] = new HexagonButton(i, j);
                tile[i][j]= new JToggleButton();
                tile[i][j].setSize(jPanel1.getWidth()/9, jPanel1.getHeight()/9);
                jPanel1.add(tile[i][j]);
                tile[i][j].setLocation(j*jPanel1.getWidth()/9, i*jPanel1.getHeight()/9);
                tile[i][j].addActionListener(listen);
//                tile[i][j].setBounds(offsetY, offsetX, 105, 95);
//                offsetX += 87;
            }
//            if(i%2 == 0){
//                System.out.print("mod 2");
//                offsetX = -52;
//            } else {
//                System.out.print("Not");
//                offsetX = -10;
//            }
//            offsetY += 76;
        }  
        firstMove = false; // First move has not been made.
        canPlay = true; // Player can start to play the game.
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
               if(!canPlay && board[i][j] == -1) tile[i][j].setSelected(true);
            }
        }
        jPanel1.repaint();
    }
    
    /**
     * Function that spawns the bombs randomly.
     * It ensures that the bombs are not spawned on the same tile.
     * Make sure bomb is not spawned on the first tile that user clicked on.
     */
    private void spawn(int y, int x){
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
     * Function that checks the number of bombs around the tile.
     */
    private void open(int y, int x){
        // if bomb is not near or tile is opened or has a bomb, returns the function.
        if(y < 0 || x < 0 || x > 8 || y > 8 || board[y][x] != 0) return;
        int bomb = 0;
        for (int i = y - 1; i <= y + 1;i++) {
            for (int j = x - 1; j <= x + 1;j++) {
                if(!(j < 0 || i < 0 || j > 8 || i > 8) && board[i][j] == -1) {
                    bomb++;
                }
            }
        }
        if(bomb == 0){
            board[y][x] = -2; // tile has no bomb around
            for (int i = y - 1; i <= y + 1;i++) {
                for (int j = x - 1; j <= x + 1;j++) {
                    if(!(j < 0 || i < 0 || j > 8 || i > 8))
                    if(i != y || j !=x) open(i,j);
                }
            }
        } else board[y][x] = bomb;
    }
    
    /**
     * Function that sets board when player loses.
     * Message pops out saying that player loses and shows location of the bombs.
     */
    private void lose(){
        canPlay = false;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == -1){
                    tile[i][j].setText("BOOM");
                    tile[i][j].setSelected(true);
                }
            }
        }
        javax.swing.JOptionPane.showMessageDialog(null, "You LOSE!!!");
    }
    
    /**
     * Function that sets board when player wins.
     * Message pops out saying that player wins.
     */
    private void checkWin(){
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
        if(win){
            javax.swing.JOptionPane.showMessageDialog(null, "You win!!!");
            canPlay = false; 
        }
    }
    
    ActionListener listen = new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            boolean found = false; // Found tile that is selected
            int i = 0, j =0;
            // Find tile that player has selected.
            for(i = 0; i < 9; i++){
                for(j = 0; j < 9; j++){
                    if(e.getSource() == tile[i][j]){
                         found = true;
                         break;
                    }
                }
                if(found) break;
            }

            // Check if game can be played.
            if(canPlay){
                tile[i][j].setSelected(true);
                if(!firstMove){
                    spawn(i, j);
                    Score.startScore(jLabel1, canPlay, firstMove);
                    firstMove = true;
                }
                if(board[i][j] != -1){
                    open(i, j);
                    reval();
                } else lose();
                checkWin();
            } else {
                reval();
            }
            
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

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
            .addGap(0, 195, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Score"));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu1.setText("Game");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("New Game");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Help");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Change Version");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentResized
        if(ver == "square"){
            resize();
        }
    }//GEN-LAST:event_jPanel1ComponentResized

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        board = new int[9][9];
        reval();
        canPlay = true;
        firstMove = false;
        jLabel1.setText("0");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        javax.swing.JOptionPane.showMessageDialog(null, "<html><body><p style='width: 200px;'>Click on any tile to start the game. </br> Open all"
                + " tiles without a bomb in it to win the game. </br> If you open a tile containing a bomb, you lose! </br>"
                + " The number on the tile represents the number of bombs near the tile. </br> The bomb could be "
                + "to the left, right, above, below or any diagonals of the tile so be careful. </br> Have fun</p></body></html>");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if(ver == "square"){
            ver = "hex";
        } else {
            ver = "square";
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
