import javax.swing.*;

public class BlackBishop extends JLabel implements Piece{
	private static ImageIcon img = new ImageIcon("Images\\black-bishop.png");
	private static BoardCell currentCellOccupied;
	
	public BlackBishop(){
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
	public Board.ChessPiece getEnumVal() {
		// TODO Auto-generated method stub
		return Board.ChessPiece.BLACK_BISHOP;
	}
}