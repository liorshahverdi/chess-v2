package chess;

import javax.swing.*;

import chess.Board.ChessPiece;

public class WhiteBishop extends JLabel implements Piece{
	private static ImageIcon img = new ImageIcon("Images\\white-bishop.png");
	private static BoardCell currentCellOccupied;
	
	public WhiteBishop(){
		super(img);
	}

	@Override
	public void setCurrentCellOccupied(BoardCell x) {
		// TODO Auto-generated method stub
		this.currentCellOccupied = x;
	}

	@Override
	public BoardCell getCurrentCellOccupied() {
		// TODO Auto-generated method stub
		return currentCellOccupied;
	}

	@Override
	public ChessPiece getEnumVal() {
		// TODO Auto-generated method stub
		return ChessPiece.WHITE_BISHOP;
	}
}
