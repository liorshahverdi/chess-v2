package chess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game extends JLayeredPane {
    public static final int WIDTH = 1080;
    public static final int HEIGHT = 780;
    private static final int GRID_ROWS = 8;
    private static final int GRID_COLS = 8;
    private static final int GAP = 3;
    private static final Dimension LAYERED_PANE_SIZE = new Dimension(WIDTH, HEIGHT);
    private static final Dimension LABEL_SIZE = new Dimension(60, 40);
    private GridLayout gridlayout = new GridLayout(GRID_ROWS, GRID_COLS, GAP, GAP);
    private JPanel backingPanel = new JPanel(gridlayout);
    private JPanel[][] panelGrid = new JPanel[GRID_ROWS][GRID_COLS];
    
    private ImageIcon black_castle_img = new ImageIcon("Images\\black-castle.png");
    private JLabel blackCastle1 = new JLabel(black_castle_img);
    private JLabel blackCastle2 = new JLabel(black_castle_img);
    private ImageIcon black_bishop_img = new ImageIcon("Images\\black-bishop.png");
    private JLabel blackBishop1 = new JLabel(black_bishop_img);
    private JLabel blackBishop2 = new JLabel(black_bishop_img);
    private ImageIcon black_horse_img = new ImageIcon("Images\\black-horse.png");
    private JLabel blackHorse1 = new JLabel(black_horse_img);
    private JLabel blackHorse2 = new JLabel(black_horse_img);
    private ImageIcon black_queen_img = new ImageIcon("Images\\black-queen.png");
    private JLabel blackQueen = new JLabel(black_queen_img);
    private ImageIcon black_king_img = new ImageIcon("Images\\black-king.png");
    private JLabel blackKing = new JLabel(black_king_img);
    private ImageIcon black_pawn_img = new ImageIcon("Images\\black-pawn.png");
    private JLabel blackPawn1 = new JLabel(black_pawn_img);
    private JLabel blackPawn2 = new JLabel(black_pawn_img);
    private JLabel blackPawn3 = new JLabel(black_pawn_img);
    private JLabel blackPawn4 = new JLabel(black_pawn_img);
    private JLabel blackPawn5 = new JLabel(black_pawn_img);
    private JLabel blackPawn6 = new JLabel(black_pawn_img);
    private JLabel blackPawn7 = new JLabel(black_pawn_img);
    private JLabel blackPawn8 = new JLabel(black_pawn_img);
    
    private ImageIcon white_castle_img = new ImageIcon("Images\\white-castle.png");
    private JLabel whiteCastle1 = new JLabel(white_castle_img);
    private JLabel whiteCastle2 = new JLabel(white_castle_img);
    private ImageIcon white_bishop_img = new ImageIcon("Images\\white-bishop.png");
    private JLabel whiteBishop1 = new JLabel(white_bishop_img);
    private JLabel whiteBishop2 = new JLabel(white_bishop_img);
    private ImageIcon white_horse_img = new ImageIcon("Images\\white-horse.png");
    private JLabel whiteHorse1 = new JLabel(white_horse_img);
    private JLabel whiteHorse2 = new JLabel(white_horse_img);
    private ImageIcon white_queen_img = new ImageIcon("Images\\white-queen.png");
    private JLabel whiteQueen = new JLabel(white_queen_img);
    private ImageIcon white_king_img = new ImageIcon("Images\\white-king.png");
    private JLabel whiteKing = new JLabel(white_king_img);
    private ImageIcon white_pawn_img = new ImageIcon("Images\\white-pawn.png");
    private JLabel whitePawn1 = new JLabel(white_pawn_img);
    private JLabel whitePawn2 = new JLabel(white_pawn_img);
    private JLabel whitePawn3 = new JLabel(white_pawn_img);
    private JLabel whitePawn4 = new JLabel(white_pawn_img);
    private JLabel whitePawn5 = new JLabel(white_pawn_img);
    private JLabel whitePawn6 = new JLabel(white_pawn_img);
    private JLabel whitePawn7 = new JLabel(white_pawn_img);
    private JLabel whitePawn8 = new JLabel(white_pawn_img);
    
    public Game() {
        backingPanel.setSize(LAYERED_PANE_SIZE);
        backingPanel.setLocation(2 * GAP, 2 * GAP);
        backingPanel.setBackground(Color.gray);
        
        int k=0;
        
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLS; col++) {
                panelGrid[row][col] = new JPanel(new GridBagLayout());
                backingPanel.add(panelGrid[row][col]);
                if (row==0 || row==2 || row==4 || row==6){
                	if (col%2==0) panelGrid[row][col].setBackground(Color.white);
                	else panelGrid[row][col].setBackground(Color.gray);
                }
                else{
                	if (col%2==0) panelGrid[row][col].setBackground(Color.gray);
                	else panelGrid[row][col].setBackground(Color.white);
                }
                k++;
            }
        }
        
        /*redLabel.setOpaque(true);
        redLabel.setBackground(Color.red.brighter().brighter());
        redLabel.setPreferredSize(LABEL_SIZE);
        panelGrid[4][3].add(redLabel);

        blueLabel.setOpaque(true);
        blueLabel.setBackground(Color.blue.brighter().brighter());
        blueLabel.setPreferredSize(LABEL_SIZE);
        panelGrid[1][1].add(blueLabel); */
        
        panelGrid[0][0].add(blackCastle1);
        panelGrid[0][1].add(blackHorse1);
        panelGrid[0][2].add(blackBishop1);
        panelGrid[0][3].add(blackQueen);
        panelGrid[0][4].add(blackKing);
        panelGrid[0][5].add(blackBishop2);
        panelGrid[0][6].add(blackHorse2);
        panelGrid[0][7].add(blackCastle2);
        panelGrid[1][0].add(blackPawn1);
        panelGrid[1][1].add(blackPawn2);
        panelGrid[1][2].add(blackPawn3);
        panelGrid[1][3].add(blackPawn4);
        panelGrid[1][4].add(blackPawn5);
        panelGrid[1][5].add(blackPawn6);
        panelGrid[1][6].add(blackPawn7);
        panelGrid[1][7].add(blackPawn8);
        
        panelGrid[7][0].add(whiteCastle1);
        panelGrid[7][7].add(whiteCastle2);
        panelGrid[7][2].add(whiteBishop1);
        panelGrid[7][5].add(whiteBishop2);
        panelGrid[7][1].add(whiteHorse1);
        panelGrid[7][6].add(whiteHorse2);
        panelGrid[7][3].add(whiteQueen);
        panelGrid[7][4].add(whiteKing);
        panelGrid[6][0].add(whitePawn1);
        panelGrid[6][1].add(whitePawn2);
        panelGrid[6][2].add(whitePawn3);
        panelGrid[6][3].add(whitePawn4);
        panelGrid[6][4].add(whitePawn5);
        panelGrid[6][5].add(whitePawn6);
        panelGrid[6][6].add(whitePawn7);
        panelGrid[6][7].add(whitePawn8);
       
        backingPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        setPreferredSize(LAYERED_PANE_SIZE);
        add(backingPanel, JLayeredPane.DEFAULT_LAYER);
        MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
        addMouseListener(myMouseAdapter);
        addMouseMotionListener(myMouseAdapter);
    }

    private class MyMouseAdapter extends MouseAdapter {
        private JLabel dragLabel = null;
        private int dragLabelWidthDiv2;
        private int dragLabelHeightDiv2;
        private JPanel clickedPanel = null;

        @Override
        public void mousePressed(MouseEvent me) {
            clickedPanel = (JPanel) backingPanel.getComponentAt(me.getPoint());
            Component[] components = clickedPanel.getComponents();
            if (components.length == 0) {
                return;
            }
            // if we click on jpanel that holds a jlabel
            if (components[0] instanceof JLabel) {

                // remove label from panel
                dragLabel = (JLabel) components[0];
                clickedPanel.remove(dragLabel);
                clickedPanel.revalidate();
                clickedPanel.repaint();

                dragLabelWidthDiv2 = dragLabel.getWidth() / 2;
                dragLabelHeightDiv2 = dragLabel.getHeight() / 2;

                int x = me.getPoint().x - dragLabelWidthDiv2;
                int y = me.getPoint().y - dragLabelHeightDiv2;
                dragLabel.setLocation(x, y);
                add(dragLabel, JLayeredPane.DRAG_LAYER);
                repaint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent me) {
            if (dragLabel == null) {
                return;
            }
            int x = me.getPoint().x - dragLabelWidthDiv2;
            int y = me.getPoint().y - dragLabelHeightDiv2;
            dragLabel.setLocation(x, y);
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            if (dragLabel == null) {
                return;
            }
            remove(dragLabel); // remove dragLabel for drag layer of JLayeredPane
            JPanel droppedPanel = (JPanel) backingPanel.getComponentAt(me.getPoint());
            if (droppedPanel == null) {
                // if off the grid, return label to home
                clickedPanel.add(dragLabel);
                clickedPanel.revalidate();
            } else {
                int r = -1;
                int c = -1;
                searchPanelGrid: for (int row = 0; row < panelGrid.length; row++) {
                    for (int col = 0; col < panelGrid[row].length; col++) {
                        if (panelGrid[row][col] == droppedPanel) {
                            r = row;
                            c = col;
                            break searchPanelGrid;
                        }
                    }
                }

                if (r == -1 || c == -1) {
                    // if off the grid, return label to home
                    clickedPanel.add(dragLabel);
                    clickedPanel.revalidate();
                } else {
                	Component[] theseComponents = droppedPanel.getComponents();
                	if (theseComponents.length != 0){
                		clickedPanel.add(dragLabel);
                		clickedPanel.revalidate();
                	} else{
                		droppedPanel.add(dragLabel);
                        droppedPanel.revalidate();
                	}
                }
            }

            repaint();
            dragLabel = null;
        }
    }

    private static void createAndShowUI() {
        JFrame frame = new JFrame("Chess");
        frame.getContentPane().add(new Game());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                createAndShowUI();
            }
        });
    }
}
