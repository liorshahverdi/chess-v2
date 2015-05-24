package chess;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class WhitePawn extends JLabel implements Piece{
	private static ImageIcon img = new ImageIcon("Images\\white-pawn.png");
	private BoardCell currentCellOccupied;
	
	public void setCurrentCellOccupied(BoardCell x){ this.currentCellOccupied = x; }
	public BoardCell getCurrentCellOccupied() { return currentCellOccupied; }
	
	public WhitePawn(){
		super(img);
	}
}
