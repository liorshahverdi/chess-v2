package chess;

public class Move {
	private BoardCell fromCell;
	private BoardCell toCell;
	private Piece movedPiece;
	private Piece targetedPiece;
	
	public Move(BoardCell f, BoardCell t, Piece p){
		this.fromCell = f;
		this.toCell = t;
		this.movedPiece = p;
		this.targetedPiece = null;
	}
	
	public Move(BoardCell f, BoardCell t, Piece mp, Piece tp){
		this.fromCell = f;
		this.toCell = t;
		this.movedPiece = mp;
		this.targetedPiece = tp;
	}
	
	public BoardCell getFromCell() { return fromCell; }
	public BoardCell getToCell() { return toCell; }
	public Piece getMovedPiece() { return movedPiece; }
	public Piece getTargetedPiece() { return targetedPiece; }
	
	public String toString(){ return "From="+this.fromCell.toString()+"\tTo="+this.toCell.toString()
			+"\tmovedPiece="+this.movedPiece.getClass(); }
}
